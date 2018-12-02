package br.ufrn.dimap.orchestrator.domain.token;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.TokenNotFoundException;
import com.google.cloud.datastore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Repository
public class TokenRepository {

    public static final String TOKENID_FIELD = "token_id";
    public static final String TOKEN_ENTITY_NAME = "Token";

    private final Datastore datastore;
    private final KeyFactory keyFactory;

    @Autowired
    public TokenRepository(Datastore datastore) {
        this.datastore = Objects.requireNonNull(datastore, "The datastore object could not be null.");
        this.keyFactory = datastore.newKeyFactory().setKind(TOKEN_ENTITY_NAME);
    }

    public Token save(Token generatedToken) {
        Key key = keyFactory.newKey(generatedToken.getId().toString());
        Entity app = Entity.newBuilder(key)
                .set(TOKENID_FIELD, generatedToken.getId().toString())
                .set("client_appspot", generatedToken.getClientAppspot().getAppspotName())
                .set("server_appspot", generatedToken.getServerAppspot().getAppspotName())
                .set("service_name",generatedToken.getServiceName())
                .set("generation_date",generatedToken.getGenerationDate().getTime())
                .set("validation_date", generatedToken.getValidationDate().getTime())
                .build();

        // TODO add services etc

        Entity entityAfterPersisted = datastore.put(app);

        return convertToApplication(entityAfterPersisted);
    }

    private Token convertToApplication(Entity entity) {
        Token token = new Token();

        token.setId(UUID.fromString(entity.getString(TOKENID_FIELD)));
        token.setClientAppspot(Appspot.from(entity.getString("client_appspot")));
        token.setServerAppspot(Appspot.from(entity.getString("server_appspot")));
        token.setServiceName(entity.getString("service_name"));

        long generationDate = entity.getLong("generation_date");
        if (generationDate != 0) {
            token.setGenerationDate(new Date(generationDate));
        }

        long validationDate = entity.getLong("validation_date");
        if (validationDate != 0) {
            token.setValidationDate(new Date(validationDate));
        }

        return token;
    }

    public Token findTokenById(UUID tokenUUID) throws TokenNotFoundException {

        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(TOKEN_ENTITY_NAME)
                .setFilter(StructuredQuery.PropertyFilter.eq("__key__",
                        keyFactory.newKey(tokenUUID.toString())))
                .build();

        QueryResults<Entity> result = datastore.run(query);

        if(!result.hasNext()){
            throw new TokenNotFoundException("A token with the provided id was not found.");
        }

        Token token = convertToApplication(result.next());

        if(result.hasNext()){
            throw new IllegalStateException("The query returned more than one result, when a single result was expected");
        }

        return token;
    }

}
