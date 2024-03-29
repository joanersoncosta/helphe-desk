package com.wakanda.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wakanda.config.security.service.AutenticacaoSecurityService;
import com.wakanda.config.security.service.TokenService;
import com.wakanda.credencial.application.service.CredencialService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final TokenService tokenService;
	private final CredencialService credencialService;
	private final AutenticacaoSecurityService autenticacaoSecurityService;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoSecurityService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authority -> authority

	            // Permissões para Admin
//	            .antMatchers(HttpMethod.POST, "/v1/tecnico/admin/cadastro").hasRole("ROLE_ADMIN")
	          
	            // Permissões para Técnico
//	            .antMatchers(HttpMethod.GET, "/v1/cliente/restrito/**").hasAnyRole("ROLE_ADMIN", "ROLE_TECNICO")
//	            .antMatchers(HttpMethod.GET, "/v1/chamado/restrito/**").hasAnyRole("ROLE_ADMIN", "ROLE_TECNICO")
//	            .antMatchers(HttpMethod.PATCH, "/v1/tecnico/restrito/**").hasAnyRole("ROLE_ADMIN", "ROLE_TECNICO")

	            // Permissões Públicas
	            .antMatchers("/public/**").permitAll()
	            .antMatchers("/v1/tecnico/**").permitAll()
	            .antMatchers("/v1/chamado/**").permitAll()
	            .antMatchers("/v1/cliente/public/**").permitAll()

				.anyRequest()
				.authenticated()
		)		
		.addFilterBefore(new FiltroToken(tokenService, credencialService),
			UsernamePasswordAuthenticationFilter.class);
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v3/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-ui/**",
				"/swagger-ui.html");
	}

}
