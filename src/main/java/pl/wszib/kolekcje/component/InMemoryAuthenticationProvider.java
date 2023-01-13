package pl.wszib.kolekcje.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

    private static final String INCORRECT_PASSWORD = "Błąd logowania";
    private static final String CREDENTIALS_CANNOT_BE_NULL = "Login ani hasło nie mogą być puste";
    private static final String USERNAME_CANNOT_BE_NULL = "Błąd logowania";

    UserDetailsService userDetailsService;

    @Autowired
    public InMemoryAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Assert.notNull(name, USERNAME_CANNOT_BE_NULL);
        Assert.notNull(credentials, CREDENTIALS_CANNOT_BE_NULL);

        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

        if(!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException(INCORRECT_PASSWORD);
        }


        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password,
                userDetails.getAuthorities());
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);

    }

}