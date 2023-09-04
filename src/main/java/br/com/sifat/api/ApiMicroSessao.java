package br.com.sifat.api;


import br.com.sifat.core.exeptions.SifatServiceException;
import br.com.sifat.core.generic.ApiMicroSessaoGeneric;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ApiMicroSessao extends ApiMicroSessaoGeneric {


    public void validarSessoesEstaValida(String token) {


        HttpEntity<Void> entity = new HttpEntity<>(createJsonHeader(token));


        ResponseEntity<Void> response = enviarRequisicao(entity, "/valida-sessao", Void.class, HttpMethod.GET);


        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new SifatServiceException(response.getStatusCode(), "Sessão não está válida [SESSAO]");
        }


    }


}
