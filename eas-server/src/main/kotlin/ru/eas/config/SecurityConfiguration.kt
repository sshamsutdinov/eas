package ru.eas.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.eas.domain.Role
import ru.eas.rest.Api
import ru.eas.security.AuthEntryPoint
import ru.eas.security.AuthTokenFilter
import ru.eas.security.UserDetailsServiceImpl

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class SecurityConfiguration(
    private val userDetailsService: UserDetailsServiceImpl,
    private val unauthorizedHandler: AuthEntryPoint,
    private val authTokenFilter: AuthTokenFilter
) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    open fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("${Api.AUTH}/**").permitAll()
            .antMatchers(HttpMethod.GET).permitAll()
            .anyRequest().authenticated()

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}