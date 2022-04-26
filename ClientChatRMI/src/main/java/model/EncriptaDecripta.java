package model;

import java.util.Base64;

public class EncriptaDecripta {
    /** Criptografando */
    public String criptografiaBase64Encoder(String pValor) {
        return new String(Base64.getEncoder().encode(pValor.getBytes()));
    }

    /** Realizando o inverso */
    public String descriptografiaBase64Decode(String pValor) {
        return new String(Base64.getDecoder().decode(pValor.getBytes()));
    }
}
