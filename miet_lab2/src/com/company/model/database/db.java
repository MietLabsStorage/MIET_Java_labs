package com.company.model.database;

import com.company.View.Log;
import com.company.model.Actions;
import com.company.model.WashingMachine;
import com.company.model.clothes.Color;
import com.company.model.clothes.LinenColor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * database
 */
public class db {

    public static HashMap<String, UserInfo> getUsers() {
        return users;
    }

    public static final String name = "database.txt";
    private static HashMap<String, UserInfo> users = new HashMap<String, UserInfo>();

    /**
     * add user in db
     * @param data users data
     * @param nickname users nickname
     * @return true if success else false
     */
    public static boolean add(UserInfo data, String nickname){
        try{
            users.putIfAbsent(nickname, data);
            writeUsers();
            return true;
        }
        catch (Exception e){
            Log.tryWrite(e.toString());
            return false;
        }
    }

    /**
     * delete user from db
     * @param nickname users nickname
     * @return true if success else false
     */
    public static boolean delete(String nickname){
        try{
            users.remove(nickname);
            writeUsers();
            return true;
        }
        catch (Exception e){
            Log.tryWrite(e.toString());
            return false;
        }
    }

    //read str from file and return it
    private static String readStr(FileReader reader) throws IOException {
        int sym;
        String str = "";
        while((sym = reader.read()) != '\n'){
            str += ((char)sym);
        }
        return str;
    }

    /**
     * read users from file
     * @return true if success else false
     */
    public static boolean readUsers(){
        try(FileReader reader = new FileReader(name))
        {
            int count = Integer.parseInt(readStr(reader));
            for(int i = 0; i < count; i++){
                String nickname = readStr(reader);
                String password = readStr(reader);
                AccesRights access = AccesRights.tryConvert(readStr(reader));
                boolean debug = Boolean.parseBoolean(readStr(reader));
                boolean autotest = Boolean.parseBoolean(readStr(reader));
                users.putIfAbsent(nickname, new UserInfo(password, access, debug, autotest));
            }
            return true;
        }
        catch(Exception e){
            Log.tryWrite(e.toString());
            return false;
        }
    }

    //write users in file
    private static boolean writeUsers() throws IOException {
        try(FileWriter writer = new FileWriter(name, false))
        {
            writer.write(users.size() + "\n");
            for (Map.Entry entry: users.entrySet()){
                writer.write(entry.getKey().toString() + "\n");
                writer.write(((UserInfo)entry.getValue()).getPassword() + "\n");
                writer.write(((UserInfo)entry.getValue()).getAccess().toString() + "\n");
                writer.write(Boolean.toString(((UserInfo)entry.getValue()).isDebug()) + "\n");
                writer.write(Boolean.toString(((UserInfo)entry.getValue()).isAutotest()) + "\n");
            }
            writer.flush();
            Log.tryWrite("Update bd");
            return true;
        }
        catch (Exception e){
            Log.tryWrite(e.toString());
            return false;
        }
    }

    /**
     * read wm from file
     * @return true if success else false
     */
    public static boolean readWMandLinens() throws IOException {
        users.get(Actions.getCurrentNickname()).setWm(new WashingMachine());
        try(FileReader reader = new FileReader((Actions.getCurrentNickname())+name))
        {
            String conditioner = readStr(reader);
            String powder = readStr(reader);
            int temperature = Integer.parseInt(readStr(reader));
            Color color = Color.tryConvert(readStr(reader));
            users.get(Actions.getCurrentNickname()).getWm().setPowder(powder);
            users.get(Actions.getCurrentNickname()).getWm().setConditioner(conditioner);
            users.get(Actions.getCurrentNickname()).getWm().setColor(color);
            users.get(Actions.getCurrentNickname()).getWm().setTemperature(temperature);
            int size = Integer.parseInt(readStr(reader));
            ArrayList<LinenColor> linens = new ArrayList<>();
            for(int i = 0; i < size; i++){
                int ltemperatureWashining = Integer.parseInt(readStr(reader));
                int ltemperatureIroning = Integer.parseInt(readStr(reader));
                Color lcolor = Color.tryConvert(readStr(reader));
                linens.add(new LinenColor(ltemperatureWashining, ltemperatureIroning, lcolor));
            }
            users.get(Actions.getCurrentNickname()).getWm().load(linens);

            size = Integer.parseInt(readStr(reader));
            for(int i = 0; i < size; i++){
                int ltemperatureWashining = Integer.parseInt(readStr(reader));
                int ltemperatureIroning = Integer.parseInt(readStr(reader));
                Color lcolor = Color.tryConvert(readStr(reader));
                users.get(Actions.getCurrentNickname()).getDirtyHeap().add(new LinenColor(ltemperatureWashining, ltemperatureIroning, lcolor));
            }

            return true;
        }
        catch(Exception e){
            Log.tryWrite("BD user read: " + e.toString());
            return false;
        }
    }

    //write wm in file
    public static boolean writeWMandLinens() throws IOException {
        try(FileWriter writer = new FileWriter((Actions.getCurrentNickname())+name, false))
        {
            String s = Actions.getCurrentNickname();
            UserInfo user = users.get(s);
            WashingMachine washingMachine = user.getWm();

            writer.write(washingMachine.getConditioner() + "\n");
            writer.write(washingMachine.getPowder() + "\n");
            writer.write(washingMachine.getTemperature() + "\n");
            writer.write(washingMachine.getColor().toString() + "\n");
            writer.write(washingMachine.getInputLinens().size() + "\n");
            for(LinenColor linen : washingMachine.getInputLinens()){
                writer.write(linen.getTemperatureWashing()+ "\n");
                writer.write(linen.getTemperatureIroning()+ "\n");
                writer.write(linen.getColor().toString()+ "\n");
            }
            writer.write(user.getDirtyHeap().size()+ "\n");
            for(LinenColor linen : user.getDirtyHeap()){
                writer.write(linen.getTemperatureWashing()+ "\n");
                writer.write(linen.getTemperatureIroning()+ "\n");
                writer.write(linen.getColor().toString()+ "\n");
            }
            writer.flush();
            Log.tryWrite("Update bd");
            return true;
        }
        catch (Exception e){
            Log.tryWrite("BR user write:" + e.toString());
            return false;
        }
    }

    /**
     * create string of users nicknames and acces rights
     * @return string of users
     */
    public static String getUsersAsString(){
        String str = "Users: \n";
        for (Map.Entry entry: users.entrySet()){
            str += entry.getKey().toString() + " access(" + ((UserInfo)entry.getValue()).getAccess().toString() +
                    ") debug(" + ((UserInfo)entry.getValue()).isDebug() + ") autotest(" + ((UserInfo)entry.getValue()).isAutotest() +
                    ")\n";
        }
        return str;
    }
}
