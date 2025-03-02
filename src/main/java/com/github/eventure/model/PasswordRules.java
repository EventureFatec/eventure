package com.github.eventure.model;

/**
 * Uses bitwise rules to set any special flags when checking the password 
 * validity - aside from one exception in the class, most flags are positive
 * and must be set.
 */
public class PasswordRules {
    /** The default state. */
    public static final short HAS_NONE                 = 0b000000;
    public static final short HAS_ENOUGH_CHARACTERS    = 0b000001;
    public static final short HAS_LOWERCASE_CHARACTERS = 0b000010;
    public static final short HAS_UPPERCASE_CHARACTERS = 0b000100;
    public static final short HAS_NUMBERS              = 0b001000;
    public static final short HAS_SYMBOLS              = 0b010000;
    /** Password flags must be equal to this. */
    public static final short HAS_FULL_VALIDITY        = 0b011111;
    /** This is the exception and must always be unset. */
    public static final short HAS_INVALID_CHARACTERS   = 0b100000;
}
