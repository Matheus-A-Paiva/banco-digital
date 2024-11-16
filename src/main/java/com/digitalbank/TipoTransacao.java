package com.digitalbank;

import lombok.Getter;

public enum TipoTransacao {

    DEPOSITO ("Depósito"),
    SAQUE ("Saque"),
    TRANSFERENCIA ("Transferência");

    @Getter
    private final String nome;

    private TipoTransacao (String nome) {
        this.nome = nome;
    }
}
