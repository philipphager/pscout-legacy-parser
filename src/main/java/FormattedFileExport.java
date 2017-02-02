import java.io.File;
import java.util.List;

public interface FormattedFileExport {
  void write(File out, List<Method> methods);
}
