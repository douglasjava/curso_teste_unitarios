package br.ce.wcaquinho;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void testBoolean() {

		assertTrue(true);
		assertFalse(false);

		assertEquals(1, 1);

		assertEquals(0.51234, 0.512, 0.001); // 3 parametro -> Precissão.

		assertEquals(Math.PI, 3.14, 0.01);

		int i = 5;
		Integer i2 = 5;

		assertEquals(Integer.valueOf(i), i2);
		assertEquals(i, i2.intValue());

		assertEquals("bola", "bola");
		assertTrue("bola".equalsIgnoreCase("Bola"));

		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = null;

		assertEquals(u1, u1);

		assertEquals(u1, u2); // Implementar equals
		assertNotEquals("bola", "casa");

		assertSame(u2, u2); // Comparação de Objetos
		assertNotSame(u1, u2);

		assertNull(u3);
		assertNotNull(u2);

		assertEquals("Erro de comparação", 2, 2);

	}

}
