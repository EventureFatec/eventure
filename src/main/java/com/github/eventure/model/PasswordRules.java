package com.github.eventure.model;

public class PasswordRules {
    public static final short HAS_NONE                 = 0b00000;
    public static final short HAS_ENOUGH_CHARACTERS    = 0b00001;
    public static final short HAS_LOWERCASE_CHARACTERS = 0b00010;
    public static final short HAS_UPPERCASE_CHARACTERS = 0b00100;
    public static final short HAS_NUMBERS              = 0b01000;
    public static final short HAS_SYMBOLS              = 0b10000;
    public static final short HAS_ALL                  = 0b11111;
}
