import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Main {
    static JFrame frame;
    static JTextField textFieldHost;
    static JTextField textFieldPort;
    static JButton buttonConnect;

    public static void connect(){
        Socket socket = null;
        try {//получение строки клиентом
            socket = new Socket("localhost", 20);
            BufferedReader dis = new BufferedReader(new
                    InputStreamReader(socket.getInputStream()));
            String msg = dis.readLine();
            System.out.println(msg);
        } catch (IOException e) {
            System.out.println("ошибка: " + e);
        }
    }

    public static void runAndListen(){
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Client");
        frame.setSize(200, 200);
        frame.setResizable(false);

        textFieldHost = new JTextField("host");
        textFieldHost.setBounds(20,20,100,20);
        textFieldHost.setVisible(true);

        textFieldPort = new JTextField("port");
        textFieldPort.setBounds(textFieldHost.getX()+textFieldHost.getWidth()+5,20,50,20);
        textFieldPort.setVisible(true);

        buttonConnect = new JButton("connect");
        buttonConnect.setBounds(textFieldHost.getX(), textFieldHost.getY()+textFieldHost.getHeight()+10,100,30);
        buttonConnect.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(textFieldHost);
        panel.add(textFieldPort);
        panel.add(buttonConnect);

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
