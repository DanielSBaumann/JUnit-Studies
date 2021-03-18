package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoServiceTest2 {

	private LocacaoService service;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		service = new LocacaoService();
	}

//	@After
//	public void tearDown() {
//		count++;
//		System.out.println("After : Count -> " + count);
//	}
//
//	@BeforeClass
//	public static void setupClass() {
//		count = 1;
//		System.out.println("Before Class : Count -> " + count);
//	}
//
//	@AfterClass
//	public static void tearDownClass() {
//		count++;
//		System.out.println("After Class : Count -> " + count);
//	}

	@Test
	public void test() throws Exception {

		// cenario
		Usuario usuario = new Usuario("Joao");
		Filme filme = new Filme("It", 1, 10.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		error.checkThat(locacao.getValor(), is(10.0));
		error.checkThat(locacao.getValor(), is(equalTo(10.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

	}

	// elegante
	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_semEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Joao");
		Filme filme = new Filme("It", 0, 10.0);
		// acao
		service.alugarFilme(usuario, filme);
	}

	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {
//		// cenario
		Usuario usuario = new Usuario("Joao");
		Filme filme = new Filme("It", 3, 10.0);
		// acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	@Test
	public void testeLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
		Usuario usuario = new Usuario("Joao");
		// Filme filme = new Filme("It", 3, 10.0);

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);

	}

//	// robusta
//	@Test
//	public void testLocacao_semEstoque2() {
//		// cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Joao");
//		Filme filme = new Filme("It", 0, 10.0);
//		// acao
//		try {
//			service.alugarFilme(usuario, filme);
//			Assert.fail("Deveria ter lançado uma exceção");
//		} catch (Exception e) {
//			assertThat(e.getMessage(), is("Filme sem estoque"));
//		}
//	}
//
//	// nova
//	@Test
//	public void testLocacao_semEstoque3() throws Exception {
//
//		// cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Joao");
//		Filme filme = new Filme("It", 0, 10.0);
//		exception.expect(Exception.class);
//		exception.expectMessage("Filme sem estoque");
//
//		// acao
//		service.alugarFilme(usuario, filme);
//	}
}
