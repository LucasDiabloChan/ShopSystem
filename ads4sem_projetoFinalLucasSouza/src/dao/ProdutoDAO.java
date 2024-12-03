package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Blob;
import helpers.DatabaseConnection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.Produto;

/**
 * @author Workstation
 */
public class ProdutoDAO {
    Connection conn = null;
    
    public void cadastrarSimplificado(Produto prod) {
        try{
            conn = DatabaseConnection.obterConexao();
            String query = "INSERT INTO produto(pdt_status, pdt_nome, pdt_precoCompra, pdt_precoVenda, pdt_fator, pdt_dataCadastro) VALUES(?, ?, ?, ?, ?, ?);";
            
            PreparedStatement pst = conn.prepareStatement(query);
            
            pst.setString(1, String.valueOf(prod.getPdtStatus()));
            pst.setString(2, prod.getPdtNome());
            pst.setBigDecimal(3, prod.getPdtprecoCompra());
            pst.setBigDecimal(4, prod.getPdtprecoVenda());
            pst.setBigDecimal(5, prod.getPdtFator());
            pst.setDate(6, prod.getPdtdataCadastro());
            
            pst.execute();
            pst.close();
            conn.close();
            //DatabaseConnection.fecharConexao();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    
    public void cadastrarCompleto(Produto prod){
        try{
            conn = DatabaseConnection.obterConexao();
            String query = "INSERT INTO produto VALUES( "
                            .concat("?, ?, ?, ?, ?, ?, ? ") //1-7
                            .concat("?, ?, ?, ?, ?, ?, ?") //7-14
                            .concat(");  "); // fim insert
            
            PreparedStatement pst = conn.prepareStatement(query);
            
            pst.setString(0, query);
            pst.setString(1, String.valueOf(prod.getPdtStatus()));
            pst.setString(2, prod.getPdtNome());
            pst.setBigDecimal(3, prod.getPdtprecoCompra());
            pst.setBigDecimal(4, prod.getPdtprecoVenda());
            pst.setBigDecimal(5, prod.getPdtFator());
            pst.setDate(6, prod.getPdtdataCadastro());
            
            pst.execute();
            pst.close();
            conn.close();
            //DatabaseConnection.fecharConexao();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    
    public List<Produto> listar() {
        conn = DatabaseConnection.obterConexao();
        List<Produto> produtos = new ArrayList<>();
        
        try{
            String query = "SELECT pdt_id, pdt_status, pdt_nome, pdt_precoCompra, pdt_precoVenda, pdt_fator, pdt_dataCadastro FROM produto LIMIT 50;";
            
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Produto dbProd = new Produto();
                dbProd.setPdtId(rs.getInt("pdt_id"));
                dbProd.setPdtStatus(rs.getString("pdt_status").charAt(0));
                dbProd.setPdtNome(rs.getString("pdt_nome"));
                dbProd.setPdtprecoCompra(rs.getBigDecimal("pdt_precoCompra"));
                dbProd.setPdtprecoVenda(rs.getBigDecimal("pdt_precoVenda"));
                dbProd.setPdtFator(rs.getBigDecimal("pdt_fator"));
                dbProd.setPdtdataCadastro(rs.getDate("pdt_dataCadastro"));
                produtos.add(dbProd);
            }
            pst.close();
            conn.close();
        }catch(SQLException ex){
            ex.getMessage();
        }
        return produtos;
    }
 
    public void alterar(Produto prod){
        conn = DatabaseConnection.obterConexao();
        
        try{
            String query = "UPDATE produto SET "
                            .concat("pdt_cod = ?, ") //1
                            .concat("pdt_status = ?, ") //2
                            .concat("pdt_nome = ?, ") //3
                            .concat("pdt_descricao = ?, ") //4
                            .concat("pdt_qtdEstoque = ?, ") //5
                            .concat("pdt_estoqueMinimo = ?, ") // 6
                            .concat("pdt_estoqueMaximo = ?, ") // 7
                            .concat("pdt_precoCompra = ?, ") // 8
                            .concat("pdt_precoVenda = ?, ") // 9
                            .concat("pdt_barCode = ?, ") // 10
                            .concat("pdt_ncm = ?, ") // 11
                            .concat("pdt_fator = ?, ") // 12
                            .concat("pdt_dataCadastro = ?, ") // 13
                            .concat("pdt_imagem = ? ") // 14
                            .concat("WHERE pdt_id = ?;"); // 15
            
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, prod.getPdtCod());
            pst.setString(2, String.valueOf(prod.getPdtStatus()));
            pst.setString(3, prod.getPdtNome());
            pst.setString(4, prod.getPdtDescricao());
            pst.setInt(5, prod.getPdtqtdEstoque());
            pst.setInt(6, prod.getPdtestoqueMinimo());
            pst.setInt(7, prod.getPdtestoqueMaximo());
            pst.setBigDecimal(8, prod.getPdtprecoCompra());
            pst.setBigDecimal(9, prod.getPdtprecoVenda());
            pst.setString(10, String.valueOf(prod.getPdtbarCode()));
            pst.setString(11, prod.getPdtNcm());
            pst.setBigDecimal(12, prod.getPdtFator());
            pst.setDate(13, prod.getPdtdataCadastro());
            pst.setBlob(14, prod.getPdtBinarioImagem(), prod.getPdtTamImagem());
            pst.setInt(15, prod.getPdtId());
            
            pst.execute();
            
            pst.close();
            conn.close();
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
    
    public void excluir(int id){
        conn = DatabaseConnection.obterConexao();
        
        try{
            String query = "DELETE FROM produto WHERE pdt_id = ?;";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            
            pst.execute();
            
            pst.close();
            conn.close();
        }catch(SQLException ex){
            ex.getMessage();
        }
        
    }

    public Produto buscarPorId(int id) {
        conn = DatabaseConnection.obterConexao();
        Produto p = new Produto();
        
        try{
            String query = "SELECT * FROM produto WHERE pdt_id = ?;";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){                
                p.setPdtId(rs.getInt("pdt_id"));
                p.setPdtId(rs.getInt("pdt_cod"));
                p.setPdtStatus(rs.getString("pdt_status").charAt(0));
                p.setPdtNome(rs.getString("pdt_nome"));
                p.setPdtDescricao(rs.getString("pdt_descricao"));
                p.setPdtqtdEstoque(rs.getInt("pdt_qtdEstoque"));
                p.setPdtestoqueMinimo(rs.getInt("pdt_estoqueMinimo"));
                p.setPdtestoqueMaximo(rs.getInt("pdt_estoqueMaximo"));
                p.setPdtprecoCompra(rs.getBigDecimal("pdt_precoCompra"));
                p.setPdtprecoVenda(rs.getBigDecimal("pdt_precoVenda"));
                p.setPdtNcm(rs.getString("pdt_ncm"));
                p.setPdtFator(rs.getBigDecimal("pdt_fator"));
                p.setPdtdataCadastro(rs.getDate("pdt_dataCadastro"));
                
                Blob blob = rs.getBlob("pdt_imagem");
                if(blob != null){
                    byte[] img = blob.getBytes(1, (int) blob.length());
                    BufferedImage finalImg = null;

                    try{
                        finalImg = ImageIO.read(new ByteArrayInputStream(img));
                    } catch (IOException ex) {
                        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    p.setPdtImagemConvertida(new ImageIcon(finalImg));
                }else{
                    p.setPdtImagemConvertida(new ImageIcon());
                }
            }
            
            rs.close();
            pst.close();
            conn.close();
            return p;
        }catch(SQLException ex){
            ex.getMessage();
        }
        return p;
    }
}
