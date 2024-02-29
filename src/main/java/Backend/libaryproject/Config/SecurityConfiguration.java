package Backend.libaryproject.Config;
import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;



@Configuration // Set this up setting up the application's environment
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable Cross Site Request Forgery
        //Cross Site Request Forgery is an
        // attack that forces an end user to execute unwanted actions on a web application

        http.csrf(csrf -> csrf.disable());

        // Protect endpoints at /api/<type>/secure
        http.authorizeHttpRequests(configurer -> //authorizeRequests is a method that takes a configurer;
                             // however, it deprecated,
                        // so we have to use the below method
                        configurer
                                //This line specifies the list of endpoints
                                // that should be protected and require authentication.
                                // Requests to URLs that match any of the
                                // listed patterns will need to be authenticated.
                                //
                                //authenticated(): This method specifies that any request matching the patterns
                                // defined above should be authenticated, meaning only authenticated users
                                // will be allowed to access those endpoints.
                                .requestMatchers("/api/reviews/secure/**")
                                .authenticated())// anyone with valid OAuth2 tokens can access
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults()) // expecting a JWT token
                         );

        // Add CORS filters
       http.cors(Customizer.withDefaults());

        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }

}














