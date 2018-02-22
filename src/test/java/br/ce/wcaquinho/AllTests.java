package br.ce.wcaquinho;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssertTest.class, CalculadoraTest.class, LocacaoServiceTeste.class })
public class AllTests {

}
