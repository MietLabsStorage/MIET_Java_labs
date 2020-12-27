import Exceptions.JException;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * class for writing logs in file
 */
public class Logs {
    private static final String name = "Logs/log";
    private static String dt;

    public static void init(){
        dt = LocalDate.now()+"-"+LocalTime.now().format(DateTimeFormatter.ofPattern("H-mm-ss"));
    }

    /**
     * write new line in logs-file
     *
     * @param msg message-line
     */
    public static void writeMessage(String msg) {

        try (FileWriter writer = new FileWriter(name+dt+".txt", true)) {
            writer.write(LocalDate.now() + " " +
                    LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss")) + "|\t" + msg + "\n");
            writer.flush();
        } catch (Exception e) {
            new JException(e.getMessage());
        }

    }
}
