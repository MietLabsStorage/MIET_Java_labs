import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Main {
    static JFrame frame;
    static JTextField textFieldAddress;
    static JButton buttonConnect;
    static JButton buttonDownload;

    static Socket socket = null;

    public static void connect() {
        try {
            String[] uri = textFieldAddress.getText().split(":");
            socket = new Socket(uri[0], Integer.parseInt(uri[1]));
            buttonConnect.setText("disconnect");
            textFieldAddress.setEditable(false);
            buttonDownload.setVisible(true);
        } catch (Exception e) {
            System.out.println("ошибка: " + e);
        }
    }

    public static void disconnect() {
        try {
            socket.close();
            buttonConnect.setText("connect");
            textFieldAddress.setEditable(true);
            buttonDownload.setVisible(false);
        } catch (Exception e) {
            System.out.println("ошибка: " + e);
        }
    }

    public static void downloadFile() {
        try {
            /*BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = dis.readLine();
            System.out.println(msg);*/

            BufferedOutputStream bis = new BufferedOutputStream(new Output("somefile.dat"));
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            byte[] byteArray = new byte[8192];
            int in;
            while ((in = bis.read(byteArray)) != -1){
                bos.write(byteArray,0,in);
            }
            bis.close();
            bos.close();

            connect();
        }
        catch (Exception e){
            System.out.println("ошибка: " + e);
        }
    }

    public static void runAndListen() {
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonConnect.getText().equals("connect")) {
                    connect();
                } else {
                    disconnect();
                }
            }
        });
        buttonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldAddress.getText().equals("127.0.0.1:20") || textFieldAddress.getText().equals("localhost:20")){
                    downloadFile();
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Client");
        frame.setSize(200, 200);
        frame.setResizable(false);

        textFieldAddress = new JTextField("127.0.0.1:0");
        textFieldAddress.setBounds(20, 20, 150, 20);
        textFieldAddress.setVisible(true);

        buttonConnect = new JButton("connect");
        buttonConnect.setBounds(textFieldAddress.getX(), textFieldAddress.getY() + textFieldAddress.getHeight() + 10, 150, 30);
        buttonConnect.setVisible(true);

        buttonDownload = new JButton("download file");
        buttonDownload.setBounds(textFieldAddress.getX(), textFieldAddress.getY() + textFieldAddress.getHeight() + 70, 150, 30);
        buttonDownload.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(textFieldAddress);
        panel.add(buttonConnect);
        panel.add(buttonDownload);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runAndListen();
            }
        });
    }
}
