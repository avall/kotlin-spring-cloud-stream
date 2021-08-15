package com.avall.ms.attachments.application.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").authenticated()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/actuator/prometheus").authenticated()
            .antMatchers("/actuator/info").authenticated()
            .antMatchers("/actuator/health").permitAll()
            .antMatchers("/actuator/health/*").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS)
    }
}