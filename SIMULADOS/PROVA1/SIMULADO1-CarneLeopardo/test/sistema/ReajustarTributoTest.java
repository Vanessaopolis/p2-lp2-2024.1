package sistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReajustarTributoTest {
	
	private Tributo tributo;
	
	@BeforeEach
	public void setUp() {
		tributo = new Tributo(20, "TRIBUTO TESTE", 100, 2020);
	}
	
	@Test
	public void testReajustarTributoComPercentualPositivoEAnoFuturo() {
		assertEquals(110, tributo.reajustarTributo(2021, 0.10), 0.00001, "O valor reajustado deve ser 110.0");
		assertEquals(2021, tributo.getAno(), "O ano deve ser atualizado para 2021");
	}
	
	@Test
	public void testReajustarTributoComPercentualForaDoLimitePermitido() {
		assertEquals(0, tributo.reajustarTributo(2021, -1), "O resultado deve ser 0 para percentual for -1.0");
	}
	
	@Test
	public void testReajustarTributoComAnoMenorAoAtual() {
		assertEquals(0, tributo.reajustarTributo(2019, 0.10), "O resultado deve ser 0 para anos menores");
	}
	
	@Test
	public void testReajustarTributoComAnoIgualAoAtual() {
		assertEquals(0, tributo.reajustarTributo(2020, 0.10), "O resultado deve ser 0 para anos iguais");
	}
}
