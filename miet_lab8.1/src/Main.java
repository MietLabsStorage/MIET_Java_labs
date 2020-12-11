import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        String outputFile = "index.html";

        String strURL = "https://habr.com/ru/";
        //String strURL = "https://ru.wikipedia.org/wiki/Заглавная_страница";
        //String strURL = "https://old.miet.ru/structure/s/248";
        //String strURL = "https://javarush.ru";
        //String strURL = "http://institut-spintex.ru/about/";

        System.out.println("Start loading...");
        try {
            URL url = new URL(strURL);

            //очистка файла
            try(FileWriter writer = new FileWriter(outputFile, false)) {
                //just cleaning file
            }

            //загрузка исходного текста
            try {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.openStream()));
                String string = reader.readLine();
                while (string != null) {
                    try(FileWriter writer = new FileWriter(outputFile, true))
                    {
                        // запись строки в файл
                        writer.write(string);
                        writer.append('\n');
                        writer.flush();
                    }
                    string = reader.readLine();
                }
                reader.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }

        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("Finish loading");
    }
}