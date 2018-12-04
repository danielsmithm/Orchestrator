package br.ufrn.dimap.orchestrator.token;

import static org.junit.Assert.assertNotNull;

import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedServiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.ApplicationRepository;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.token.Token;
import br.ufrn.dimap.orchestrator.domain.token.TokenFactory;
import br.ufrn.dimap.orchestrator.domain.token.TokenRepository;
import br.ufrn.dimap.orchestrator.domain.token.exceptions.InvalidServiceException;

@RunWith(MockitoJUnitRunner.class)
public class TokenFactoryTests {

	private TokenFactory tokenFactory;
	
	@Mock
	private TokenRepository tokenRepositoryMock;
	
	@Mock
	private ApplicationRepository applicationRepositoryMock;

	@Mock
	private ProvidedServiceRepository providedServiceRepository;
	
	@Mock
	private Application applicationMock;
	
	@Before
	public void setup() {
		tokenFactory = new TokenFactory(tokenRepositoryMock,applicationRepositoryMock, providedServiceRepository);
	}
	
	@Test(expected=ApplicationNotFoundException.class)
	public void testGenerateToken_withoutClientAppspot() throws InvalidServiceException, ApplicationNotFoundException {
		tokenFactory.generateToken(null, Appspot.from("test"), "test");
	}
	
	@Test(expected=ApplicationNotFoundException.class)
	public void testGenerateToken_withoutServerAppspot() throws InvalidServiceException, ApplicationNotFoundException {
		tokenFactory.generateToken(Appspot.from("test"), null, "test");
	}
	
	@Test(expected=ApplicationNotFoundException.class)
	public void testGenerateToken_withoutServiceName() throws InvalidServiceException, ApplicationNotFoundException {
		tokenFactory.generateToken(Appspot.from("test"), Appspot.from("test2"), null);
	}
	
	@Test
	public void testGenerateToken() throws InvalidServiceException, ApplicationNotFoundException {

		String serviceName = "test";
		Appspot clientAppspot = Appspot.from(serviceName);
		Appspot serverAppspot = Appspot.from("test2");
		
		Mockito.when(applicationRepositoryMock.existsApplicationWithAppspot(clientAppspot)).thenReturn(true);
		Mockito.when(applicationRepositoryMock.existsApplicationWithAppspot(serverAppspot)).thenReturn(true);		
		Mockito.when(applicationRepositoryMock.findByAppspot(serverAppspot)).thenReturn(applicationMock);
		
		Mockito.when(providedServiceRepository.hasProvidedService(serverAppspot,serviceName)).thenReturn(true);
		
		Token generatedToken = tokenFactory.generateToken(clientAppspot, serverAppspot, serviceName);
		
		assertNotNull(generatedToken);
		
	}
	
}
