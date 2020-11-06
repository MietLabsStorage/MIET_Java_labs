package com.company.model;

import com.company.Controller;
import com.company.View.Log;
import com.company.model.clothes.LinenColor;
import com.company.model.database.AccesRights;
import com.company.model.database.UserInfo;
import com.company.model.database.db;
import com.company.View.Tag;

import java.util.ArrayList;

/**
 * Model class
 */
public class Actions {

    /**
     * current nickname getter
     * @return nickname
     */
    public static String getCurrentNickname() {
        return currentNickname;
    }
    private static String currentNickname;

    /**
     * do any action after authorization against tag's int code
     * @param actNum tag's int code
     * @throws Exception
     */
    public static int runAction(int actNum) throws Exception {
        //in all if-s: ret 0 to continue, ret -1 to exit

        //add powder in wm
        if(actNum == Tag.AddWMPowder.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("powder");
            db.getUsers().get(currentNickname).getWm().setPowder(Controller.getString());
            return 0;
        }

        //add conditioner in wm
        if(actNum == Tag.AddWMConditioner.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("conditioner");
            db.getUsers().get(currentNickname).getWm().setConditioner(Controller.getString());
            return 0;
        }

        //add color in wm
        if(actNum == Tag.AddWMColor.getCode()){
            com.company.View.Console.Out.ShowAddWMSmth("color");
            com.company.View.Console.Out.ShowColors();
            db.getUsers().get(currentNickname).getWm().setColor(Controller.getColor());
            return 0;
        }

        //add temperature in wm
        if(actNum == Tag.AddWMTemperature.getCode()){
            try {
                com.company.View.Console.Out.ShowAddWMSmth("temperature");
                db.getUsers().get(currentNickname).getWm().setTemperature(Controller.getInt());
            }
            catch (Exception e){
                com.company.View.Console.Err.PrintErr(e.toString());
            }
            return 0;
        }

        //load dirty heap in wm
        if(actNum == Tag.LoadWMLinen.getCode()){
            try {
                //load in wm from derty heap and reload in derty heap that was not loaded in wm
                ArrayList<LinenColor> tempHeap = db.getUsers().get(currentNickname).getWm().load(
                        db.getUsers().get(currentNickname).getDirtyHeap());
                db.getUsers().get(currentNickname).getDirtyHeap().clear();
                db.getUsers().get(currentNickname).getDirtyHeap().addAll(tempHeap);
                /*db.getUsers().get(currentNickname).getDirtyHeap().addAll(
                        db.getUsers().get(currentNickname).getWm().load(
                                db.getUsers().get(currentNickname).getDirtyHeap()
                        ));*/
            }
            catch (Exception e){
                com.company.View.Console.Err.PrintErr(e.toString());
            }
            return 0;
        }

        //unload linen heap from wm
        if(actNum == Tag.UnloadWMLinenAndShow.getCode()){
            com.company.View.Console.Out.ShowLinens(db.getUsers().get(currentNickname).getWm().unload());
            return 0;
        }

        //run wm to working
        if(actNum == Tag.RunWM.getCode()){
            com.company.View.Console.Out.ShowRunWM(db.getUsers().get(currentNickname).getWm().run());
            return 0;
        }

        //show status of wm
        if(actNum == Tag.StatusWM.getCode()){
            com.company.View.Console.Out.ShowStatusWM(db.getUsers().get(currentNickname).getWm());
            return 0;
        }

        //add linen in dirty heap
        if(actNum == Tag.AddDirtyLinen.getCode()){
            com.company.View.Console.Out.ShowLinenColorStructure();
            db.getUsers().get(currentNickname).getDirtyHeap().add(new LinenColor(Controller.getInt(),Controller.getInt(),Controller.getColor()));
            return 0;
        }

        //shor dirty heap
        if(actNum == Tag.ShowDirtyLinen.getCode()){
            com.company.View.Console.Out.ShowLinens(db.getUsers().get(currentNickname).getDirtyHeap());
            return 0;
        }

        if(actNum == Tag.SaveChanges.getCode()){
            db.writeWMandLinens();
            Log.alwaysWrite("Safe changes of " + currentNickname);
            return 0;
        }

        //sign out
        if(actNum == Tag.SignOut.getCode()){
            db.writeWMandLinens();
            Log.alwaysWrite("Sign out" + currentNickname);
            return -1;
        }

        //if no actions was done
        com.company.View.Console.Err.PrintErr("no so action");
        return 0;
    }

    /**
     * do any action before authorization against tag's int code
     * @param actNum
     * @throws Exception
     */
    public static int runSign(int actNum) throws Exception {
        //in all if-s: ret 0 to continue, ret -1 to exit

        //sign in
        if(actNum == Tag.SignIn.getCode()){
            com.company.View.Console.Out.ShowMessage("Sign In:: ");
            com.company.View.Console.Out.ShowMessage("Write nickname: ");
            String nickname = Controller.getString();
            com.company.View.Console.Out.ShowMessage("Write password: ");
            String password = Controller.getString();

            if(db.getUsers().containsKey(nickname)) {
                if (db.getUsers().get(nickname).getPassword().equals(password)) {
                    currentNickname = nickname;
                    Log.isRun = db.getUsers().get(currentNickname).isDebug();
                    db.readWMandLinens();
                    if(db.getUsers().get(currentNickname).isAutotest()){
                        Autotest.run();
                    }
                    System.out.println("\n|___Hello_" + nickname + "___|");
                    Log.alwaysWrite("Sign in: " + nickname);
                    com.company.View.Console.Out.ShowActionsMenu();
                }
            }
            else{
                com.company.View.Console.Err.PrintErr("incorrect nickname or password");
                Log.alwaysWrite("incorrect nickname or password");
            }
            return 0;
        }

        //sign up
        if(actNum == Tag.SignUp.getCode()){
            com.company.View.Console.Out.ShowMessage("Sign Up:: ");
            com.company.View.Console.Out.ShowMessage("Write nickname: ");
            String nickname = Controller.getString();
            com.company.View.Console.Out.ShowMessage("Write password: ");
            String password = Controller.getString();
            AccesRights access;
            while(true){
                try{
                    com.company.View.Console.Out.ShowMessage("Write access (root or user): ");
                    access = AccesRights.tryConvert(Controller.getString());
                    break;
                }
                catch (Exception e){
                    com.company.View.Console.Err.PrintErr(e.toString());
                }
            }
            boolean debug, autotest;
            if(access.equals(AccesRights.user)){
                com.company.View.Console.Out.ShowMessage("Write isDebug: ");
                debug = Boolean.parseBoolean(Controller.getString());
                autotest = false;
            }
            else{
                debug = true;
                com.company.View.Console.Out.ShowMessage("Write isAutotest: ");
                autotest = Boolean.parseBoolean(Controller.getString());
            }
            UserInfo user = new UserInfo(password, access, debug, autotest);

            db.add(new UserInfo(password, access, debug, autotest), nickname);
            currentNickname = nickname;
            db.readWMandLinens();
            Log.isRun = user.isDebug();
            if(db.getUsers().get(currentNickname).isAutotest()){
                Autotest.run();
            }
            System.out.println("\n|___Hello_" + nickname + "___|");
            Log.alwaysWrite("Sign in: " + nickname);
            com.company.View.Console.Out.ShowActionsMenu();
            return 0;
        }

        //show all users
        if(actNum == Tag.ShowUsers.getCode()){
            com.company.View.Console.Out.ShowMessage(db.getUsersAsString());
            return 0;
        }

        //delete user
        if(actNum == Tag.DeleteUser.getCode()){
            com.company.View.Console.Out.ShowMessage("Write nickname: ");
            String nickname = Controller.getString();
            com.company.View.Console.Out.ShowMessage("Write password: ");
            String password = Controller.getString();
            if(db.getUsers().get(nickname).getPassword().equals(password)){
                db.delete(nickname);
            }
            Log.alwaysWrite("Delete user: " + nickname);
            return 0;
        }

        //end of program
        if(actNum == Tag.Exit.getCode()){
            Log.alwaysWrite("Exit");
            return -1;
        }

        //if no actions was done
        com.company.View.Console.Err.PrintErr("no so action");
        Log.alwaysWrite("no so action");
        return 0;
    }
}
