package com.company.model.clothes;

/**
 * Enum that consist of:
 *     light(1),
 *     dark(2),
 *     multycolor(3);
 */
public enum Color {
    light(1),
    dark(2),
    multicolor(3),
    nonColor(4);

    //int-code of element
    private final int code;

    /**
     * constructor
     * @param code int code of element
     */
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

    /**
     * try convert string in Color
     * @param name string name
     * @return Color name
     * @throws Exception if cant convert
     */
    public static Color tryConvert(String name) throws Exception {
        for(Color colors : Color.values()){
            if(name.equals(colors.toString())){
                return colors;
            }
        }
        throw new Exception("Unsuccess convertation");
    }
}
