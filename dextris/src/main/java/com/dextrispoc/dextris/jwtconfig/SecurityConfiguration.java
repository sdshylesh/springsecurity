package com.dextrispoc.dextris.jwtconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dextrispoc.dextris.jwtfilter.JwtFilterDex;
import com.dextrispoc.dextris.jwtservice.UserServiceJwt;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserServiceJwt userServiceJwt;
	
	 @Autowired
	    private JwtFilterDex jwtFilterDex;
	
	
	 @Bean
	 AuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 
		 provider.setUserDetailsService(userServiceJwt);
		 provider.setPasswordEncoder(new BCryptPasswordEncoder());
		 return provider;
	 } 
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	 @Bean
	 public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
		 http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/creatingAdmin/hello","/creatingUser/user","/creatingAdmin/admin","/creatingAdmin/verify").permitAll()
	            .anyRequest().authenticated()
//	            .antMatchers("/admin/**").hasRole("ADMIN") // Require ADMIN role for URLs starting with /admin
//	            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
	        )
	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).apply(new CorsConfigurer<HttpSecurity>());
	      http.addFilterBefore(jwtFilterDex, UsernamePasswordAuthenticationFilter.class);//validating jwttoken
	      
	      return http.build();
	 }

}
