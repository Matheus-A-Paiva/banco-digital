package com.digitalbank;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transacao {

    private TipoTransacao tipo;
    private double valor;
    private LocalDateTime data;
    private String descricao;

    public Transacao(TipoTransacao tipo, double valor, LocalDateTime data, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "tipo=" + tipo +
                ", valor=" + valor +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
