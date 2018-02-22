package br.ce.wcaquinho;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Calculadora;
import br.ce.wcaquino.servicos.NaoPodeDividirPorZero;

public class CalculadoraTest {

	public Calculadora calc;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void upSet() {
		calc = new Calculadora();
	}

	@Test
	public void somaDoisValores() {

		// cenario
		int a = 5, b = 6;

		// a��o
		Integer soma = calc.soma(a, b);

		// verifica�ao
		assertEquals(11, soma, 0.01);
	}

	@Test
	public void subtrairDoisValores() {

		// cenario
		int a = 10, b = 6;

		// a��o
		Integer soma = calc.subtrair(a, b);

		// verifica�ao
		assertEquals(4, soma, 0.01);
	}

	@Test(expected = NaoPodeDividirPorZero.class)
	public void dividirPorZero() throws NaoPodeDividirPorZero {

		// cenario
		int a = 10, b = 0;

		// a��o
		Integer soma = calc.divide(a, b);

		// verifica�ao
		assertEquals("N�o pode dividir por zero", soma);
	}

	@Test
	public void dividiPorZero() {

		// cenario
		int a = 10, b = 0;

		try {
			calc.divide(a, b);
			fail("Deveria ter lan�ado exce��o");
		} catch (NaoPodeDividirPorZero e) {
			assertThat(e.getMessage(), is("N�o pode dividir por zero"));
		}
	}

	@Test
	public void testeFilmeSemEstoque3() throws NaoPodeDividirPorZero {

		int a = 10, b = 0;

		exception.expect(NaoPodeDividirPorZero.class);
		exception.expectMessage("N�o pode dividir por zero");

		calc.divide(a, b);

	}

}
