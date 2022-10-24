package br.com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;



public class ItemPedidoDTO {

	private Integer idItemPedido;
	private Integer quantidade;
	private BigDecimal precoVenda;
	private Integer percentualDesconto;
	private BigDecimal valorBruto;	
	private BigDecimal valorLiquido;
	
	//private PedidoDTO PedidosDTO;
	//private ProdutoDTO ProdutosDTO;
	private List<ProdutoDTO> listaProdutosDTO;
	private List<PedidoDTO> listaPedidoDTO;
	
	
	public Integer getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Integer getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(Integer percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public BigDecimal getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	
	public List<ProdutoDTO> getListaProdutosDTO() {
		return listaProdutosDTO;
	}
	public void setListaProdutosDTO(List<ProdutoDTO> listaProdutosDTO) {
		this.listaProdutosDTO = listaProdutosDTO;
	}
	public List<PedidoDTO> getListaPedidoDTO() {
		return listaPedidoDTO;
	}
	public void setListaPedidoDTO(List<PedidoDTO> listaPedidoDTO) {
		this.listaPedidoDTO = listaPedidoDTO;
	}
/*
	public ProdutoDTO getProdutosDTO() {
		return ProdutosDTO;
	}
	public void setProdutosDTO(ProdutoDTO produtosDTO) {
		ProdutosDTO = produtosDTO;
	}
	public PedidoDTO getPedidosDTO() {
		return PedidosDTO;
	}
	public void setPedidosDTO(PedidoDTO pedidosDTO) {
		PedidosDTO = pedidosDTO;
	}
	
	*/
	
}
