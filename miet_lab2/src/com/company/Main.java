package com.company;

import com.company.model.database.db;

/**
 * @author Max Myasikov PIN-34
 * @version lab2
 */
public class Main {

    public static void main(String[] args) throws Exception {

        com.company.View.Log.alwaysWrite("Запуск программы");
        com.company.View.Log.alwaysWrite("Загрузка из базы данных: " + db.readUsers());
        com.company.View.Console.Out.SignInMenu();
        com.company.View.Log.alwaysWrite("Конец программы");
    }
}
