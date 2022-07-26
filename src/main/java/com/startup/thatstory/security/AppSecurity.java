package com.startup.thatstory.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity

public class AppSecurity extends WebSecurityConfigurerAdapter {
	
	
	
	@Value("${app.cors.allowedOrigins}")
	private List<String> allowedOrigins;
	
	
	@Override
	protected void configure(HttpSecurity httpsecurity) throws Exception {

		httpsecurity
		.cors()
		.and()
		.antMatcher("**")
		.antMatcher("/*")
		.antMatcher("/**")
		.authorizeRequests()
		//.antMatchers("/public/**")
		.antMatchers(HttpMethod.POST,"**").permitAll()
		.antMatchers(HttpMethod.PUT,"**").permitAll()
		.antMatchers(HttpMethod.DELETE,"**").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.oauth2Login()
		.and()
		.csrf().disable();
		httpsecurity.headers().frameOptions().disable();
	}
	
	
//	public void successHandler(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws JsonProcessingException, IOException
//	{
//	response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("accessTokenIs", "accessToken")));	
//	}
//	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		
//		allowedOrigins.addAll(allowedOrigins);
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOriginPatterns(allowedOrigins);
////	    configuration.addAllowedOrigin("http://localhost:3000");
//	    configuration.addAllowedHeader("*");
//	    configuration.addAllowedMethod("GET");
//	    configuration.addAllowedMethod("POST");
//	    configuration.addAllowedMethod("PUT");
//	    List<String> values = new ArrayList();
//	    values.add("Authorization");
//	    values.add("Cache-Control");
//	    values.add("Content-Type");
//	    configuration.setAllowedHeaders(values);
//	    configuration.setAllowCredentials(true);
//	    configuration.applyPermitDefaultValues();
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
	
	   @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOriginPatterns(allowedOrigins);
	        configuration.setAllowedMethods(ImmutableList.of("HEAD",
	                "GET", "POST", "PUT", "DELETE", "PATCH"));
	        // setAllowCredentials(true) is important, otherwise:
	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
	        configuration.setAllowCredentials(true);
	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
	        // will fail with 403 Invalid CORS request
	        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("*/**", configuration);
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }


	
}