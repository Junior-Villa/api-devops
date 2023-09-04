package br.com.sifat;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApiTesteDevopsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiTesteDevopsApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
