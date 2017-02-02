import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
  public void print(File outputFile, String method) {
    try (FileWriter fileWriter = new FileWriter(outputFile, true)) {
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(method);
      bufferedWriter.flush();
      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
