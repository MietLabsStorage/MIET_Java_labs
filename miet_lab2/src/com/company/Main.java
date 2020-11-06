package com.company;

import com.company.View.Console.Err;
import com.company.View.graphic.Graphs;
import com.company.View.Log;
import com.company.model.database.db;

import javax.swing.*;

/**
 * @author Max Myasikov PIN-34
 * @version lab2
 */
public class Main {

    public static void main(String[] args) throws Exception {

        /*EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Graphs.initAndRun();
            }
        });*/

        Log.alwaysWrite("=====================================================================");
        Log.alwaysWrite("Запуск программы");
        Log.alwaysWrite("Загрузка из базы данных: " + db.readUsers());
        com.company.View.Console.Out.SignInMenu();
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Graphs.initAndRun();
            }
        });
        Log.alwaysWrite("Конец программы");
        Log.alwaysWrite("Произошло ошибок: " + Err.getCount());
        Log.alwaysWrite("=====================================================================");
    }
}
