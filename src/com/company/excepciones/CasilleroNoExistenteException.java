package com.company.excepciones;

public class CasilleroNoExistenteException extends Exception {
    public CasilleroNoExistenteException(String mensaje){
        super(mensaje);
    }
}
