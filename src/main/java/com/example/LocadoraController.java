package com.example;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import models.Locadora;
import models.Carro;
import models.Cliente;
import models.Locacao;
import requests.IdRequest;
import responses.ErroResponse;

@RestController
@RequestMapping("/acmerent")
public class LocadoraController {
    private Locadora locadora;
    private final LocadoraRepository repository;

    public LocadoraController(LocadoraRepository repository) {
        this.repository = repository;
        this.locadora = repository.getLocadora();
    }
    
    @GetMapping("/init")
    public String init() {
        return "Locadora já foi inicializada!";
    }

    @GetMapping("/listaautomoveis")
    public List<Carro> getAutomoveis() {
        return repository.getAllCarros();
        
    }

    @GetMapping("/listaclientes")
    public List<Cliente> getClientes() {
        return repository.getAllClientes();
    }

    @GetMapping("/listalocacoes")
    public List<Locacao> getLocacoes() {
        return locadora.getLocacoes();
    }

    @GetMapping("/consultacliente")
    public ResponseEntity<?> consultaCliente(@RequestParam int id) {
        Cliente cliente = repository.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            ErroResponse erro = new ErroResponse("Usuario não encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @PostMapping("/validaautomovel")
    public ResponseEntity<?> validaAutomovel(@RequestBody IdRequest id) {
        Carro carro = locadora.getCarroById(id.getId());
        if (carro != null) {
            return ResponseEntity.ok(carro.isDisponivel());
        } else {
            ErroResponse erro = new ErroResponse("Automóvel não encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @PostMapping("/atendimento/cadlocacao")
    public boolean cadastrarLocacao(@RequestBody Locacao locacao) {
        try {
            repository.addLocacao(locacao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/atualizaautomovel/{id}/estado/{status}")
    public ResponseEntity<?> atualizaAutomovel(@PathVariable int id, @PathVariable boolean status) {
        Carro carro = repository.getCarroById(id);
        if (carro != null) {
            carro.setDisponivel(status);
            return ResponseEntity.ok(carro);
        } else {
            ErroResponse erro = new ErroResponse("Automóvel não encontrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @PostMapping("/atendimento/finalizalocacao")
    public boolean finalizaLocacao(@RequestBody IdRequest id) {
        Locacao loc = repository.getLocacaoById(id.getId());
        if (loc == null) {
            return false;
        }
        loc.setFinalizado(true);
        return true;
    }
}
