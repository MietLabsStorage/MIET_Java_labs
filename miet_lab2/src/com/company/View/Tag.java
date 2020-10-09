package com.company.View;

public enum Tag {
    AddWMPowder(1001),
    AddWMConditioner(1002),
    AddWMColor(1003),
    AddWMTemperature(1004),

    LoadWMLinen(1101),
    UnloadWMLinenAndShow(1102),
    RunWM(1104),
    StatusWM(1105),

    AddDirtyLinen(2001),
    ShowDirtyLinen(2002);

    private final int code;

    Tag(int code){
        this.code = code;
    }

    /**
     * get int code of enum's elements
     * @return int-code of element
     */
    public int getCode(){ return code;}
}
