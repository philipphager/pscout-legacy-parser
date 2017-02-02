import java.io.File;
import java.util.List;

public class SQLFileExport implements FormattedFileExport {
  private static final String SQL_FORMAT =
      "INSERT INTO ProtectedMethod VALUES('%s', '%s','%s','%s','%s')\n";
  private final FileUtils fileUtils;

  public SQLFileExport(FileUtils fileUtils) {
    this.fileUtils = fileUtils;
  }

  @Override public void write(File out, List<Method> methods) {
    for (Method method : methods) {
      fileUtils.print(out, String.format(SQL_FORMAT,
          method.permissionId,
          method.declaringType,
          method.returnType,
          method.name,
          method.argsTypes));
    }
  }
}
