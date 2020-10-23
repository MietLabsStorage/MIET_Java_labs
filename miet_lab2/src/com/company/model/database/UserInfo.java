package com.company.model.database;

import com.company.model.WashingMachine;
import com.company.model.clothes.LinenColor;

import java.util.ArrayList;

public class UserInfo {
    private final String password;
    private final AccesRights acces;
    private final boolean debug;
    private final boolean autotest;

    /**
     * wm getter
     * @return
     */
    public WashingMachine getWm() {
        return wm;
    }

    /**
     * dirty heap getter
     * @return
     */
    public ArrayList<LinenColor> getDirtyHeap() {
        return dirtyHeap;
    }

    private WashingMachine wm;
    private ArrayList<LinenColor> dirtyHeap;

    /**
     * constructor
     * @param password
     * @param acces
     * @param debug
     * @param autotest
     */
    public UserInfo(String password, AccesRights acces, boolean debug, boolean autotest) {
        this.password = password;
        this.acces = acces;
        this.debug = debug;
        this.autotest = autotest;

        wm = new WashingMachine();
        dirtyHeap = new ArrayList<LinenColor>();
    }

    public String getPassword() {
        return password;
    }

    /**
     * acces getter
     * @return
     */
    public AccesRights getAcces(){
        return acces;
    }

    /**
     * debug getter
     * @return
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * autotest getter
     * @return
     */
    public boolean isAutotest() {
        return autotest;
    }
}
