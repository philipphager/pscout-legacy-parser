public class Method {
  public final String permissionId;
  public final String declaringType;
  public final String returnType;
  public final String name;
  public final String argsTypes;

  public Method(String permissionId, String declaringType, String returnType, String name,
      String argsTypes) {
    this.permissionId = permissionId;
    this.declaringType = declaringType;
    this.returnType = returnType;
    this.name = name;
    this.argsTypes = argsTypes;
  }

  @Override public String toString() {
    return String.format("%s.%s(%s) -> %s", declaringType, name, argsTypes, returnType);
  }

  public String toCSV() {
    return String.format("\"%s\",\"%s\",\"%s\",\"%s\"\n", permissionId, declaringType, name,
        returnType);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Method method = (Method) o;

    if (permissionId != null ? !permissionId.equals(method.permissionId)
        : method.permissionId != null) {
      return false;
    }
    if (declaringType != null ? !declaringType.equals(method.declaringType)
        : method.declaringType != null) {
      return false;
    }
    if (returnType != null ? !returnType.equals(method.returnType) : method.returnType != null) {
      return false;
    }
    if (name != null ? !name.equals(method.name) : method.name != null) return false;
    return argsTypes != null ? argsTypes.equals(method.argsTypes) : method.argsTypes == null;
  }

  @Override public int hashCode() {
    int result = permissionId != null ? permissionId.hashCode() : 0;
    result = 31 * result + (declaringType != null ? declaringType.hashCode() : 0);
    result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (argsTypes != null ? argsTypes.hashCode() : 0);
    return result;
  }
}
