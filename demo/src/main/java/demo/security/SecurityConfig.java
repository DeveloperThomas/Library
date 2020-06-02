package demo.security;

import demo.service.ApplicationUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    private ApplicationUserDetailsService applicationUserDetailsService;

    public SecurityConfig(ApplicationUserDetailsService applicationUserDetailsService) {
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/books/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/books/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")

                .mvcMatchers(HttpMethod.GET, "/api/authors/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/authors/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/api/authors/**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/api/authors/**").authenticated()

                .mvcMatchers("/api/users/**").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}
