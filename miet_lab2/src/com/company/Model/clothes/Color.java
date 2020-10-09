package com.company.Model.clothes;

/**
 * Enum that consist of:
 *     light(1),
 *     dark(2),
 *     multycolor(3);
 */
public enum Color {
    light(1),
    dark(2),
    multycolor(3);

    //int-code of element
    private final int code;

    Color(int code) {
        this.code = code;
    }

    /**
     * get int code of enum's elements
     * @return int-code of element
     */
    public int getCode() {
        return code;
    }
}
