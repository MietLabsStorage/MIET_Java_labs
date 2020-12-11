import java.io.*;
import java.net.*;

public class Main{
    static int count = 0;
    public static void main(String[] args) throws Exception{
        Socket socket = null;

        System.out.println("Server start");
        try {//посылка строки клиенту
            ServerSocket server = new ServerSocket(20);
            while(true)
            {
                socket = server.accept();
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println("привет!"+(count++));
                ps.flush();
                socket.close(); // разрыв соединения
            }

        }catch (IOException e){System.out.println("ошибка: " + e); }
        finally {
            System.out.println("Server close");
        }
    }
}
