package com.github.eventure.controllers;

import java.util.Arrays;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.passwords.Password;
import com.github.eventure.model.passwords.PasswordRules;

public class PasswordController {
    private static final short MINIMUM_LENGTH = 8;

    /**
     * Encrypts a password.
     *
     * @param   password    the password before it is encrypted
     * @return              the encrypted password class
     * @see                 Password
     */
    public Password createPassword(String password) {
        var p = new Password();

        var salt = Encryption.generateSalt();

        p.setPasswordSalt(salt);
        p.setPasswordHash(Encryption.generateHash(password, salt));
        return p;
    }

    /**
     * Verifies if a password is equal to another.
     *
     * @param   password        the first password as a string
     * @param   storedPassword  the second password as its class
     * @return                  true if they are equal, false if they are not
     */
    public boolean verifyPassword(String password, Password storedPassword) {
        var p = new Password();

        p.setPasswordHash(Encryption.generateHash(password, storedPassword.getPasswordSalt()));

        return Encryption.checkHashes(p.getPasswordHash(), storedPassword.getPasswordHash());
    }

    /**
     * Changes a password if it is new
     *
     * @param   newPassword     the new password as a string
     * @param   storedPassword  the current password as its class
     * @return                  null if failed, Password if successful.
     */
    public Password changePassword(String newPassword, Password storedPassword) {
        if (verifyPassword(newPassword, storedPassword)) return null;

        return createPassword(newPassword);
    }

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
