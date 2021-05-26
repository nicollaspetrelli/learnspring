package dev.nicollas.learnspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContatoController {

    /*
        Para views de arquivos estáticos: resources/static/example.html
        Para views com template engine: resources/templates/example.jpa
        Também é necessário configurar o application.properties para templates engines
    */

    @GetMapping("/contatos")
    public String getHomePage() {
        return "contato.html";
    }

}
