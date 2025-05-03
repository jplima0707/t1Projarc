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
import org.springframework.web.bind.annotation.PostMapping;
import models.Locadora;
import models.Carro;
import models.Cliente;
import models.Locacao;
import responses.ErroResponse;

@RestController
@RequestMapping("/acmerent")
public class LocadoraController {
    private Locadora locadora = new Locadora();
    
    @GetMapping("/init")
    public String init() {
        locadora.addCliente(new Cliente("Lucas", "12345678900", "1234567890"));
        locadora.addCarro(new Carro(1990, "IXP2029", 50.0));
        locadora.addCarro(new Carro(2000, "ABC1234", 60.0));
        locadora.addCarro(new Carro(2010, "XYZ5678", 70.0));
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


}
