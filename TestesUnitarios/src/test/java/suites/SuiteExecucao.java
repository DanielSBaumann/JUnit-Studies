package suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTest;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTest2;

//@RunWith(Suite.class)
@SuiteClasses({ CalculadoraTest.class, CalculoValorLocacaoTest.class, LocacaoServiceTest2.class })
public class SuiteExecucao {

	@BeforeClass
	public static void before() {
		System.out.println("Before");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After");
	}
}
