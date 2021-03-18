package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		Assert.assertEquals(0.5123, 0.51234, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);

		int n1 = 5;
		Integer n2 = 5;

		/*
		 * A ordem de insercao é
		 * 
		 * Assert.assertEquals("valor esperado","valor atual");
		 */

		Assert.assertEquals(Integer.valueOf(n1), n2);
		Assert.assertEquals(n1, n2.intValue());

		Assert.assertEquals("bola", "bola");
		// Assert.assertEquals("bola", "Bola");
		Assert.assertEquals("bola", "Bola".toLowerCase());
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));

		Usuario u1 = new Usuario("user");
		Usuario u2 = new Usuario("user");
		Usuario u3 = null;

		Assert.assertEquals(u1, u2);
		// Assert.assertSame(u1, u2);

		Assert.assertNull(u3);
		Assert.assertTrue(u3 == null);
		Assert.assertNotEquals(u3, u2);
		Assert.assertNotSame(u1, u2);

		// exemplo com msg erro
		// Assert.assertEquals("Erro de comparação", u3, u2);
		Assert.assertEquals("Erro de comparação", null, u3);
	}
}
