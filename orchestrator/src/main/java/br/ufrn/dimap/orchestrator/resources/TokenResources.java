package br.ufrn.dimap.orchestrator.resources;

import br.ufrn.dimap.orchestrator.service.TokenService;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.InvalidServiceException;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.InvalidTokenException;
import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.TokenAlreadyValidatedException;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.TokenNotFoundException;
import br.ufrn.dimap.orchestrator.resources.dto.ServerErrorDTO;
import br.ufrn.dimap.orchestrator.resources.dto.TokenCreationDTO;
import br.ufrn.dimap.orchestrator.resources.dto.TokenDTO;
import br.ufrn.dimap.orchestrator.resources.dto.TokenValidationDTO;
import br.ufrn.dimap.orchestrator.resources.dto.assembler.TokenDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Restful endpoint for the token services. 
 * <p>
 * Provides operations for generation and validation of tokens.  
 * 
 * @author Daniel Smith 
 * @author Vitor Greati
 *
 */
@RestController
@RequestMapping("/token")
public class TokenResources {

	/**
	 * Dependency for the token service.
	 */
    private final TokenService tokenService;

    @Autowired
    public TokenResources(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Create a token and returns a DTO containing the token information.
     * 
     * @param tokenCreationDTO the DTO containing the parameters for token creation.
     * @return a DTO containing the token informations
     * @throws InvalidServiceException if the informed appspot for the provider of the service doesn't provide the service. 
     * @throws ApplicationNotFoundException if doesn't exists an application for a provided appspot in DTO.
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    private TokenDTO createToken(@RequestBody TokenCreationDTO tokenCreationDTO) throws InvalidServiceException, ApplicationNotFoundException{

        Token token = tokenService.generateToken(Appspot.from(tokenCreationDTO.getClientAppspot()),Appspot.from(tokenCreationDTO.getServerAppspot()),tokenCreationDTO.getServiceName());

        return TokenDTOAssembler.fromToken(token);
    }

    /**
     * Validate a token and returns the result of the validation.
     * 
     * @param tokenValidationDTO the DTO containing the parameters for the validation.
     * @return the TokenDTO containing the result of the validation, if the token is successfully validated 
     * @throws TokenAlreadyValidatedException if the token was validated before this request
     * @throws InvalidTokenException if a token with the provided parameters in tokenValidationDTO doesn't exists.
     * @throws TokenNotFoundException if a token with the DTO tokenId field wasn't found.
     */
    @RequestMapping(value = "/validate",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    private TokenDTO validateToken(@RequestBody TokenValidationDTO tokenValidationDTO) throws TokenAlreadyValidatedException, InvalidTokenException, TokenNotFoundException {
        String tokenId = tokenValidationDTO.getTokenId();

        if(tokenId == null){
            throw new InvalidTokenException("The token-id is required.");
        }

        Token token = tokenService.validate(UUID.fromString(tokenId), Appspot.from(tokenValidationDTO.getClientAppspot()),Appspot.from(tokenValidationDTO.getServerAppspot()),tokenValidationDTO.getServiceName());

        return TokenDTOAssembler.fromToken(token);
    }

    //EXCEPTION HANDLING
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({TokenNotFoundException.class,ApplicationNotFoundException.class})
    public ResponseEntity<ServerErrorDTO> exceptionHandlerForForbiddenException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<ServerErrorDTO>(new ServerErrorDTO(HttpStatus.FORBIDDEN.value(), e.getMessage(),e.getClass().getName()), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TokenAlreadyValidatedException.class, InvalidTokenException.class})
    public ResponseEntity<ServerErrorDTO> exceptionHandlerForBadRequestException(HttpServletRequest req, Exception e) {
        return new ResponseEntity<ServerErrorDTO>(new ServerErrorDTO(HttpStatus.FORBIDDEN.value(), e.getMessage(),e.getClass().getName()), HttpStatus.FORBIDDEN);
    }

}
