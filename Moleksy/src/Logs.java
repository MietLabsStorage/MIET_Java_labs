import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * class for writing logs in file
 */
public class Logs {
    private static final String name = "log.txt";
    private static boolean rewrite = true;

    /**
     * write new line in logs-file
     *
     * @param msg message-line
     * @return succession of writing
     */
    public static boolean writeMessage(String msg) {
        if (rewrite) {
            try (FileWriter writer = new FileWriter(name, false)) {
                rewrite = false;
            } catch (Exception e) {
            }
        }

        try (FileWriter writer = new FileWriter(name, true)) {
            writer.write(LocalDate.now() + " " +
                    LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss")) + "|\t" + msg + "\n");
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
