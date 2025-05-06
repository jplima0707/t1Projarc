package com.example;

import models.Locadora;
import models.Carro;
import models.Cliente;
import models.Locacao;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LocadoraRepository {
    private final Locadora locadora;

    public LocadoraRepository() {
        locadora = new Locadora();
        initData();
    }

    private void initData() {
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
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public List<Carro> getAllCarros() {
        return this.locadora.getCarros();
    }

    public List<Cliente> getAllClientes() {
        return this.locadora.getClientes();
    }

    public List<Locacao> getAllLocacoes() {
        return this.locadora.getLocacoes();
    }

    public Cliente getClienteById(int id) {
        return this.locadora.getClienteById(id);
    }

    public Carro getCarroById(int id) {
        return this.locadora.getCarroById(id);
    }

    public void addLocacao(Locacao locacao) {
        this.locadora.addLocacao(locacao);
    }

    public Locacao getLocacaoById(int id) {
        return this.locadora.getLocacaoById(id);
    }
}