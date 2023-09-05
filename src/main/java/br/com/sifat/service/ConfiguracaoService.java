package br.com.sifat.service;

import br.com.sifat.model.Configuracao;
import br.com.sifat.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;



    public Configuracao buscarConfiguracao()
    {
        return configuracaoRepository.findAll().get(0);
    }

}
