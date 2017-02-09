public final class Assertions {

  public static void assertTrue(boolean argument, String message) {
    if (!argument) {
      throw new IllegalStateException(message);
    }
  }

  private Assertions() {
    // No instances of util classes.
  }
}
