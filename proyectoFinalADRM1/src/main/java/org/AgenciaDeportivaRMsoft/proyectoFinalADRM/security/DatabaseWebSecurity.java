package org.AgenciaDeportivaRMsoft.proyectoFinalADRM.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
		@Bean
	 UserDetailsManager usersCustom(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("select username,password,estatus from Usuarios u where username=?");
		users.setAuthoritiesByUsernameQuery("select u.username,p.perfil from UsuarioPerfil up " + 
		"inner join Usuarios u on u.id = up.idUsuario " + 
		"inner join Perfiles p on p.id = up.idPerfil " + 
		"where u.username=?");
		return users;
		}
		
		@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests()
					// Los recursos estáticos no requieren autenticación
					.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/equipos/**","/error").permitAll()
					// Las vistas públicas no requieren autenticación
					.requestMatchers("/","/css/**","/fonts/**","/signup", "/guardar","/homeEquipos", "/bcript/**", "/acerca", "/buscar", "/equipos/detalle/**").permitAll()
					//Asignar permisos a URLS por roles 
					.requestMatchers("/usuarios/**").hasAnyAuthority("Administrador")
					.requestMatchers("/deportistas/**").hasAnyAuthority("Administrador","Supervisor")
					.requestMatchers("/equipos/**").hasAnyAuthority("Administrador","Supervisor")
					.requestMatchers("/estadios/**").hasAnyAuthority("Administrador","Supervisor")
					.requestMatchers("/productos/**").hasAnyAuthority("Administrador","Supervisor")
					.requestMatchers("/perfiles/**").hasAnyAuthority("Administrador")
					.requestMatchers("/usuarioPerfil").hasAnyAuthority("Usuario")
					// Todas las demás URLs de la Aplicación requieren autenticación
					.anyRequest().authenticated()
					// El formulario de Login no requiere autenticacion
					.and().formLogin().loginPage("/login").permitAll();
			return http.build();
		}
		
@Bean
		 PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}


