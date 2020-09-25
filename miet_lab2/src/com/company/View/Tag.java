package com.company.View;

public enum Tag {
    AddWMPowder(1001),
    AddWMConditioner(1002),
    AddWMColor(1003),
    AddWMTemperature(1004),
    LoadWMLinen(1101),
    UnloadWMLinen(1102),
    RunWM(1103),

    AddDirtyLinen(2001),

    Action(0);

    private final int code;

    Tag(int code){
        this.code = code;
    }

    public int getCode(){ return code;}
}
