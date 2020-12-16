import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {
    static int count = 0;
    static Socket socket = null;
    static ServerSocket server = null;

    public static void serverWork() throws Exception {
        try {
            server = new ServerSocket(20);
            while (true) {
                socket = server.accept();

                BufferedReader inputMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter outputMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String filename = inputMessage.readLine();
                File file = new File(filename);

                if (file.exists()) {
                    outputMessage.write("You load file:: " + filename + "\n");
                    outputMessage.flush();

                    DataOutputStream outputFile = new DataOutputStream(socket.getOutputStream());

                    FileInputStream fileStream = new FileInputStream(file);
                    long length = file.length();
                    byte[] bytes = new byte[(int) length];
                    int count;

                    outputFile.writeLong(length);
                    while ((count = fileStream.read(bytes)) != -1) {
                        outputFile.write(bytes, 0, count);
                    }
                    fileStream.close();
                    outputFile.close();
                }
                else{
                    outputMessage.write("NOT_EXIST");
                    outputMessage.flush();
                }
                socket.close();
            }
        }  catch (Exception) {
            server.close();
            serverWork();
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server start");
        serverWork();
    }
}
