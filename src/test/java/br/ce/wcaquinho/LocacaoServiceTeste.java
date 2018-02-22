package br.ce.wcaquinho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.servicos.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTeste {

	Locacao locacao;
	LocacaoService locacaoService;

	/****
	 * Junit reinicializa todo o scopo da classe, a cada testes
	 */
	private static Integer cont = 0;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	/** Antes de cada teste **/
	@Before
	public void setUp() {
		locacao = new Locacao();
		locacaoService = new LocacaoService();
		cont++;
		System.out.println("Inicializando " + cont + "° teste");
	}

	@After
	public void tearDown() {
		System.out.println("Finalizando " + cont + "° teste");
	}

	/** Antes de todo o conjunto de teste **/
	@BeforeClass
	public static void setUpClass() {
		System.out.println("Testes Iniciados");
	}

	/** Final de todo o conjunto de teste **/
	@AfterClass
	public static void tearDownClass() {
		System.out.println("Testes Finalizados");
	}

	@Test
	public void testeValorLocacao() throws Exception {

		Usuario usuario = new Usuario("Douglas");
		List<Filme> filmes = Arrays.asList(new Filme("Up Altas aventuras!", 5, 5.0));

		locacao = locacaoService.alugarFilme(usuario, filmes);

		assertEquals(5.0, locacao.getValor(), 0);

	}

	@Test
	public void testeDataLocacao() throws Exception {

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto", 3, 3.5));

		locacao = locacaoService.alugarFilme(usuario, filmes);

		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));

	}

	@Test
	public void testeDataRetorno() throws Exception {

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto 2", 3, 3.5));

		locacao = locacaoService.alugarFilme(usuario, filmes);

		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}

	@Test
	public void testeAsssertThat() throws LocadoraException {

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto 2", 3, 3.5));

		locacao = locacaoService.alugarFilme(usuario, filmes);
		assertThat(locacao.getValor(), is(equalTo(3.5))); // Verifica-se que valor é igual a 5
	}

	@Test
	public void deveAlugarFilme() throws Exception {

		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto 2", 1, 5.0));

		locacao = locacaoService.alugarFilme(usuario, filmes);

		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));

	}

	@Test(expected = LocadoraException.class)
	public void testeFilmeSemEstoque() throws Exception {

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto 2", 0, 5.0));

		locacaoService.alugarFilme(usuario, filmes);

	}

	@Test
	public void testeFilmeSemEstoque2() {

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto 2", 0, 5.0));

		try {
			locacaoService.alugarFilme(usuario, filmes);
			fail("Deveria ter lançado exceção");
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Filme não tem no estoque"));
		}

	}

	@Test
	public void testeFilmeSemEstoque3() throws LocadoraException {

		Usuario usuario = new Usuario("Débora");
		List<Filme> filmes = Arrays.asList(new Filme("Deus não está morto 2", 0, 5.0));

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme não tem no estoque");

		locacaoService.alugarFilme(usuario, filmes);

	}

	@Test
	public void testPagar75Pct() throws LocadoraException {

		Usuario usuario = new Usuario("Douglas");
		List<Filme> filmes = Arrays.asList(new Filme("Marley1", 2, 4.0), new Filme("Marley2", 2, 4.0),
				new Filme("Marley3", 2, 4.0));

		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

		assertThat(locacao.getValor(), is(11.0));

	}

	@Test
	public void testPagar50Pct() throws LocadoraException {

		Usuario usuario = new Usuario("Douglas");
		List<Filme> filmes = Arrays.asList(new Filme("Marley1", 2, 4.0), new Filme("Marley2", 2, 4.0),
				new Filme("Marley3", 2, 4.0), new Filme("Marley3", 2, 4.0));

		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

		assertEquals(13.0, locacao.getValor(), 0.01);
		// assertThat(locacao.getValor(), is(13.0));

	}

	@Test
	public void testPagar25Pct() throws LocadoraException {

		Usuario usuario = new Usuario("Douglas");
		List<Filme> filmes = Arrays.asList(new Filme("Marley1", 2, 4.0), new Filme("Marley2", 2, 4.0),
				new Filme("Marley3", 2, 4.0), new Filme("Marley3", 2, 4.0), new Filme("Marley3", 2, 4.0));

		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

		// 4+4+3+2+1
		assertThat(locacao.getValor(), is(14.0));

	}

	@Test
	public void testPagar0Pct() throws LocadoraException {

		Usuario usuario = new Usuario("Douglas");
		List<Filme> filmes = Arrays.asList(new Filme("Marley1", 2, 4.0), new Filme("Marley2", 2, 4.0),
				new Filme("Marley3", 2, 4.0), new Filme("Marley3", 2, 4.0), new Filme("Marley3", 2, 4.0),
				new Filme("Marley3", 2, 4.0));

		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

		assertThat(locacao.getValor(), is(14.0));

	}

	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws LocadoraException {

		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		Usuario usuario = new Usuario("Douglas");
		List<Filme> filmes = Arrays.asList(new Filme("Marley1", 2, 4.0), new Filme("Marley2", 2, 4.0));

		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

		boolean isMonday = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.SUNDAY);
		assertTrue(isMonday);

	}

}
