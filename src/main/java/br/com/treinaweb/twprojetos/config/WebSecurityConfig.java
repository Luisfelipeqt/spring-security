package br.com.treinaweb.twprojetos.config;


import br.com.treinaweb.twprojetos.enums.Perfil;
import br.com.treinaweb.twprojetos.services.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServicesImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/adminlte/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                .antMatchers("/**/cadastrar").hasAuthority(Perfil.ADMIN.toString())
                .antMatchers("/**/editar").hasAuthority(Perfil.ADMIN.toString())
                .antMatchers("/**/excluir").hasAuthority(Perfil.ADMIN.toString())
                .anyRequest().authenticated();

        http.formLogin() //-> inicialização da configuração do ‘login’
                .loginPage("/login") //— > qual a rota que a minha aplicação utilizará para realizar o ‘login’ dos utilizadores
                .defaultSuccessUrl("/clientes") //— > após o ‘login’ ser feito com sucesso para onde o utilizador precisa ser redirecionado
                .permitAll(); //-> liberando essas rotas para de login para serem usadas


    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
