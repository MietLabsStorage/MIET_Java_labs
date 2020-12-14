import java.io.*;
import java.net.*;

public class Main{
    static int count = 0;
    public static void main(String[] args) throws Exception{
        Socket socket = null;

        System.out.println("Server start");
        try {
            ServerSocket server = new ServerSocket(20);
            while(true)
            {
                socket = server.accept();
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("somefile.dat"));
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                byte[] byteArray = new byte[8192];
                int in;
                while ((in = bis.read(byteArray)) != -1){
                    bos.write(byteArray,0,in);
                }
                bis.close();
                bos.close();
                //PrintStream ps = new PrintStream(socket.getOutputStream());
                //ps.println("привет!"+(count++));
                //ps.flush();

                socket.close();
            }

        }catch (IOException e){System.out.println("ошибка: " + e); }
        finally {
            System.out.println("Server close");
        }
    }
}
