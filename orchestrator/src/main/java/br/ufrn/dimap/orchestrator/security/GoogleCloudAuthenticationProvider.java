package br.ufrn.dimap.orchestrator.security;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GoogleCloudAuthenticationProvider implements AuthenticationProvider {

    private final ApplicationService applicationService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GoogleCloudAuthenticationProvider(ApplicationService applicationService, PasswordEncoder passwordEncoder) {
        this.applicationService = applicationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String password = authentication.getCredentials().toString();

            if(password == null || password.isEmpty()){
                throw new UsernameNotFoundException("Appspot or password invalid.");
            }

            Application application = applicationService.findApplicationByAppspot(Appspot.from(authentication.getName()));

            if(application == null){
                throw new UsernameNotFoundException("Appspot or password invalid.");
            }

            if(!passwordEncoder.matches(password,application.getPassword())){
                throw new UsernameNotFoundException("Appspot or password invalid.");
            }

            Appspot applicationAppspot = application.getAppspot();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(applicationAppspot.getAppspotName(), password, new ArrayList<>());

            return usernamePasswordAuthenticationToken;
        } catch (ApplicationNotFoundException e) {
            throw new UsernameNotFoundException("Appspot or password invalid.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
