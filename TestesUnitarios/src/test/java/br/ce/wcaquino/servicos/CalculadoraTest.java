package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.entidades.Calculadora;
import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {

	private Calculadora calc;

	@Before
	public void setup() {
		calc = new Calculadora();
	}

	@Test
	public void somarDois() {
		// cenario
		int a = 5;
		int b = 3;
		// a��o
		int result = calc.somar(a, b);
		// verifica��o
		assertEquals(8, result);
	}

	@Test
	public void subtrairDois() {
		// cenario
		int a = 5;
		int b = 3;
		// a��o
		int result = calc.subtrair(a, b);
		// verifica��o
		assertEquals(2, result);
	}

	@Test
	public void dividirDois() throws NaoPodeDividirPorZeroException {
		// cenario
		int a = 12;
		int b = 3;
		// a��o
		int result = calc.dividir(a, b);
		// verifica��o
		assertEquals(4, result);
	}

	@Test
	public void multiplicarDois() {
		// cenario
		int a = 4;
		int b = 3;
		// a��o
		int result = calc.multiplicar(a, b);
		// verifica��o
		assertEquals(12, result);
	}

	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void excecaoDividirZero() throws NaoPodeDividirPorZeroException {
		// cenario
		int a = 10;
		int b = 0;
		// a��o
		int result = calc.dividir(a, b);
		// verifica��o
		assertEquals(12, result);
	}
}
