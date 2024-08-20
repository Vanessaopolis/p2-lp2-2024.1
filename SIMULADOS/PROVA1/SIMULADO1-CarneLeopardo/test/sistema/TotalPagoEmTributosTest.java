package sistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalPagoEmTributosTest {
	private Contribuinte contribuinte;
	private Tributo tributo1, tributo2, tributo3, tributo4;
	
	@BeforeEach
	public void setUp() {
		contribuinte = new Contribuinte("123.456.789-00", "Nome Teste", "(00)98765-4321");
		tributo1 = new Tributo(1, "tributo1", 51, 2020);
		tributo2 = new Tributo(2, "tributo2", 23, 2021);
		tributo3 = new Tributo(3, "tributo3", 44, 2020);
		tributo4 = new Tributo(4, "tributo4", 17, 2022);
		
		// atribui os tributos para o contribuinte
		contribuinte.atribuirTributo(tributo1);
		contribuinte.atribuirTributo(tributo2);
		contribuinte.atribuirTributo(tributo3);
		contribuinte.atribuirTributo(tributo4);
	}
	
	@Test
	public void testTotalPagoEmTributosAnoEspecifico() {
		contribuinte.pagarTributo(1);
		contribuinte.pagarTributo(2);
		
		assertEquals(23, contribuinte.totalPagoEmTributos(2021), 0.00001, "A soma total paga em 2021 deve ser do tributo2");
	}
	
	@Test
	public void testTotalPagoEmTributosAnoEspecificoComMaisTributos() {
		contribuinte.pagarTributo(1);
		contribuinte.pagarTributo(2);
		contribuinte.pagarTributo(3);
		
		
		assertEquals(95, contribuinte.totalPagoEmTributos(2020), 0.00001, "A soma total paga em 2020 deve ser do tributo1+tributo3");
	}
	
	@Test
	public void testTotalPagoEmTributosAnoNaoExistente() {
		contribuinte.pagarTributo(1);
		contribuinte.pagarTributo(2);
		contribuinte.pagarTributo(3);
		contribuinte.pagarTributo(4);
		
		assertEquals(0, contribuinte.totalPagoEmTributos(2015), 0.00001, "A soma total paga em 2015 deve ser 0");
	}
}