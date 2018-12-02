package br.ufrn.dimap.orchestrator.token.integration.repository;

import br.ufrn.dimap.orchestrator.OrchestratorApplication;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.domain.token.TokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application.properties")
@ContextConfiguration(classes = {OrchestratorApplication.class})
public class TokenRepositoryIntegrationTests {

    @Autowired
    private TokenRepository repository;

    @Test
    public void testSaveToken(){
        //Using the persistence constructor to initialize the token just for database integration testing purposes.
        Token token = new Token();

        Appspot clientAppspot = Appspot.from("Client appspot");
        Appspot serverAppspot = Appspot.from("Server appspot");

        token.setClientAppspot(clientAppspot);
        token.setServerAppspot(serverAppspot);
        token.setServiceName("Service name");
        token.setId(UUID.randomUUID());
        token.setGenerationDate(new Date());
        token.setValidationDate(new Date());

        Token tokenAfterSave = repository.save(token);

        assertNotNull(tokenAfterSave);
    }

}
