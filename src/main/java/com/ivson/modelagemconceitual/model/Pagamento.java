package com.ivson.modelagemconceitual.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ivson.modelagemconceitual.model.enuns.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)	// para superclasses
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type") // a classe paramento tem um campo adicional '@type'
public abstract class Pagamento implements Serializable {
	
	/**
	 * Versao numero 1 dessa classe
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private Integer estado;
	
	// dessa forma o Pagamento nao tem autoincremento de ID, mas usa o mesmo do PEDIDO
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	@JsonIgnore
	private Pedido pedido;
	
	public Pagamento() {

	}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = (estado == null) ? null : estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
