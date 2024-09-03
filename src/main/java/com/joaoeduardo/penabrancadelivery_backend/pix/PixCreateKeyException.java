package com.joaoeduardo.penabrancadelivery_backend.pix;

public class PixCreateKeyException extends RuntimeException {
    public PixCreateKeyException(String message) {
        super(message);
    }

    public PixCreateKeyException() {
        super("ERROR pix creation key!");
    }

}
