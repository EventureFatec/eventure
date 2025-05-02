package com.github.eventure.controllers;

public class CnpjController {
    public boolean isCnpjValid(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) {
            return false;
        }

        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }

        int[] weight = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        int digit;

        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weight[i + 1];
        }

        digit = 11 - sum % 11;
        if (digit > 9) {
            digit = 0;
        }

        if (digit != cnpj.charAt(12) - '0') {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weight[i];
        }

        digit = 11 - sum % 11;
        if (digit > 9) {
            digit = 0;
        }

        if (digit != cnpj.charAt(13) - '0') {
            return false;
        }

        return true;
    }
}
