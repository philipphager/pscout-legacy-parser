import java.io.File;
import java.util.List;

public class CSVFileExport implements FormattedFileExport {
  private static final String CSV_FORMAT = "\"%s\", \"%s\",\"%s\",\"%s\",\"%s\"\n";
  private final FileUtils fileUtils;

  public CSVFileExport(FileUtils fileUtils) {
    this.fileUtils = fileUtils;
  }

  @Override public void write(File out, List<Method> methods) {
    fileUtils.print(out, String.format(CSV_FORMAT,
        "permissionId",
        "declaringType",
        "returnType", "name",
        "argTypes"));

    for (Method method : methods) {
      fileUtils.print(out, String.format(CSV_FORMAT,
          method.permissionId,
          method.declaringType,
          method.returnType,
          method.name,
          method.argsTypes));
    }
  }
}
