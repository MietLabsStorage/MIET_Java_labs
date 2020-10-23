package com.company.model.database;

import com.company.View.Log;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class db {

    public static HashMap<String, UserInfo> getUsers() {
        return users;
    }

    private static final String name = "database.txt";
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
            write();
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
            write();
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
     * read db from file
     * @return true if success else false
     */
    public static boolean read(){
        try(FileReader reader = new FileReader(name))
        {
            int count = Integer.parseInt(readStr(reader));
            for(int i = 0; i < count; i++){
                String nickname = readStr(reader);
                String password = readStr(reader);
                AccesRights acces = AccesRights.tryConvert(readStr(reader));
                boolean debug = Boolean.parseBoolean(readStr(reader));
                boolean autotest = Boolean.parseBoolean(readStr(reader));
                users.putIfAbsent(nickname, new UserInfo(password, acces, debug, autotest));
            }
            return true;
        }
        catch(Exception e){
            Log.tryWrite(e.toString());
            return false;
        }
    }

    //write db in file
    private static boolean write() throws IOException {
        try(FileWriter writer = new FileWriter(name, false))
        {
            writer.write(users.size() + "\n");
            for (Map.Entry entry: users.entrySet()){
                writer.write(entry.getKey().toString() + "\n");
                writer.write(((UserInfo)entry.getValue()).getPassword() + "\n");
                writer.write(((UserInfo)entry.getValue()).getAcces().toString() + "\n");
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
     * create string of users nicknames and acces rights
     * @return string of users
     */
    public static String getUsersAsString(){
        String str = "Users: \n";
        for (Map.Entry entry: users.entrySet()){
            str += entry.getKey().toString() + " " + ((UserInfo)entry.getValue()).getAcces().toString() + "\n";
        }
        return str;
    }
}
