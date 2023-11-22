package Model;

public class Preco_Data {
    private String data;
    private double preco;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Preco_Data(String data, double preco) {
        this.data = data;
        this.preco = preco;
    }
}


