package br.com.sifat.controller;

import br.com.sifat.service.ConfiguracaoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/teste")
public class DevopsController {


    @Autowired
    private ConfiguracaoService configuracaoService;


    @GetMapping
    public ResponseEntity<String> testeApiEmFuncionamento() {
        return ResponseEntity.ok("Api em funcionamento SEM token");
    }


    @GetMapping("/banco")
    public ResponseEntity<?> testeApiEmFuncionamentoComBanco() {
        return ResponseEntity.ok(configuracaoService.buscarConfiguracao());
    }

    @GetMapping("/seguro")
    public ResponseEntity<String> buscarPorId() {
        return ResponseEntity.ok("Api em funcionamento COM token");
    }




}
