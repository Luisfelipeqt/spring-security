package br.com.treinaweb.twprojetos.services;

import br.com.treinaweb.twprojetos.entidades.Funcionario;
import br.com.treinaweb.twprojetos.entidades.UserDetailsImpl;
import br.com.treinaweb.twprojetos.repositorios.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepositorio.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        return new UserDetailsImpl(funcionario);
    }
}
