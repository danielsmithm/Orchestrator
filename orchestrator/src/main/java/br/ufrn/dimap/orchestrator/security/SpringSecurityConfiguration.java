package br.ufrn.dimap.orchestrator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final GoogleCloudAuthenticationProvider authenticationProvider;
    private final DetailService userDetailsService;

    @Autowired
    public SpringSecurityConfiguration(GoogleCloudAuthenticationProvider authenticationProvider, DetailService userDetailsService) {
        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html").anonymous()
                .antMatchers("/application/register").anonymous()
                .antMatchers("/application").authenticated()
                .antMatchers("/services").authenticated()
                .antMatchers("/application/services/add").authenticated()
                .antMatchers("/application/services/list").anonymous()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/application", true)
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                ;
    }

    @Bean
    public SecurityExpressionHandler webexpressionHandler(){
        return new DefaultWebSecurityExpressionHandler();
    }

}
