package dev.nicollas.learnspring.controllers;

import dev.nicollas.learnspring.domain.Contato;
import dev.nicollas.learnspring.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/cadastrar")
    public String createUser(Contato contato) {
        return "adicionar";
    }

    @PostMapping("/add")
    public String adicionarContato(@Validated Contato contato, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "adicionar";
        }

        service.salver(contato);
        model.addAttribute("contatos", service.buscarTodos());
        return "contatos";
    }

    @GetMapping("/edit/{id}")
    public String editarContato(@PathVariable("id") Integer id, Model model) {
        Contato contato = service.buscarPorId(id);
        model.addAttribute("contato", contato);

        return "editar";
    }

    @PostMapping("/update/{id}")
    public String atualizarContato(@PathVariable("id") Integer id, Contato contato, Model model, BindingResult bindingResult) {
        Contato objetoContato = service.buscarPorId(id);
        if (bindingResult.hasErrors()) {
            contato.setId(id);
            return "atualizar";
        }

        service.salver(contato);
        model.addAttribute("contatos", service.buscarTodos());
        return "contatos";
    }
}
