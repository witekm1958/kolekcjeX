package pl.wszib.kolekcje;

//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.wszib.kolekcje.component.CustomDaoAuthenticationProvider;
import pl.wszib.kolekcje.services.impl.JpaUserDetailsService;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//   AuthenticationProvider authenticationProvider;
//
//   @Autowired
//   public SecurityConfig(AuthenticationProvider authenticationProvider) {
//       this.authenticationProvider = authenticationProvider;
//   }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
//    }

    JpaUserDetailsService userDetailsService;
    CustomDaoAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JpaUserDetailsService UserDetailsService, CustomDaoAuthenticationProvider authenticationProvider) {
        this.userDetailsService = userDetailsService;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/login").permitAll()
               .antMatchers("/register").permitAll()
               .antMatchers("/").permitAll()
               .antMatchers("/h2-console/**").permitAll()
               .antMatchers("/css/**", "/js/**", "/img/**", "/icon/**").permitAll()
               .antMatchers("/admin_panel").hasAuthority("ADMIN")
               .antMatchers("/user_panel").hasAuthority("USER")
               .anyRequest().authenticated()
               .and()
           .formLogin()
               .loginPage("/login")
               .usernameParameter("username")
               .passwordParameter("password")
               .defaultSuccessUrl("/", true)
               .and()
           .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/")
               .deleteCookies("JSESSIONID")
               .and()
           .csrf().disable()
               ;

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
