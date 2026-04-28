package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.exceptions;

public class PedidoSqsConsumerException extends RuntimeException {

    public PedidoSqsConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}
