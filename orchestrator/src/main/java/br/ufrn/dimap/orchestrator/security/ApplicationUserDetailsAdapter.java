package br.ufrn.dimap.orchestrator.security;

import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ApplicationUserDetailsAdapter implements UserDetails {

    private Application application;

    public ApplicationUserDetailsAdapter(Application application) {
        this.application = Objects.requireNonNull(application);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return application.getPassword();
    }

    @Override
    public String getUsername() {
        Appspot appspot = application.getAppspot();

        if(appspot != null) {
            return appspot.getAppspotName();
        }

        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Application getApplication() {
        return application;
    }

}
