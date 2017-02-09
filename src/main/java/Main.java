import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  @Parameter(names = {"--pscoutInput",
      "-i"}, required = true, description = "Path to your PScout legacy file.")
  String pscoutInputFile;

  @Parameter(names = {"--outputDir",
      "-o"}, required = true, description = "Path to your output directory.")
  String outputDir;

  @Parameter(names = {"--help", "-h"})
  private boolean help = false;

  public static void main(String[] args) {
    Main main = new Main();
    JCommander jCommander = new JCommander(main);

    try {
      jCommander.parse(args);

      if (main.help) {
        jCommander.usage();
        return;
      }

      main.run();
    } catch (Exception e) {
      jCommander.usage();
    }
  }

  public void run() {
    File inFile = new File(pscoutInputFile);
    File outDir = new File(outputDir);

    Assertions.assertTrue(inFile.exists(), "Please provide a valid input file!");
    Assertions.assertTrue(outDir.exists(), "Please provide a valid output directory!");

    File outFile = new File(String.format("%s%spscout-mapping.csv", outputDir, File.separator));

    // Change for CSV, SQL, or Java SQLite output.
    FormattedFileExport exporter = new CSVFileExport(new FileUtils());

    FileParser fileParser = new FileParser(inFile);
    List<Method> methods = fileParser.extractMethods();
    exporter.write(outFile, methods);

    methods.stream()
        .map(method -> method.permissionId)
        .collect(Collectors.toList());
  }
}
