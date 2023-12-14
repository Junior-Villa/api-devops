package br.com.sifat.controller;

import br.com.sifat.service.ConfiguracaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class TesteDevopsControllerTest {

    @InjectMocks
    private TesteDevopsController testeDevopsController;

    @Mock
    private ConfiguracaoService configuracaoService;

    @Test
    void testeApiEmFuncionamento() {
        testeDevopsController.testeApiEmFuncionamento();
    }

    @Test
    void testeApiEmFuncionamentoComBanco() {
    }

    @Test
    void buscarPorId() {
    }
}