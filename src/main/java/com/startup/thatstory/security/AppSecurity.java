package com.startup.thatstory.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		.csrf().disable()
		.antMatcher("**")
		.authorizeRequests()
		//.antMatchers("/public/**")
		//.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.oauth2Login();
	}
	
	
//	public void successHandler(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws JsonProcessingException, IOException
//	{
//	response.getWriter().write(new ObjectMapper().writeValueAsString(Collections.singletonMap("accessTokenIs", "accessToken")));	
//	}
//	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		allowedOrigins.addAll(allowedOrigins);
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOriginPatterns(allowedOrigins);
	    configuration.addAllowedHeader("*");
	    configuration.addAllowedMethod("*");
	    configuration.setAllowCredentials(true);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
}