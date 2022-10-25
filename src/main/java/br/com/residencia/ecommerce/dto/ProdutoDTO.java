package br.com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProdutoDTO {

	private Integer idProduto;
	private String nomeProduto;
	private String descricao;
	private Integer qtdEstoque;
	private Date dataCadastro;
	private BigDecimal valorUnitario;
	private String imagemFileName;
	private String imagemUrl;
	private String ImagemNome;
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getImagemFileName() {
		return imagemFileName;
	}
	public void setImagemFileName(String imagemFileName) {
		this.imagemFileName = imagemFileName;
	}
	public String getImagemUrl() {
		return imagemUrl;
	}
	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	public String getImagemNome() {
		return ImagemNome;
	}
	public void setImagemNome(String imagemNome) {
		ImagemNome = imagemNome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	@Override
	public String toString() {
		return "ProdutoDTO [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto
				+ "]";
		
//		return "ProdutoDTO [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", listaLivrosDTO=" + listaLivrosDTO
//				+ "]";
	}
	
	
}
