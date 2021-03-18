package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		if (filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		if (filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException();
		}
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

//	@Test
//	public void test() {
//		// cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Joao");
//		Filme filme = new Filme("It", 2, 10.0);
//		// acao
//		Locacao locacao = service.alugarFilme(usuario, filme);
//		// verificacao
//		Assert.assertTrue(locacao.getValor() == 10);
//		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
//	}
}