package com.deepak.photoapp.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//After adding security starter, we need to create this class, as without this class, spring security will not work
// all the requests will be blocked, 401 unauthorized

@Configuration //after annotating with @Configuration we do not need to create this class instance manually
@EnableWebSecurity // this will enable Spring Security
public class WebSecurity {

	@Bean  // as we do not want to create this instance manually, spring container will create it and will call it
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// our api stateless and we will use JWT tokens, we will also disable Cross Site Request Forgery
		http.csrf().disable();
		
		// this will permit all the POST requests that are made to /users endpoint, even if user is not authenticated
		http.authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/users").permitAll()
        .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll() //to show the h2-console page
        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//last line means that the application is stateless and it will not store any HTTP Session object
		// with this configuration, spring security will not create any session
		
		http.headers().frameOptions().disable(); // H2 console uses HTML frames and if we do not add this, H2 console pages will display empty
		
        return http.build();
    }
}

/*
### Explanation of CSRF
**CSRF (Cross-Site Request Forgery)** is an attack that tricks the victim into submitting a malicious request. It exploits the trust that a site has in the user's browser. CSRF can do significant harm, such as:

- Changing user settings.
- Initiating transactions.
- Logging out the user.
- Changing the user's email address or password.

**Disabling CSRF** is important in stateless applications (like those using JWT tokens) because:

- CSRF protection relies on sessions, which are not used in stateless applications.
- It simplifies the security configuration when the application does not maintain user sessions.
*/
