package br.com.sifat.model;


import aakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;



    @NotNull
    @Column(name = "HOMOLOGACAO", nullable = false)
    private boolean homologacao = false;

    @Column(name = "DEBUG")
    private boolean debug = false;


    @Column(name = "TRACE_API")
    private boolean traceApi = false;

}
