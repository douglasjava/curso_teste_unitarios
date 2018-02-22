package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {

	private static int contador = 0;

	@Test
	public void iniciar() {
		contador = 1;
	}

	@Test
	public void verificar() {
		assertEquals(1, contador);
	}

}
