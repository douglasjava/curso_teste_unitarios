package br.ce.wcaquino.entidades;

import br.ce.wcaquino.servicos.NaoPodeDividirPorZero;

public class Calculadora {

	public int soma(int a, int b) {
		return a + b;
	}

	public int subtrair(int a, int b) {
		return a - b;
	}

	public int divide(int a, int b) throws NaoPodeDividirPorZero {
		if (b == 0) {
			throw new NaoPodeDividirPorZero("Não pode dividir por zero");
		}
		return a / b;
	}

}
