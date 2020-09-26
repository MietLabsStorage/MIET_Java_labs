package com.company.Model.clothes;

public enum Color {
    light(1),
    dark(2),
    multycolor(3);

    private final int code;

    Color(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
