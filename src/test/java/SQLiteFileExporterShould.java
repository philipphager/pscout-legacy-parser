import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class SQLiteFileExporterShould {
  @Mock File file;
  @Mock FileUtils fileUtils;
  @InjectMocks SQLiteFileExport sqLiteFileExport;

  @Before public void setUp() throws Exception {

  }

  @Test public void createEmptyFileIfNoMethodsAvailable() {
    List<Method> methods = new ArrayList<>(0);
    sqLiteFileExport.write(file, methods);

    Mockito.verify(fileUtils, Mockito.never()).print(any(File.class), anyString());
  }

  @Test public void createJavaAndSqlCommandsForOneMethod() {
    List<Method> methods = new ArrayList<>(1);
    methods.add(new Method("android.permission.INTERNET", "com/test/TestClass", "Ljava/lang/String", "test", "I"));
    String expected = "db.execSQL(\"INSERT INTO ProtectedMethod VALUES('android.permission.INTERNET','com/test/TestClass','Ljava/lang/String','test','I');\");\n";

    sqLiteFileExport.write(file, methods);

    Mockito.verify(fileUtils).print(file, expected);
  }

  @Test public void createJavaAndSqlCommandsForMultipleMethods() {
    List<Method> methods = new ArrayList<>(3);
    methods.add(new Method("android.permission.INTERNET", "com/test/TestClass", "Ljava/lang/String", "test", "I"));
    methods.add(new Method("android.permission.CAMERA", "com/test/TestClass1", "Ljava/lang/String1", "test1", "V"));
    methods.add(new Method("android.permission.READ_CONTACTS", "com/test/TestClass2", "Ljava/lang/String2", "test2", "Z"));
    String expectedFirstLine = "db.execSQL(\"INSERT INTO ProtectedMethod VALUES('android.permission.INTERNET','com/test/TestClass','Ljava/lang/String','test','I');\");\n";
    String expectedSecondLine = "db.execSQL(\"INSERT INTO ProtectedMethod VALUES('android.permission.CAMERA','com/test/TestClass1','Ljava/lang/String1','test1','V');\");\n";
    String expectedThirdLine = "db.execSQL(\"INSERT INTO ProtectedMethod VALUES('android.permission.READ_CONTACTS','com/test/TestClass2','Ljava/lang/String2','test2','Z');\");\n";

    sqLiteFileExport.write(file, methods);

    Mockito.verify(fileUtils).print(file, expectedFirstLine);
    Mockito.verify(fileUtils).print(file, expectedSecondLine);
    Mockito.verify(fileUtils).print(file, expectedThirdLine);
  }
}
