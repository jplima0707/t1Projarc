package models;
public class Carro {
    private int ano;
    private String placa;
    private double valorDiaria;
    private boolean disponivel;
    private int id;
    private static int ProxId = 0;
    
    public Carro(int ano, String placa, double valorDiaria) {
        this.ano = ano;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
        this.id = ProxId;
        ProxId++;

    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }
}
