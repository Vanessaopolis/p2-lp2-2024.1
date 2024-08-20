package sistema;

import java.util.HashMap;
import java.util.Map;

public class CarneLeopardo {
	
	private Map<String, Contribuinte> contribuintes;
	private Map<Integer, Tributo> tributos;
	
	public CarneLeopardo() {
		this.contribuintes = new HashMap<>();
		this.tributos = new HashMap<>();
	}
	
	private final int QUANTIDADE_MAXIMA_CONTRIBUINTES = 100;
	private final int QUANTIDADE_MAXIMA_TRIBUTOS = 60;
	
	public String cadastrarContribuinte(String cpf, String nome, String contato) {
		if (contribuintes.size() >= QUANTIDADE_MAXIMA_CONTRIBUINTES) {
			throw new IndexOutOfBoundsException("O cadastro de contribuintes já está cheio!");
		}
		if (contribuintes.containsKey(cpf)) {
			throw new IllegalArgumentException("Contribuinte já cadastrado!");
		}
		
		contribuintes.put(cpf, new Contribuinte(cpf, nome, contato));
		return cpf;
	}
	
	public String[] listarContribuintes() {
		if (contribuintes.size() == 0) {
			return new String[0];
		}
		String[] listaDeContribuintes = new String[contribuintes.size()];
		int i = 0;
		for (Contribuinte contribuinte : contribuintes.values()) {
			listaDeContribuintes[i++] = contribuinte.toString();
		}
		return listaDeContribuintes;
	}
	
	public int cadastrarTributo(int codigoTributo, String descricao, double valor, int ano) {
		if (codigoTributo < 1 || codigoTributo > QUANTIDADE_MAXIMA_TRIBUTOS) {
			throw new IndexOutOfBoundsException("A faixa disponível para códigos tributários é de 1 a 60!");	
		}
		if (tributos.containsKey(codigoTributo)) {
			throw new IllegalArgumentException("O código já está sendo utilizado por outro tributo!");
		}
		
		tributos.put(codigoTributo, new Tributo(codigoTributo, descricao, valor, ano));
		return codigoTributo;
	}
	
	public String[] listarTributos() {
		if (tributos.size() == 0) {
			return new String[0];
		}
		String[] listaDeTributos = new String[tributos.size()];
		int i = 0;
		for (Tributo tributo : tributos.values()) {
			if (tributo != null) {
				listaDeTributos[i++] = tributo.toString();
			}
		}
		return listaDeTributos;
	}
	
	public double reajustarTributo(int codigoTributo, int ano, double percentual) {
		if (tributos.containsKey(codigoTributo)) {
			return tributos.get(codigoTributo).reajustarTributo(ano, percentual);
		}
		return 0;
	}
	
	public String atribuirTributoAoContribuinte(int codigoTributo, String cpf) {
		if (tributos.containsKey(codigoTributo) && contribuintes.containsKey(cpf)) {
			Tributo tributo = tributos.get(codigoTributo);
			contribuintes.get(cpf).atribuirTributo(tributo);
			tributos.remove(codigoTributo);
			return "| TRIBUTO ADICIONADO COM SUCESSO |";
		}
		return "| TRIBUTO OU CONTRIBUINTE NÃO ENCONTRADO |";
	}
	
	public String pagarTributo(String cpf, int codigoTributo) {
		if (contribuintes.containsKey(cpf) && contribuintes.get(cpf).buscarTributo(codigoTributo) != null) {
			contribuintes.get(cpf).pagarTributo(codigoTributo);
			return "| TRIBUTO PAGO COM SUCESSO |";
		}
		return "| TRIBUTO OU CONTRIBUINTE NÃO ENCONTRADO |";
	}
	
	public String emitirExtratoDeTributos(String cpf) {
		if (!contribuintes.containsKey(cpf)) {
			return "| CONTRIBUINTE NÃO ENCONTRADO |";	
		}
		String[] extratoArray = contribuintes.get(cpf).gerarExtrato();
		if (extratoArray.length == 0) {
			return "| EXTRATO VAZIO |";
		}
		
		String extratoStr = "|" + String.format("%7s", "Pago?").replaceAll("^(.*\\S)\\s+", "$1") + "| " + contribuintes.get(cpf).toString();
		for (String item : extratoArray) {
			extratoStr += "\n" + item;
		}
		return extratoStr;
	}
	
	public double totalPagoEmTributos(String cpf, int ano) {
		if (!contribuintes.containsKey(cpf)) {
			throw new IllegalArgumentException("Contribuinte não cadastrado!");
		}
		
		return contribuintes.get(cpf).totalPagoEmTributos(ano);
	}
}
