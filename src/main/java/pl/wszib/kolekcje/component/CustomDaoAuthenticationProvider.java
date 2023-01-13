package pl.wszib.kolekcje.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CustomDaoAuthenticationProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;

    private static final String USERNAME_CANNOT_BE_NULL = "Błędny login lub hasło";
    private static final String CREDENTIALS_CANNOT_BE_NULL = "Login ani hasło nie mogą być puste";
    public static final String INCORRECT_PASSWORD = "Błędny login lub hasło";

    @Autowired
    public CustomDaoAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Assert.notNull(name, USERNAME_CANNOT_BE_NULL);
        Assert.notNull(credentials, CREDENTIALS_CANNOT_BE_NULL );

        if(credentials instanceof String) {
            return null;
        }
        String password = credentials.toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(name);

        boolean passwordMatch = userDetails.getPassword().equals(password);

        if (!passwordMatch) {
            throw new BadCredentialsException(INCORRECT_PASSWORD);
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(name, password, userDetails.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
