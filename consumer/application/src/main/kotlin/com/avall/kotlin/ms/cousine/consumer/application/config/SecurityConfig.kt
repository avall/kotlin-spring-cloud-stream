package com.avall.kotlin.ms.cousine.consumer.application.config

import com.avall.kotlin.ms.cousine.consumer.infrastructure.adapters.CustomUserDetailsService
import com.avall.kotlin.ms.cousine.consumer.infrastructure.security.IJwtProvider
import com.avall.kotlin.ms.cousine.consumer.infrastructure.security.JwtAuthenticationEntryPoint
import com.avall.kotlin.ms.cousine.consumer.infrastructure.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Autowired
    private val customUserDetailsService: CustomUserDetailsService,

    @Autowired
    private val unauthorizedHandler: JwtAuthenticationEntryPoint,

    @Autowired
    private val tokenProvider: IJwtProvider

) : WebSecurityConfigurerAdapter() {

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(tokenProvider, customUserDetailsService)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http
            .cors().disable()
            .csrf().disable()
            .logout().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .headers().frameOptions().sameOrigin()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/customer/**").permitAll()
            .antMatchers("/cousine/**").permitAll()
            .antMatchers("/store/**").permitAll()
            .antMatchers("/product/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers(HttpMethod.GET, "/order/**").permitAll()
            .anyRequest().authenticated()
        // @formatter:on
        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS)
    }
}