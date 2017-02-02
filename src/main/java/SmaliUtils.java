public class SmaliUtils {

  public static String smalifyType(String javaType) {
    if (StringUtils.isEmpty(javaType)) return "";

    String smaliType;

    // Strip whitespaces
    javaType = javaType.replaceAll("\\s","");

    //Search for arrays
    int arrayDefs = StringUtils.countMatches(javaType, "[]");

    // Strip arrays
    javaType = javaType.replaceAll("\\[\\]","");

    String primitive = mapPrimitive(javaType);

    // java type is primitive
    smaliType = primitive != null ? primitive : convertToReferemce(javaType);

    // prepend arrays
    for (int i = 0; i < arrayDefs; i++) {
      smaliType = "[" + smaliType;
    }

    return smaliType;
  }

  private static String convertToReferemce (String type) {
    return ("L" + type + ";").replace(".", "/");
  }

  private static String mapPrimitive(String type) {
    switch (type) {
      case "void":
        return "V";
      case "boolean":
        return "Z";
      case "char":
        return "C";
      case "byte":
        return "B";
      case "short":
        return "S";
      case "int":
        return "I";
      case "long":
        return "J";
      case "float":
        return "F";
      case "double":
        return "D";
      default:
        return null;
    }
  }
}
