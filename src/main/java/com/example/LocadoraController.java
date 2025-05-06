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
import requests.CadLocacaoRequest;
import requests.IdRequest;
import responses.ErroResponse;

@RestController
@RequestMapping("/acmerent")
public class LocadoraController {
    private Locadora locadora;
    
    @GetMapping("/init")
    public String init() {
        locadora = new Locadora();
        locadora.addCliente(new Cliente("Lucas", "12345678900", "1234567890"));
        locadora.addCliente(new Cliente("Maria", "98765432100", "0987654321"));
        locadora.addCliente(new Cliente("João", "11122233344", "1122334455"));
        locadora.addCarro(new Carro(1990, "IXP2029", 50.0));
        locadora.addCarro(new Carro(2000, "ABC1234", 60.0));
        locadora.addCarro(new Carro(2010, "XYZ5678", 70.0));
        locadora.addCarro(new Carro(2020, "LMN9012", 80.0));
        locadora.addCarro(new Carro(2023, "QRS3456", 90.0));
        locadora.addCarro(new Carro(2024, "TUV7890", 100.0));
        locadora.addCarro(new Carro(2025, "WXY1234", 110.0));
        locadora.addCarro(new Carro(2026, "ZAB5678", 120.0));
        locadora.addCarro(new Carro(2027, "CDE9012", 130.0));
        locadora.addCarro(new Carro(2028, "FGH3456", 140.0));
        locadora.addLocacao(new Locacao("2023-10-01", 5, locadora.getCarroById(0), locadora.getClienteById(0)));
        locadora.addLocacao(new Locacao("2023-10-03", 10, locadora.getCarroById(5), locadora.getClienteById(2)));
        return "Locadora inicializada com sucesso!";
    }

    @GetMapping("/listaautomoveis")
    public List<Carro> getAutomoveis() {
        return locadora.getCarros();
        
    }

    @GetMapping("/listaclientes")
    public List<Cliente> getClientes() {
        return locadora.getClientes();
    }

    @GetMapping("/listalocacoes")
    public List<Locacao> getLocacoes() {
        return locadora.getLocacoes();
    }

    @GetMapping("/consultacliente")
    public ResponseEntity<?> consultaCliente(@RequestParam int id) {
        Cliente cliente = locadora.getClienteById(id);
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
    public boolean cadastrarLocacao(@RequestBody CadLocacaoRequest locacao) {
        try {
            Carro carro = locadora.getCarroById(locacao.getIdCarro());
            Cliente cliente = locadora.getClienteById(locacao.getIdCliente());
            if (carro == null || cliente == null) {
                return false;
            }
            if (carro.isDisponivel() == false) {
                return false;
            }
            Locacao novaLocacao = new Locacao(locacao.getDataInicio(), locacao.getQntdDias(), carro, cliente);
            carro.setDisponivel(false);
            locadora.addLocacao(novaLocacao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/atualizaautomovel/{id}/estado/{status}")
    public ResponseEntity<?> atualizaAutomovel(@PathVariable int id, @PathVariable boolean status) {
        Carro carro = locadora.getCarroById(id);
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
        Locacao loc = locadora.getLocacaoById(id.getId());
        if (loc == null) {
            return false;
        }
        loc.setFinalizado(true);
        return true;
    }
}
