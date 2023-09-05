package br.com.sifat.configs.security;

import br.com.sifat.api.ApiMicroSessao;
import br.com.sifat.core.dtos.JwtTokenDecodeDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationJwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    ApiMicroSessao apiMicroSessao;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtStr = getTokenHeader(request);
            if (jwtStr != null && jwtProvider.validateJwt(jwtStr)) {
                JwtTokenDecodeDTO payload = jwtProvider.getPayload(jwtStr);
                String roleStr = jwtProvider.getClaimNameJwt(jwtStr, "roles");
                apiMicroSessao.validarSessoesEstaValida(jwtStr);

                UserDetailsImpl userDetails = UserDetailsImpl.build(payload.getIdSessao(), roleStr);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        jwtStr,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot ser User Authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenHeader(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
