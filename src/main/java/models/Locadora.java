package models;
import java.util.ArrayList;
import java.util.List;

public class Locadora {
    public List<Locacao> locacoes;
    public List<Carro> carros;
    public List<Cliente> clientes;
    
    public Locadora() {
        this.locacoes = new ArrayList<>();
        this.carros = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void addLocacao(Locacao locacao) {
        locacao.getCarro().setDisponivel(false);
        this.locacoes.add(locacao);
    }

    public void removeLocacao(Locacao locacao) {
        this.locacoes.remove(locacao);
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void addCarro(Carro carro) {
        this.carros.add(carro);
    }

    public void removeCarro(Carro carro) {
        this.carros.remove(carro);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removeCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public Carro getCarroById(int id) {
        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro;
            }
        }
        return null;
    }

    public Cliente getClienteById(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public Locacao getLocacaoById(int id) {
        for (Locacao locacao : locacoes) {
            if (locacao.getId() == id) {
                return locacao;
            }
        }
        return null;
    }
}
