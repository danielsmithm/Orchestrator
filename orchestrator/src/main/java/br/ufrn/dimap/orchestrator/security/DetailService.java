package br.ufrn.dimap.orchestrator.security;

import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetailService implements UserDetailsService {

    @Autowired
    private ApplicationService applicationService;


    @Override
    public UserDetails loadUserByUsername(String appspot) throws UsernameNotFoundException {
        try {
            Application application = applicationService.findApplicationByAppspot(Appspot.from(appspot));

            return new ApplicationUserDetailsAdapter(application);
        } catch (ApplicationNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

}
