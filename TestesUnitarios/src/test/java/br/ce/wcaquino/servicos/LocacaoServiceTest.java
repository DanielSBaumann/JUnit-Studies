package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Test
	public void test() {

		// cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Joao");
//		Filme filme = new Filme("It", 2, 10.0);
//
//		// acao
//		Locacao locacao = service.alugarFilme(usuario, filme);
//
//		// verificacao
//		// assertThat(locacao.getValor(), is(5.0));
//		// assertThat(locacao.getValor(), is(equalTo(10.0)));
//		// assertArrayEquals
//		// assertThat(locacao.getValor(), is(10.0));
//		error.checkThat(locacao.getValor(), is(10.0));
//
//		// assertThat(locacao.getValor(), is(equalTo(10.0)));
//		error.checkThat(locacao.getValor(), is(equalTo(10.0)));
//
//		// assertThat(locacao.getValor(), is(not(9.0)));
//
//		// assertTrue(locacao.getValor() == 10);
//
//		// assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//		// assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
//		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
//
//		// assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(),
//		// DataUtils.obterDataComDiferencaDias(1)));
//		// assertThat(isMesmaData(locacao.getDataRetorno(),
//		// obterDataComDiferencaDias(1)), is(true));
//		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
}
