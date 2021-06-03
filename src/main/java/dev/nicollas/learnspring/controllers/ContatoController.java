package dev.nicollas.learnspring.controllers;

import dev.nicollas.learnspring.domain.Contato;
import dev.nicollas.learnspring.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class ContatoController {

    @Autowired /* Autowired é responsável por fazer a injeção de dependencia do objeto */
    private ContatoService service;

    /*
        Para views de arquivos estáticos: resources/static/example.html
        Para views com template engine: resources/templates/example.jpa
        Também é necessário configurar o application.properties para templates engines
    */

    @GetMapping("/contatos")
    public String getHomePage(Model model) {
        List<Contato> contatos = service.buscarTodos();
        model.addAttribute("contatos", contatos);
        return "contatos.html";
    }

    @GetMapping("/delete/{id}")
    public String apagarUsuario(@PathVariable("id") int id, Model model){
        service.remover(id);
        model.addAttribute("contatos", service.buscarTodos());
        return "contatos.html";
    }

    @PutMapping("/edit/{id}")
    public String editarUsuario(@PathVariable("id") int id, Model model){
        return "contatos.html";
    }
}
