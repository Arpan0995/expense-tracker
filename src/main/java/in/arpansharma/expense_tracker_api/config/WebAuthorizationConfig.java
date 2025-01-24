package in.arpansharma.expense_tracker_api.config;

import in.arpansharma.expense_tracker_api.auth.CustomAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {

    @Autowired
    private CustomAuthenticator customAuthenticator;

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authenticationProvider(customAuthenticator);
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers("/login","/register").permitAll()
                .anyRequest().authenticated());

        return httpSecurity.build();
    }
}
