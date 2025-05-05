package models;
public class Locacao {
    
    private String dataInicio;
    private int qntdDias;
    private double valorTotal;
    private Carro carro;
    private Cliente cliente;
    private boolean finalizado;
    private int id;
    private static int ProxId = 0;

    public Locacao(String dataInicio, int qntdDias, Carro carro, Cliente cliente) {
        this.dataInicio = dataInicio;
        this.qntdDias = qntdDias;
        this.carro = carro;
        this.cliente = cliente;
        this.valorTotal = calcularValorTotal();
        this.finalizado = false;
        this.id = ProxId;
        ProxId++;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDias() {
        return qntdDias;
    }

    public void setDias(int dias) {
        this.qntdDias = dias;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public double calcularValorTotal() {
        double valor = carro.getValorDiaria() * qntdDias;
        if (qntdDias > 7) {
            valor *= 0.95; 
        }
        return valor;
    }
}
