package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contribuinte {
	
	private String cpf;
	private String nome;
	private String contato;
	private List<Tributo> tributosIndividuais;
	
	public Contribuinte(String cpf, String nome, String contato) {
		this.cpf = cpf;
		this.nome = nome;
		this.contato = contato;
		this.tributosIndividuais = new ArrayList<>();
	}
	
	public boolean atribuirTributo(Tributo tributo) {
		if (!tributosIndividuais.contains(tributo)) {
			tributosIndividuais.add(tributo);
		}
		return false;
	}
	
	public boolean pagarTributo(int codigoTributo) {
		Tributo tributoProcurado = buscarTributo(codigoTributo);
		if (tributoProcurado != null) {
			tributoProcurado.pagarTributo();
			return true;
		}
		return false;
	}
	
	public Tributo buscarTributo(int codigoTributo) {
		for (Tributo tributo : tributosIndividuais) {
			if (tributo != null && tributo.getCodigo() == codigoTributo) {
				return tributo;				
			}
		}
		return null;
	}
	
	public String[] gerarExtrato() {
		if (tributosIndividuais.size() == 0) {
			return new String[0];
		}
		
		String[] extrato = new String[tributosIndividuais.size()];
		int i = 0;
		for (Tributo tributo : tributosIndividuais) {
			extrato[i++] = tributo.situacaoPagamento();
		}
		return extrato;
	}
	
	public double totalPagoEmTributos(int ano) {
		double soma = 0;
		for (Tributo tributo : tributosIndividuais) {
			if (tributo.foiPago() && tributo.getAno() == ano) {
				soma += tributo.getValor();
			}
		}
		
		return soma;
	}
	
	@Override
 	public String toString() {
		return "| Contribuinte: " + nome + " - CPF: " + cpf + " - Contato: " + contato + " |";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Contribuinte)) {
			return false;
		}
		Contribuinte other = (Contribuinte) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	

}
