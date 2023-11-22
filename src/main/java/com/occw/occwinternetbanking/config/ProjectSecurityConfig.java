package com.occw.occwinternetbanking.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.occw.occwinternetbanking.filter.CsrfCookieFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {
	
	/*
	 * @Autowired private occwAuthenticationProvider authProvider;
	 * 
	 * @Bean public AuthenticationManager authManager(HttpSecurity http) throws
	 * Exception { AuthenticationManagerBuilder authenticationManagerBuilder =
	 * http.getSharedObject(AuthenticationManagerBuilder.class);
	 * authenticationManagerBuilder.authenticationProvider(authProvider); return
	 * authenticationManagerBuilder.build(); }
	 */

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		
		http.csrf((csrf) -> csrf
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		).addFilterAfter(new CsrfCookieFilter(),BasicAuthenticationFilter.class);
		
		http.cors().configurationSource( new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}			
		});
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(AntPathRequestMatcher.antMatcher("/occwp/*")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/myAccount")).authenticated()
				.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/customerregistration")).permitAll());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
	
	//meka use krananna puluwan in memory users la hadanna ona  nam

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() {
	 * 
	 * UserDetails admin = User.withDefaultPasswordEncoder() .username("admin")
	 * .password("12345") .authorities("admin") .build(); UserDetails user =
	 * User.withDefaultPasswordEncoder() .username("user") .password("1234")
	 * .authorities("read") .build(); return new
	 * InMemoryUserDetailsManager(admin,user);
	 * 
	 * 
	 * UserDetails admin = User.withUsername("Admin") .password("1234")
	 * .authorities("admin") .build(); UserDetails user = User.withUsername("user")
	 * .password("12345") .authorities("read") .build();
	 * 
	 * return new InMemoryUserDetailsManager(admin,user);
	 * 
	 * }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	//cutom table ekak nathuwa already exciting user table ekata danna spring jdbc authentication eken meka default denwa
	/*
	 * @Bean UserDetailsManager users(DataSource dataSource) { return new
	 * JdbcUserDetailsManager(dataSource); }
	 */

	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

}
