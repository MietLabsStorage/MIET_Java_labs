package com.company.model.database;

import com.company.model.WashingMachine;
import com.company.model.clothes.LinenColor;

import java.util.ArrayList;

/**
 * class with all user info without nickname
 */
public class UserInfo {
    private final String password;
    private final AccesRights access;
    private final boolean debug;
    private final boolean autotest;

    private WashingMachine wm;
    private final ArrayList<LinenColor> dirtyHeap;

    /**
     * wm getter
     * @return washing machine
     */
    public WashingMachine getWm() {
        return wm;
    }

    public void setWm(WashingMachine _wm){
        wm = _wm;
    }

    /**
     * dirty heap getter
     * @return dirty heap linens
     */
    public ArrayList<LinenColor> getDirtyHeap() {
        return dirtyHeap;
    }

    /**
     * constructor
     * @param password password
     * @param access access roots (root/user)
     * @param debug is debug run
     * @param autotest is autotests run
     */
    public UserInfo(String password, AccesRights access, boolean debug, boolean autotest) {
        this.password = password;
        this.access = access;
        this.debug = debug;
        this.autotest = autotest;

        wm = new WashingMachine();
        dirtyHeap = new ArrayList<LinenColor>();
    }

    /**
     * password getter
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * access getter
     * @return access rights
     */
    public AccesRights getAccess(){
        return access;
    }

    /**
     * debug getter
     * @return debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * autotest getter
     * @return autotest
     */
    public boolean isAutotest() {
        return autotest;
    }
}
