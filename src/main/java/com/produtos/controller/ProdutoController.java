package com.produtos.controller;

import com.produtos.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class ProdutoController {
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;
    @FXML private TextField txtQuantidade;
    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, Integer> colId;
    @FXML private TableColumn<Produto, String> colNome;
    @FXML private TableColumn<Produto, Double> colPreco;
    @FXML private TableColumn<Produto, Integer> colQuantidade;

    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();
    private int proximoId = 1;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        colPreco.setCellValueFactory(cellData -> cellData.getValue().precoProperty().asObject());
        colQuantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty().asObject());
        tabelaProdutos.setItems(listaProdutos);
        tabelaProdutos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> preencherCampos(newSel));
    }

    private void preencherCampos(Produto produto) {
        if (produto != null) {
            txtId.setText(String.valueOf(produto.getId()));
            txtNome.setText(produto.getNome());
            txtPreco.setText(String.valueOf(produto.getPreco()));
            txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
        } else {
            txtId.clear();
            txtNome.clear();
            txtPreco.clear();
            txtQuantidade.clear();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void adicionarProduto(ActionEvent event) {
        String nome = txtNome.getText();
        String precoStr = txtPreco.getText();
        String quantidadeStr = txtQuantidade.getText();
        if (nome.isEmpty() || precoStr.isEmpty() || quantidadeStr.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Preencha todos os campos para adicionar um produto.");
            return;
        }
        double preco;
        int quantidade;
        try {
            preco = Double.parseDouble(precoStr);
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Valor inválido", "Preço e quantidade devem ser numéricos.");
            return;
        }
        Produto produto = new Produto(proximoId++, nome, preco, quantidade);
        listaProdutos.add(produto);
        preencherCampos(null);
    }

    @FXML
    public void atualizarProduto(ActionEvent event) {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Nenhum produto selecionado", "Selecione um produto para atualizar.");
            return;
        }
        String nome = txtNome.getText();
        String precoStr = txtPreco.getText();
        String quantidadeStr = txtQuantidade.getText();
        if (nome.isEmpty() || precoStr.isEmpty() || quantidadeStr.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Preencha todos os campos para atualizar o produto.");
            return;
        }
        try {
            selecionado.setNome(nome);
            selecionado.setPreco(Double.parseDouble(precoStr));
            selecionado.setQuantidade(Integer.parseInt(quantidadeStr));
            tabelaProdutos.refresh();
        } catch (NumberFormatException e) {
            mostrarAlerta("Valor inválido", "Preço e quantidade devem ser numéricos.");
        }
    }

    @FXML
    public void excluirProduto(ActionEvent event) {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta("Nenhum produto selecionado", "Selecione um produto para excluir.");
            return;
        }
        listaProdutos.remove(selecionado);
        preencherCampos(null);
    }

    @FXML
    private void exportarParaCSV(ActionEvent event) {
        try (FileWriter writer = new FileWriter("produtos.csv")) {
            writer.write("ID,Nome,Preco,Quantidade\n");
            for (Produto p : listaProdutos) {
                writer.write(p.getId() + "," + p.getNome() + "," + p.getPreco() + "," + p.getQuantidade() + "\n");
            }
            mostrarAlerta("Exportação Concluída", "Produtos exportados para produtos.csv com sucesso!");
        } catch (IOException e) {
            mostrarAlerta("Erro ao Exportar", "Não foi possível exportar para CSV: " + e.getMessage());
        }
    }
}
