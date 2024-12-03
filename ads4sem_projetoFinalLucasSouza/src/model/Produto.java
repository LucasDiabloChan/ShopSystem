/*
 * Click nbfs://nbhost/SystemBlobSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemBlobSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.io.FileInputStream;
import java.sql.Blob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.ImageIcon;

/**
 * @Descricao Classe para representar a entidade "produtos"
 * @author Workstation
 */
@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByPdtId", query = "SELECT p FROM Produto p WHERE p.pdtId = :pdtId"),
    @NamedQuery(name = "Produto.findByPdtCod", query = "SELECT p FROM Produto p WHERE p.pdtCod = :pdtCod"),
    @NamedQuery(name = "Produto.findByPdtStatus", query = "SELECT p FROM Produto p WHERE p.pdtStatus = :pdtStatus"),
    @NamedQuery(name = "Produto.findByPdtNome", query = "SELECT p FROM Produto p WHERE p.pdtNome = :pdtNome"),
    @NamedQuery(name = "Produto.findByPdtqtdEstoque", query = "SELECT p FROM Produto p WHERE p.pdtqtdEstoque = :pdtqtdEstoque"),
    @NamedQuery(name = "Produto.findByPdtestoqueMinimo", query = "SELECT p FROM Produto p WHERE p.pdtestoqueMinimo = :pdtestoqueMinimo"),
    @NamedQuery(name = "Produto.findByPdtestoqueMaximo", query = "SELECT p FROM Produto p WHERE p.pdtestoqueMaximo = :pdtestoqueMaximo"),
    @NamedQuery(name = "Produto.findByPdtprecoCompra", query = "SELECT p FROM Produto p WHERE p.pdtprecoCompra = :pdtprecoCompra"),
    @NamedQuery(name = "Produto.findByPdtprecoVenda", query = "SELECT p FROM Produto p WHERE p.pdtprecoVenda = :pdtprecoVenda"),
    @NamedQuery(name = "Produto.findByPdtbarCode", query = "SELECT p FROM Produto p WHERE p.pdtbarCode = :pdtbarCode"),
    @NamedQuery(name = "Produto.findByPdtNcm", query = "SELECT p FROM Produto p WHERE p.pdtNcm = :pdtNcm"),
    @NamedQuery(name = "Produto.findByPdtFator", query = "SELECT p FROM Produto p WHERE p.pdtFator = :pdtFator"),
    @NamedQuery(name = "Produto.findByPdtdataCadastro", query = "SELECT p FROM Produto p WHERE p.pdtdataCadastro = :pdtdataCadastro"),
    @NamedQuery(name = "Produto.findByPdtImagem", query = "SELECT p FROM Produto p WHERE p.pdtImagem = :pdtImagem")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdt_id")
    private Integer pdtId;
    @Column(name = "pdt_cod")
    private String pdtCod;
    @Basic(optional = false)
    @Column(name = "pdt_status")
    private Character pdtStatus;
    @Basic(optional = false)
    @Column(name = "pdt_nome")
    private String pdtNome;
    @Lob
    @Column(name = "pdt_descricao")
    private String pdtDescricao;
    @Column(name = "pdt_qtdEstoque")
    private Integer pdtqtdEstoque;
    @Column(name = "pdt_estoqueMinimo")
    private Integer pdtestoqueMinimo;
    @Column(name = "pdt_estoqueMaximo")
    private Integer pdtestoqueMaximo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "pdt_precoCompra")
    private BigDecimal pdtprecoCompra;
    @Basic(optional = false)
    @Column(name = "pdt_precoVenda")
    private BigDecimal pdtprecoVenda;
    @Column(name = "pdt_barCode")
    private BigInteger pdtbarCode;
    @Column(name = "pdt_ncm")
    private String pdtNcm;
    @Basic(optional = false)
    @Column(name = "pdt_fator")
    private BigDecimal pdtFator;
    @Basic(optional = false)
    @Column(name = "pdt_dataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdtdataCadastro;
    @Column(name = "pdt_imagem")
    private FileInputStream pdtBinarioImagem;
    private long pdtTamImagem;
    private ImageIcon pdtImagemConvertida;

    

    public Produto() {
    }

    public Produto(Integer pdtId) {
        this.pdtId = pdtId;
    }
    
    public Produto(Integer pdtId, Character pdtStatus, String pdtNome, BigDecimal pdtprecoCompra, BigDecimal pdtprecoVenda, BigDecimal pdtFator, Date pdtdataCadastro) {
        this.pdtId = pdtId;
        this.pdtStatus = pdtStatus;
        this.pdtNome = pdtNome;
        this.pdtprecoCompra = pdtprecoCompra;
        this.pdtprecoVenda = pdtprecoVenda;
        this.pdtFator = pdtFator;
        this.pdtdataCadastro = pdtdataCadastro;
    }

    public FileInputStream getPdtBinarioImagem() {
        return pdtBinarioImagem;
    }

    public void setPdtBinarioImagem(FileInputStream pdtBinarioImagem) {
        this.pdtBinarioImagem = pdtBinarioImagem;
    }

    public long getPdtTamImagem() {
        return pdtTamImagem;
    }

    public void setPdtTamImagem(long pdtTamImagem) {
        this.pdtTamImagem = pdtTamImagem;
    }

    public ImageIcon getPdtImagemConvertida() {
        return pdtImagemConvertida;
    }

    public void setPdtImagemConvertida(ImageIcon pdtImagemConvertida) {
        this.pdtImagemConvertida = pdtImagemConvertida;
    }
    
    

    public Integer getPdtId() {
        return pdtId;
    }

    public void setPdtId(Integer pdtId) {
        this.pdtId = pdtId;
    }

    public String getPdtCod() {
        return pdtCod;
    }

    public void setPdtCod(String pdtCod) {
        this.pdtCod = pdtCod;
    }

    public Character getPdtStatus() {
        return pdtStatus;
    }

    public void setPdtStatus(Character pdtStatus) {
        this.pdtStatus = pdtStatus;
    }

    public String getPdtNome() {
        return pdtNome;
    }

    public void setPdtNome(String pdtNome) {
        this.pdtNome = pdtNome;
    }

    public String getPdtDescricao() {
        return pdtDescricao;
    }

    public void setPdtDescricao(String pdtDescricao) {
        this.pdtDescricao = pdtDescricao;
    }

    public Integer getPdtqtdEstoque() {
        return pdtqtdEstoque;
    }

    public void setPdtqtdEstoque(Integer pdtqtdEstoque) {
        this.pdtqtdEstoque = pdtqtdEstoque;
    }

    public Integer getPdtestoqueMinimo() {
        return pdtestoqueMinimo;
    }

    public void setPdtestoqueMinimo(Integer pdtestoqueMinimo) {
        this.pdtestoqueMinimo = pdtestoqueMinimo;
    }

    public Integer getPdtestoqueMaximo() {
        return pdtestoqueMaximo;
    }

    public void setPdtestoqueMaximo(Integer pdtestoqueMaximo) {
        this.pdtestoqueMaximo = pdtestoqueMaximo;
    }

    public BigDecimal getPdtprecoCompra() {
        return pdtprecoCompra;
    }

    public void setPdtprecoCompra(BigDecimal pdtprecoCompra) {
        this.pdtprecoCompra = pdtprecoCompra;
    }

    public BigDecimal getPdtprecoVenda() {
        return pdtprecoVenda;
    }

    public void setPdtprecoVenda(BigDecimal pdtprecoVenda) {
        this.pdtprecoVenda = pdtprecoVenda;
    }

    public BigInteger getPdtbarCode() {
        return pdtbarCode;
    }

    public void setPdtbarCode(BigInteger pdtbarCode) {
        this.pdtbarCode = pdtbarCode;
    }

    public String getPdtNcm() {
        return pdtNcm;
    }

    public void setPdtNcm(String pdtNcm) {
        this.pdtNcm = pdtNcm;
    }

    public BigDecimal getPdtFator() {
        return pdtFator;
    }

    public void setPdtFator(BigDecimal pdtFator) {
        this.pdtFator = pdtFator;
    }

    public Date getPdtdataCadastro() {
        return pdtdataCadastro;
    }

    public void setPdtdataCadastro(Date pdtdataCadastro) {
        this.pdtdataCadastro = pdtdataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdtId != null ? pdtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.pdtId == null && other.pdtId != null) || (this.pdtId != null && !this.pdtId.equals(other.pdtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Produto[ pdtId=" + pdtId + " ]";
    }
    
}
