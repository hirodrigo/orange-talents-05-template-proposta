package br.com.zupacademy.rodrigo.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
@Configuration
@Profile("default")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.GET, "/actuator/health/").hasAuthority("SCOPE_propostas")
				.antMatchers(HttpMethod.GET, "/actuator/prometheus/").hasAuthority("SCOPE_propostas")
				.anyRequest().authenticated())
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}

}
