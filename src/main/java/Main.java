import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Provide pscout file and output directory");
    }

    File inFile = new File(args[0]);
    File outFile = new File(String.format("%s%sprotected-methods.txt", args[1], File.separator));

    if (!inFile.exists()) {
      throw new IllegalArgumentException("Must provide valid path to Pscout file");
    }

    // Change for CSV, SQL, or Java SQLite output.
    FormattedFileExport exporter = new SQLiteFileExport(new FileUtils());

    FileParser fileParser = new FileParser(inFile);
    List<Method> methods = fileParser.extractMethods();
    exporter.write(outFile, methods);

    methods.stream()
        .map(method -> method.permissionId)
        .collect(Collectors.toList());
  }
}
