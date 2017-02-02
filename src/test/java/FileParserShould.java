import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileParserShould {
  private ClassLoader classLoader;
  private FileParser fileParser;

  @Before public void setUp() throws Exception {
    classLoader = getClass().getClassLoader();
  }

  @Test public void returnEmptyListOfMethodsForEmptyFile() {
    File emptyFile = new File(classLoader.getResource("empty.txt").getFile());

    fileParser = new FileParser(emptyFile);

    assertThat(fileParser.extractMethods()).isEqualTo(new ArrayList<Method>(0));
  }

  @Test public void ignorePermissionIdIfNotGiven() {
    File noPermissionFile = new File(classLoader.getResource("no-permission.txt").getFile());

    fileParser = new FileParser(noPermissionFile);

    assertThat(fileParser.extractMethods()).containsExactly(
        new Method("", "Landroid/net/wifi/WifiManager;", "Z", "reassociate", ""),
        new Method("", "Landroid/net/wifi/WifiManager;", "Z", "startScan", ""),
        new Method("", "Landroid/net/wifi/WifiManager;", "V", "setCountryCode",
            "Ljava/lang/String;Z")
    );
  }

  @Test public void extractAllValidPermissionsFromFile() {
    File validFile = new File(classLoader.getResource("valid.txt").getFile());

    fileParser = new FileParser(validFile);

    assertThat(fileParser.extractMethods()).containsExactly(
        new Method("android.permission.CHANGE_WIFI_STATE", "Landroid/net/wifi/WifiManager;", "Z",
            "reassociate", ""),
        new Method("android.permission.CHANGE_WIFI_STATE", "Landroid/net/wifi/p2p/WifiP2pManager;",
            "Landroid/net/wifi/p2p/WifiP2pManager$Channel;", "initialize",
            "Landroid/content/Context;Landroid/os/Looper;Landroid/net/wifi/p2p/WifiP2pManager$ChannelListener;"),
        new Method("android.permission.READ_PHONE_STATE", "Landroid/telephony/TelephonyManager;",
            "Ljava/lang/String;", "getSubscriberId", "")
    );
  }
}
