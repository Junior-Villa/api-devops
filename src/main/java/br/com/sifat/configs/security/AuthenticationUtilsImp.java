package br.com.sifat.configs.security;

import br.com.sifat.core.interfaces.AuthenticationUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUtilsImp implements AuthenticationUtils {

    public UserDetailsImpl getCurrentUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getToken() {
        return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
    }

}

