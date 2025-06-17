package com.produtos.model;

import javafx.beans.property.*;

public class Produto {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final DoubleProperty preco = new SimpleDoubleProperty();
    private final IntegerProperty quantidade = new SimpleIntegerProperty();

    public Produto() {}
    public Produto(int id, String nome, double preco, int quantidade) {
        this.id.set(id);
        this.nome.set(nome);
        this.preco.set(preco);
        this.quantidade.set(quantidade);
    }

    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getNome() { return nome.get(); }
    public void setNome(String nome) { this.nome.set(nome); }
    public StringProperty nomeProperty() { return nome; }

    public double getPreco() { return preco.get(); }
    public void setPreco(double preco) { this.preco.set(preco); }
    public DoubleProperty precoProperty() { return preco; }

    public int getQuantidade() { return quantidade.get(); }
    public void setQuantidade(int quantidade) { this.quantidade.set(quantidade); }
    public IntegerProperty quantidadeProperty() { return quantidade; }
}
