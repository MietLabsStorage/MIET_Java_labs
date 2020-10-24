package com.company.model.database;

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

    /**
     * constructor
     * @param code int-code of element
     */
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

    /**
     * try convert string in AccesRights
     * @param name string name
     * @return AccesRights name
     * @throws Exception if cant convert
     */
    public static AccesRights tryConvert(String name) throws Exception {
        for(AccesRights rights : AccesRights.values()){
            if(name.equals(rights.toString())){
                return rights;
            }
        }
        throw new Exception("Unsuccess convertation");
    }
}

