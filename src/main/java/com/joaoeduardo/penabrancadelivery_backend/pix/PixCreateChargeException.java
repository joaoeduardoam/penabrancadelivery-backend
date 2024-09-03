package com.joaoeduardo.penabrancadelivery_backend.pix;

import br.com.efi.efisdk.exceptions.EfiPayException;
import org.json.JSONObject;

public class PixCreateChargeException extends RuntimeException {
    public PixCreateChargeException(String message) {
        super(message);
    }

    public PixCreateChargeException() {
        super("ERROR pix creation charge!");
    }

}
