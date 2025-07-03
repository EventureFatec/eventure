package com.github.eventure.model.passwords;

import java.util.Base64;

public class Password {

    public Password() {

    }
    
    private byte[] passwordHash;
    private byte[] passwordSalt;

    public Password(byte[] passwordHash, byte[] passwordSalt) {
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    // Métodos para converter os bytes para uma string base64
    public String getPasswordHashAsString() {
        return Base64.getEncoder().encodeToString(passwordHash);
    }

    public String getPasswordSaltAsString() {
        return Base64.getEncoder().encodeToString(passwordSalt);
    }
}
