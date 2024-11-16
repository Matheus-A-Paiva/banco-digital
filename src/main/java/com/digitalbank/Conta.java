package com.digitalbank;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;


	private List<Transacao> historico;
	@Getter
	protected int agencia;
	@Getter
	protected int numero;
	@Getter
	protected double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.historico = new ArrayList<>();
	}

	@Override
	public void sacar(double valor) {
		if (valor < saldo) {
			saldo -= valor;
			registrarTransacao(TipoTransacao.SAQUE, valor, "Saque realizado no valor de " + valor, null);
		}
		System.out.println("Saldo insuficiente para saque");
		return;
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
		registrarTransacao(TipoTransacao.DEPOSITO, valor, "Depósito realizado no valor de " + valor, null);
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
		if (contaDestino instanceof Conta) {
			Conta conta = (Conta) contaDestino;
			registrarTransacao(TipoTransacao.TRANSFERENCIA, valor, "Transferência para conta "  + conta.getNumero() + " no valor de " + valor, contaDestino);
		}

	}

	protected void registrarTransacao(TipoTransacao tipo, double valor, String descricao, IConta contaDestino) {
		historico.add(new Transacao(tipo, valor, LocalDateTime.now(), descricao));
		if (contaDestino!= null) {
			((Conta) contaDestino).registrarTransacao(tipo, valor, descricao, null);
		}
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	@Override
	public void imprimirHistorico() {
		System.out.println("=== Histórico transações ===");
		historico.forEach(transacao -> System.out.println(transacao));
	}

}
