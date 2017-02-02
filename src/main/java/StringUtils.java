public class StringUtils {
  private static final int NOT_FOUND = -1;

  public static int countMatches(final CharSequence str, final CharSequence sub) {
    if (isEmpty(str) || isEmpty(sub)) {
      return 0;
    }

    int count = 0;
    int idx = 0;

    while ((idx = indexOf(str, sub, idx)) != NOT_FOUND) {
      count++;
      idx += sub.length();
    }

    return count;
  }

  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  public static int indexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
    return cs.toString().indexOf(searchChar.toString(), start);
  }
}
