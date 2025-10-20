package com.Mais_Saude.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Mais_Saude.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    
@Autowired
UsuarioRepository usuarioRepository;

@Autowired
private UserConfig userConfig;

@Bean
public PasswordEncoder passwordEncoder (){
    return new BCryptPasswordEncoder();

}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(
	            auth -> auth.requestMatchers("/login").permitAll() // liberando acesso para logar
.requestMatchers(   "/css/**", "/js/**","/scss/**","/img/**","/vendor/**").permitAll()
	           // .requestMatchers("").hasAnyAuthority("")		// aqui coloca 1 - caminho ou caminhos - 2 a autoridade necessaria
	            .anyRequest().authenticated())
	            .formLogin(formLogin -> formLogin	
                .loginPage("/login") 
                .defaultSuccessUrl("/", true)
                .permitAll())
                .logout(logout -> logout
                    .logoutUrl("/logout") 
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .logoutSuccessUrl("/login?logout") // para onde vai depois de deslogar
                    .permitAll());
    return http.build();   
}
@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

	         authenticationManagerBuilder
	         .userDetailsService(userConfig)  
	         .passwordEncoder(passwordEncoder());    
       
	  return authenticationManagerBuilder.build();}

      
// descomentar para caso queia um exemplo de senha encriptada 	
// 	@PostConstruct
// public void printPassword() {
//     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
//     System.out.println(encoder.encode("123"));
// }


}
