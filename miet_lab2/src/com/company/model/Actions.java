package com.company.model;

import com.company.Controller;
import com.company.model.clothes.LinenColor;
import com.company.model.database.AccesRights;
import com.company.model.database.UserInfo;
import com.company.model.database.db;
import com.company.View.Log;
import com.company.View.Tag;

/**
 * Model class
 */
public class Actions {

    private static UserInfo user;

    /**
     * do any action after authorization against tag's int code
     * @param actNum tag's int code
     * @throws Exception
     */
    public static int runAction(int actNum) throws Exception {

        if(actNum == Tag.AddWMPowder.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("powder");
            user.getWm().setPowder(Controller.getString());
            return 0;
        }

        if(actNum == Tag.AddWMConditioner.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("conditioner");
            user.getWm().setConditioner(Controller.getString());
            return 0;
        }

        if(actNum == Tag.AddWMColor.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("color");
            com.company.View.Console.Out.ShowColors();
            user.getWm().setColor(Controller.getColor());
            return 0;
        }

        if(actNum == Tag.AddWMTemperature.getCode()){
            try {
                com.company.View.Console.Out.ShowAddWMSmth("temperature");
                user.getWm().setTemperature(Controller.getInt());
            }
            catch (Exception e){
                com.company.View.Console.Err.PrintErr(e.toString());
            }
            return 0;
        }

        if(actNum == Tag.LoadWMLinen.getCode()){
            try {
                user.getDirtyHeap().addAll(user.getWm().load(user.getDirtyHeap()));
            }
            catch (Exception e){
                com.company.View.Console.Err.PrintErr(e.toString());
            }
            return 0;
        }

        if(actNum == Tag.UnloadWMLinenAndShow.getCode()){
            com.company.View.Console.Out.ShowLinens(user.getWm().unload());
            return 0;
        }

        if(actNum == Tag.RunWM.getCode()){
            com.company.View.Console.Out.ShowRunWM(user.getWm().run());
            return 0;
        }

        if(actNum == Tag.StatusWM.getCode()){
            com.company.View.Console.Out.ShowStatusWM(user.getWm());
            return 0;
        }

        if(actNum == Tag.AddDirtyLinen.getCode()){
            com.company.View.Console.Out.ShowLinenColorStructure();
            user.getDirtyHeap().add(new LinenColor(Controller.getInt(),Controller.getInt(),Controller.getColor()));
            return 0;
        }

        if(actNum == Tag.ShowDirtyLinen.getCode()){
            com.company.View.Console.Out.ShowLinens(user.getDirtyHeap());
            return 0;
        }

        if(actNum == Tag.SignOut.getCode()){
            com.company.View.Log.alwaysWrite("Sign out");
            return -1;
        }

        com.company.View.Console.Err.PrintErr("no so action");
        return 0;
    }

    /**
     * do any action before authorization against tag's int code
     * @param actNum
     * @throws Exception
     */
    public static int runSign(int actNum) throws Exception {
        if(actNum == Tag.SignIn.getCode()){
            com.company.View.Console.Out.ShowMessage("Sign In:: ");
            com.company.View.Console.Out.ShowMessage("Write nickname: ");
            String nickname = Controller.getString();
            com.company.View.Console.Out.ShowMessage("Write password: ");
            String password = Controller.getString();
            if(db.getUsers().containsKey(nickname)) {
                if (db.getUsers().get(nickname).getPassword().equals(password)) {
                    user = new UserInfo(db.getUsers().get(nickname).getPassword(),
                            db.getUsers().get(nickname).getAcces(),
                            db.getUsers().get(nickname).isDebug(),
                            db.getUsers().get(nickname).isAutotest()
                    );
                    Log.isRun = user.isDebug();
                    System.out.println("\n|___Hello_" + nickname + "___|");
                    com.company.View.Log.alwaysWrite("Sign in: " + nickname);
                    com.company.View.Console.Out.ShowActionsMenu();
                }
            }
            else{
                com.company.View.Console.Err.PrintErr("incorrect nickname or password");
                Log.alwaysWrite("incorrect nickname or password");
            }
            return 0;
        }

        if(actNum == Tag.SignUp.getCode()){
            com.company.View.Console.Out.ShowMessage("Sign Up:: ");
            com.company.View.Console.Out.ShowMessage("Write nickname: ");
            String nickname = Controller.getString();
            com.company.View.Console.Out.ShowMessage("Write password: ");
            String password = Controller.getString();
            AccesRights acces;
            while(true){
                try{
                    com.company.View.Console.Out.ShowMessage("Write acces (root or user): ");
                    acces = AccesRights.tryConvert(Controller.getString());
                    break;
                }
                catch (Exception e){
                    com.company.View.Console.Err.PrintErr(e.toString());
                }
            }
            boolean debug, autotest;
            if(acces.equals(AccesRights.user)){
                com.company.View.Console.Out.ShowMessage("Write isDebug: ");
                debug = Boolean.parseBoolean(Controller.getString());
                autotest = false;
            }
            else{
                debug = true;
                com.company.View.Console.Out.ShowMessage("Write isAutotest: ");
                autotest = Boolean.parseBoolean(Controller.getString());
            }
            user = new UserInfo(password,
                    acces,
                    debug,
                    autotest
            );
            db.add(new UserInfo(password, acces, debug, autotest), nickname);
            Log.isRun = user.isDebug();
            System.out.println("\n|___Hello_" + nickname + "___|");
            com.company.View.Log.alwaysWrite("Sign in: " + nickname);
            com.company.View.Console.Out.ShowActionsMenu();
            return 0;
        }

        if(actNum == Tag.ShowUsers.getCode()){
            com.company.View.Console.Out.ShowMessage(db.getUsersAsString());
            return 0;
        }

        if(actNum == Tag.DeleteUser.getCode()){
            com.company.View.Console.Out.ShowMessage("Write nickname: ");
            String nickname = Controller.getString();
            com.company.View.Console.Out.ShowMessage("Write password: ");
            String password = Controller.getString();
            if(db.getUsers().get(nickname).getPassword().equals(password)){
                db.delete(nickname);
            }
            com.company.View.Log.alwaysWrite("Delete user: " + nickname);
            return 0;
        }

        if(actNum == Tag.Exit.getCode()){
            com.company.View.Log.alwaysWrite("Exit");
            return -1;
        }

        com.company.View.Console.Err.PrintErr("no so action");
        Log.alwaysWrite("no so action");
        return 0;
    }
}
