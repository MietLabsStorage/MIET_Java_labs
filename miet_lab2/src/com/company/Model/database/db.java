package com.company.Model.database;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class db {
    private static final String name = "database.txt";
    private static HashMap<String, UserInfo> users = new HashMap<String, UserInfo>();

    public static boolean add(UserInfo data, String nickname){
        try{
            users.putIfAbsent(nickname, data);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean delete(String nickname){
        try{
            users.remove(nickname);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private static String readStr(FileReader reader) throws IOException {
        int sym;
        String str = "";
        while((sym = reader.read()) != '\n'){
            str += ((char)sym);
        }
        return str;
    }
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
            return false;
        }
    }

    public static boolean write() throws IOException {
        try(FileWriter writer = new FileWriter(name, false))
        {
            writer.write(users.size());
            for (Map.Entry entry: users.entrySet()){
                writer.write(entry.getKey().toString() + "\n");
                writer.write(((UserInfo)entry.getValue()).getPassword() + "\n");
                writer.write(((UserInfo)entry.getValue()).getAcces().toString() + "\n");
                writer.write(Boolean.toString(((UserInfo)entry.getValue()).isDebug()) + "\n");
                writer.write(Boolean.toString(((UserInfo)entry.getValue()).isAutotest()) + "\n");
            }
            writer.flush();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static String showUsers(){
        String str = "";
        for (Map.Entry entry: users.entrySet()){
            str += entry.getKey().toString() + " " + ((UserInfo)entry.getValue()).getAcces().toString();
        }
        return str;
    }
}
