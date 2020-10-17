package com.company.Model.database;

import com.company.Model.WashingMachine;
import com.company.Model.clothes.LinenColor;

import java.util.ArrayList;

public class UserInfo {
    private String password;
    private AccesRights acces;
    private boolean debug;
    private boolean autotest;

    private WashingMachine wm = new WashingMachine();
    private ArrayList<LinenColor> dirtyHeap = new ArrayList<>();

    public UserInfo(String password, AccesRights acces, boolean debug, boolean autotest) {
        this.password = password;
        this.acces = acces;
        this.debug = debug;
        this.autotest = autotest;
    }

    public String getPassword() {
        return password;
    }

    public AccesRights getAcces(){
        return acces;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isAutotest() {
        return autotest;
    }
}
