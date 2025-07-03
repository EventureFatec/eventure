package com.github.eventure.model;

public enum Visibilidade {
    PUBLICO, PRIVADO;
    
    public static boolean isValido(Visibilidade v) {
        return v == PUBLICO || v == PRIVADO;
    }
}
