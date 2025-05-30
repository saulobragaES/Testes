package br.com.teste.Aplicacao.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empacotamento")
public class RecursoController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('USER')")
    public String testeMensagem() {
        return "Olá, Testando a URL mensagem.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // Exige que o usuário tenha o papel 'ADMIN'
    public String admin() {
        return "Olá, Administrador tem acesso exclusivo.";
    }

    @GetMapping("/public/info")
    public String Info() {
        return "Essa URL e publica.";
    }
}
