package br.ufrn.dimap.orchestrator.resources;

import br.ufrn.dimap.orchestrator.application.TokenService;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.token.InvalidServiceException;
import br.ufrn.dimap.orchestrator.domain.token.InvalidTokenException;
import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.domain.token.TokenAlreadyValidatedException;
import br.ufrn.dimap.orchestrator.domain.token.TokenNotFoundException;
import br.ufrn.dimap.orchestrator.resources.dto.TokenCreationDTO;
import br.ufrn.dimap.orchestrator.resources.dto.TokenDTO;
import br.ufrn.dimap.orchestrator.resources.dto.TokenValidationDTO;
import br.ufrn.dimap.orchestrator.resources.dto.assembler.TokenDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/token")
@RestController
public class TokenResources {

    private final TokenService tokenService;

    @Autowired
    public TokenResources(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    private TokenDTO createToken(@RequestBody TokenCreationDTO tokenCreationDTO) throws InvalidServiceException, ApplicationNotFoundException{

        Token token = tokenService.generateToken(Appspot.from(tokenCreationDTO.getClientAppspot()),Appspot.from(tokenCreationDTO.getServerAppspot()),tokenCreationDTO.getServiceName());

        return TokenDTOAssembler.fromToken(token);
    }

    @RequestMapping(value = "/validate",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    private TokenDTO createToken(@RequestBody TokenValidationDTO tokenValidationDTO) throws TokenAlreadyValidatedException, InvalidTokenException, TokenNotFoundException {
        Token token = tokenService.validate(UUID.fromString(tokenValidationDTO.getTokenId()), Appspot.from(tokenValidationDTO.getClientAppspot()),Appspot.from(tokenValidationDTO.getServerAppspot()),tokenValidationDTO.getServiceName());

        return TokenDTOAssembler.fromToken(token);
    }

}
