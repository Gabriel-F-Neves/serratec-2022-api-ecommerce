package br.com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class PedidoDTO {

	private Integer idPedidoDTO;
	private Instant datapedido;
	private BigDecimal valorTotal;
	
	public Integer getIdPedido() {
		return idPedidoDTO;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedidoDTO = idPedido;
	}
	public Instant getDatapedido() {
		return datapedido;
	}
	public void setDatapedido(Instant datapedido) {
		this.datapedido = datapedido;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	
}
