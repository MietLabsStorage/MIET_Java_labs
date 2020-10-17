package com.company.Model.database;

/**
 * Enum that consist of:
 *     root(1),
 *     user(2),
 */
public enum AccesRights {
    root(1),
    user(2);

    //int-code of element
    private final int code;

   AccesRights(int code) {
        this.code = code;
    }

    /**
     * get int code of enum's elements
     * @return int-code of element
     */
    public int getCode() {
        return code;
    }

    public static AccesRights tryConvert(String name){
        for(AccesRights rights : AccesRights.values()){
            if(name.equals(rights.toString())){
                return rights;
            }
        }
        return null;
    }
}

