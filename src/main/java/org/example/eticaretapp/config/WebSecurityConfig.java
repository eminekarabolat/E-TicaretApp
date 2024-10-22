package org.example.eticaretapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http.csrf().disable().cors().disable(); -> this is deprecated, so I used 2 lines below
		http.csrf(csrf -> csrf.disable())  // Disable CSRF
		    .cors(cors -> cors.disable())  // Disable CORS
		    // I want to add jwtRequestFilter before AuthorizationFilter
		    .authorizeHttpRequests(auth -> auth.requestMatchers("/swagger-ui/**",  // Allow access to Swagger UI
		                                                        "/v3/api-docs/**",  // Allow access to OpenAPI docs (used by Swagger)
		                                                        "/swagger-resources/**",  // Allow access to Swagger resources
		                                                        "/webjars/**",  // Allow access to webjars (Swagger uses it for CSS/JS)
		                                                        "/swagger-ui.html"  // Allow access to main Swagger page).permitAll()
		                                       ).permitAll()
		                                       /*.requestMatchers("/v1/dev/auth/dologin", "/v1/dev/auth" +
				                                                                                                                    "/register",
		                                                                     "/product", "/auth/verify", "/error")
		                                       .permitAll().anyRequest().permitAll()*/
		    );
		return http.build();
		
	}
}