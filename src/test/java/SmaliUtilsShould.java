import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SmaliUtilsShould {
  @Test public void mapPrimitives() {
    List<String> primitives =
        Arrays.asList("void", "boolean", "byte", "short", "char", "int", "float", "long", "double");
    List<String> mappedPrimitives = new ArrayList<>(9);

    for (String primitive : primitives) {
      mappedPrimitives.add(SmaliUtils.smalifyType(primitive));
    }

    assertThat(mappedPrimitives).containsExactly("V", "Z", "B", "S", "C", "I", "F", "J", "D");
  }

  @Test public void mapArrayOfPrimitives() {
    List<String> primitives =
        Arrays.asList("void", "boolean[]", "byte[]", "short[][]", "char[]", "int[][][]", "float[]",
            "long[]", "double[]");
    List<String> mappedPrimitives = new ArrayList<>(9);

    mappedPrimitives.addAll(primitives.stream()
        .map(SmaliUtils::smalifyType)
        .collect(Collectors.toList()));

    assertThat(mappedPrimitives).containsExactly("V", "[Z", "[B", "[[S", "[C", "[[[I", "[F", "[J", "[D");
  }

  @Test public void mapReferenceTypes() {
    List<String> referenceTypes = Arrays.asList("java.util.String", "android.net.wifi.WifiManager");
    List<String> mappedPrimitives = new ArrayList<>(2);

    mappedPrimitives.addAll(referenceTypes.stream()
        .map(SmaliUtils::smalifyType)
        .collect(Collectors.toList()));

    assertThat(mappedPrimitives).containsExactly("Ljava/util/String;", "Landroid/net/wifi/WifiManager;");
  }

  @Test public void mapArrayOfReferenceTypes() {
    List<String> referenceTypes = Arrays.asList("java.util.String[][]", "android.net.wifi.WifiManager[]");
    List<String> mappedPrimitives = new ArrayList<>(2);

    mappedPrimitives.addAll(referenceTypes.stream()
        .map(SmaliUtils::smalifyType)
        .collect(Collectors.toList()));

    assertThat(mappedPrimitives).containsExactly("[[Ljava/util/String;", "[Landroid/net/wifi/WifiManager;");
  }

  @Test public void stripWhiteSpaces() {
    List<String> referenceTypes = Arrays.asList("java.util.String [] ", " boolean ");
    List<String> mappedPrimitives = new ArrayList<>(2);

    mappedPrimitives.addAll(referenceTypes.stream()
        .map(SmaliUtils::smalifyType)
        .collect(Collectors.toList()));

    assertThat(mappedPrimitives).containsExactly("[Ljava/util/String;", "Z");
  }
}
