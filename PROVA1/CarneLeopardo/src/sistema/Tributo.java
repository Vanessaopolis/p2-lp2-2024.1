package sistema;

import java.util.Objects;

public class Tributo {
	private int codigo;
	private String descricao;
	private double valor;
	private int ano;
	private boolean pago;
	
	public Tributo(int codigoTriuto, String descricao, double valor, int ano) {
		this.codigo = codigoTriuto;
		this.descricao = descricao;
		this.valor = valor;
		this.ano = ano;
		this.pago = false;
	}
	
	public Tributo(Tributo tributo) {
		this.codigo = tributo.codigo;
		this.descricao = tributo.descricao;
		this.valor = tributo.valor;
		this.ano = tributo.ano;
		this.pago = tributo.pago;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public double getValor() {
		return valor;
	}
	
	public int getAno() {
		return ano;
	}
	
	public boolean foiPago() {
		return pago;
	}
	
	public double reajustarTributo(int ano, double percentual) {
		if (percentual > -1 && ano > this.ano) {
			valor = valor * (1 + percentual);
			this.ano = ano;
			return valor;
		}
		return 0;
	}
	
	public void pagarTributo() {
		pago = true;
	}
	
	public String situacaoPagamento() {
		String situacaoPagamento = "NÃO";
		if (pago) {
			situacaoPagamento = "SIM";
		}
		
		return "|" + String.format("%7s", situacaoPagamento).replaceAll("^(.*\\S)\\s+", "$1") + "| " + toString();
	}
	
	@Override
	public String toString() {
		return "| Tributo: " + codigo + " - " + descricao + " - Valor: R$" + String.format("%.2f", valor) + " - Ano Base: " + ano + " |";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tributo)) {
			return false;
		}
		Tributo other = (Tributo) obj;
		return codigo == other.codigo;
	}

}
