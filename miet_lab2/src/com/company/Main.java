package com.company;

import com.company.Model.clothes.Color;
import com.company.Model.database.AccesRights;
import com.company.Model.database.UserInfo;
import com.company.Model.database.db;
import com.company.View.Tag;

import javax.swing.text.View;

/**
 * @author Max Myasikov PIN-34
 * @version lab2
 */
public class Main {

    public static void main(String[] args) throws Exception {

        db.read();
        db.add(new UserInfo("88888888", AccesRights.root, true, true), "simka");
        db.write();
        while (true) {
            com.company.View.Console.Out.ShowActionsMenu();
        }
    }
}
