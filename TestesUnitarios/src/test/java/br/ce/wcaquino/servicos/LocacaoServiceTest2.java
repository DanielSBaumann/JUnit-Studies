package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.verificarDiaSemana;
import static matchers.MatchersProprios.caiNumaSegunda;
import static matchers.MatchersProprios.hoje;
import static matchers.MatchersProprios.hojeComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

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

	@Test
	public void deveAlugarFilmeComSucesso() throws Exception {
		assumeFalse(verificarDiaSemana(new Date(), Calendar.SATURDAY));
		// cenario
		Usuario usuario = new Usuario("Joao");
		List<Filme> filmes = Arrays.asList(new Filme("It", 1, 10.0));
		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		// verificacao
		error.checkThat(locacao.getValor(), is(10.0));
		error.checkThat(locacao.getValor(), is(equalTo(10.0)));

		// error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		// error.checkThat(isMesmaData(locacao.getDataRetorno(),
		// obterDataComDiferencaDias(1)), is(true));
		error.checkThat(locacao.getDataRetorno(), hojeComDiferencaDias(1));
		error.checkThat(locacao.getDataRetorno(), hoje());
	}

	// elegante
	@Test(expected = FilmeSemEstoqueException.class)
	public void deveLancarExceptionSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Joao");
		List<Filme> filmes = Arrays.asList(new Filme("It", 0, 10.0));
		// acao
		service.alugarFilme(usuario, filmes);
	}

	@Test
	public void naoDeveALugarFilmeSemUsuario() throws FilmeSemEstoqueException {
//		// cenario
		Usuario usuario = new Usuario("Joao");
		List<Filme> filmes = Arrays.asList(new Filme("It", 3, 10.0));
		// Filme filme = new Filme("It", 3, 10.0);
		// acao
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	@Test
	public void naoDeveALugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
		Usuario usuario = new Usuario("Joao");
		// Filme filme = new Filme("It", 3, 10.0);
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);
	}

	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
		assumeTrue(verificarDiaSemana(new Date(), Calendar.SATURDAY));
		// cenario
		Usuario usuario = new Usuario("Joao");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		// verificacao
		boolean monday = DataUtils.verificarDiaSemana(resultado.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(monday);
		// assertThat(resultado.getDataRetorno(), new
		// DiaSemanaMatcher(Calendar.MONDAY));
		// assertThat(resultado.getDataRetorno(), caiEm(Calendar.MONDAY));
		assertThat(resultado.getDataRetorno(), caiNumaSegunda());
//		assertThat(retorno.getDataRetorno(), caiEm(Calendar.MONDAY));
//		assertThat(retorno.getDataRetorno(), caiNumaSegunda);
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
//			Assert.fail("Deveria ter lan?ado uma exce??o");
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

	// @Test
//	public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
//		Usuario usuario = new Usuario("Joao");
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0));
//		// acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		// verificacao
//		// 4+4+3
//		assertThat(resultado.getValor(), is(11.0));
//	}
//
//	@Test
//	public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
//		Usuario usuario = new Usuario("Joao");
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0));
//		// acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		// verificacao
//		// 4+4+3
//		assertThat(resultado.getValor(), is(13.0));
//
//	}
//
//	@Test
//	public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
//		Usuario usuario = new Usuario("Joao");
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), new Filme("Filme 5", 2, 4.0));
//		// acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		// verificacao
//		assertThat(resultado.getValor(), is(14.0));
//
//	}
//
//	@Test
//	public void devePagar100PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
//		Usuario usuario = new Usuario("Joao");
//		List<Filme> filmes = Arrays
//				.asList(
//						new Filme("Filme 1", 2, 4.0), 
//						new Filme("Filme 2", 2, 4.0),
//						new Filme("Filme 3", 2, 4.0), 
//						new Filme("Filme 4", 2, 4.0), 
//						new Filme("Filme 5", 2, 4.0),
//						new Filme("Filme 6", 2, 4.0));
//		// acao
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		// verificacao
//		// 4+4+3
//		assertThat(resultado.getValor(), is(14.0));
//
//	}

	// @After
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
}
