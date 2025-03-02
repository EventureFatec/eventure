package com.github.eventure.controllers;

import com.github.eventure.model.PasswordRules;

public class PasswordController {
    private static final short MINIMUM_LENGTH = 8;

    /**
     * Checks the password and verifies its validity.
     *
     * NOTE: Allowed characters: Basic Latin (0x20 -> 0x7E)
     *
     * @param   password    the password before it is encrypted
     * @return              the password flags (as a short)
     * @see                 PasswordRules
     */
    public short validatePasswordRules(String password) {
        var activeRules = PasswordRules.HAS_NONE;

        if (password.length() >= MINIMUM_LENGTH) activeRules |= PasswordRules.HAS_ENOUGH_CHARACTERS;

        for (char c: password.toCharArray()) {
            if (!((activeRules & PasswordRules.HAS_INVALID_CHARACTERS) == 0) && (c < '!' || c > '~')) {
                activeRules |= PasswordRules.HAS_INVALID_CHARACTERS;  // NOTE: This is the rule that must not be set!
            } else if (((activeRules & PasswordRules.HAS_LOWERCASE_CHARACTERS) == 0) && (c >= 'a' && c <= 'z')) {
                activeRules |= PasswordRules.HAS_LOWERCASE_CHARACTERS;
            } else if (((activeRules & PasswordRules.HAS_UPPERCASE_CHARACTERS) == 0) && (c >= 'A' && c <= 'Z')) {
                activeRules |= PasswordRules.HAS_UPPERCASE_CHARACTERS;
            } else if (((activeRules & PasswordRules.HAS_NUMBERS) == 0) && (c >= '0' && c <= '9')) {
                activeRules |= PasswordRules.HAS_NUMBERS;
            } else {
                if ((activeRules & PasswordRules.HAS_SYMBOLS) == 0) {
                    if ((c >= '!' && c <= '/') ||
                        (c >= ':' && c <= '@') ||
                        (c >= '[' && c <= '`') ||
                        (c >= '{' && c <= '~')) {
                        activeRules |= PasswordRules.HAS_SYMBOLS;
                    }
                }
            }
        }

        return activeRules;
    }
}
