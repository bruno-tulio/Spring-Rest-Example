package com.homeio.forum.config

import com.homeio.forum.config.filter.StatelessAuthenticationFilter
import com.homeio.forum.repository.UsuarioRepository
import com.homeio.forum.security.AppUserDetailsService
import com.homeio.forum.service.JWTService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@ComponentScan(basePackageClasses = [AppUserDetailsService::class])
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Autowired
    @Qualifier("appUserDetailService")
    private lateinit var userDetails: UserDetailsService

    @Autowired
    private lateinit var jwtService: JWTService

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    //authenticação user
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetails)?.passwordEncoder(passawordEnconder())
    }

    //mapaamento recursos web
    override fun configure(web: WebSecurity) {

    }

    //mapaamento url permisoes
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/*/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/*/topicos/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(StatelessAuthenticationFilter(jwtService, usuarioRepository),

                UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    fun passawordEnconder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }



}


fun main(args: Array<String>) {
    println("SENHA A COIPIAR: ${BCryptPasswordEncoder().encode("123456")}")
}