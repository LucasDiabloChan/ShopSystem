/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProdutoDAO;
import java.io.FileInputStream;
import java.sql.Blob;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import model.Produto;

/**
 *
 * @author Workstation
 */
public class ProdutoController {
    private ProdutoDAO pd;
    
    public ProdutoController(){
        pd = new ProdutoDAO();
    }
    
    public void cadastrar(String cod, char status, String nome, 
            double precoCompra, double precoVenda,
            double fator, java.sql.Date dataCadastro){
        Produto prod = new Produto();
        prod.setPdtCod(cod);
        prod.setPdtStatus(status);
        prod.setPdtNome(nome);
        prod.setPdtprecoCompra(BigDecimal.valueOf(precoCompra));
        prod.setPdtprecoVenda(BigDecimal.valueOf(precoVenda));
        prod.setPdtFator(BigDecimal.valueOf(fator));
        prod.setPdtdataCadastro(dataCadastro);
        
        pd.cadastrar(prod);
    }
    
    public List<Produto> listar(){
        return pd.listar();
    }
    
    public void alterar(String codigo, 
            char status, 
            String nome,
            String descricao, 
            int qtdEstoque, 
            int estoqueMin,
            int estoqueMax, 
            double precoCompra, 
            double precoVenda,
            BigInteger barcode, 
            String ncm, 
            double fator, 
            java.sql.Date dataCadastro, 
            FileInputStream foto, 
            long tamanho){
        Produto prod = new Produto();
        prod.setPdtCod(codigo);
        prod.setPdtStatus(status);
        prod.setPdtNome(nome);
        prod.setPdtDescricao(descricao);
        prod.setPdtqtdEstoque(qtdEstoque);
        prod.setPdtestoqueMinimo(estoqueMin);
        prod.setPdtestoqueMaximo(estoqueMax);
        prod.setPdtprecoCompra(BigDecimal.valueOf(precoCompra));
        prod.setPdtprecoVenda(BigDecimal.valueOf(precoVenda));
        prod.setPdtbarCode(barcode);
        prod.setPdtNcm(ncm);
        prod.setPdtFator(BigDecimal.valueOf(fator));
        prod.setPdtdataCadastro(dataCadastro);
        prod.setPdtBinarioImagem(foto);
        prod.setPdtTamImagem(tamanho);
        pd.alterar(prod);
    }
    
    public void excluir(int codigo){
        pd.excluir(codigo);
    }

    public Produto buscarPorId(int id) {
        return pd.buscarPorId(id);
    }
}
