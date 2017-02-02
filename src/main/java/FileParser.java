import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
  private final Pattern permissionHeader = Pattern.compile("Permission:(.*)");
  private final Pattern methodSignature = Pattern.compile("<(.*):\\s(.*)\\s(.*)\\((.*)\\)>");
  private final int EXPECTED_PERMISSION_NUMBER = 800;
  private final File file;

  public FileParser(File file) {
    this.file = file;
  }

  public List<Method> extractMethods() {
    List<Method> methods = new ArrayList<>(EXPECTED_PERMISSION_NUMBER);

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String permissionId = "";

      for (String line; (line = reader.readLine()) != null; ) {
        Matcher permissionMatcher = permissionHeader.matcher(line);

        if (permissionMatcher.find()) {
          permissionId = permissionMatcher.group(1);

          // Skip line with permission description.
          reader.readLine();
        }

        Matcher methodMatcher = methodSignature.matcher(line);

        if (methodMatcher.find()) {
          String declaringType = SmaliUtils.smalifyType(methodMatcher.group(1));
          String returnType = SmaliUtils.smalifyType(methodMatcher.group(2));
          String name = methodMatcher.group(3).replace("\\s", "");

          String[] args = methodMatcher.group(4).split(",");
          String argTypes = "";

          for (String arg : args) {
            argTypes += SmaliUtils.smalifyType(arg);
          }

          methods.add(new Method(permissionId, declaringType, returnType, name, argTypes));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      return methods;
    }
  }
}
