package org.openlca.simapro.csv;

class Strings {

  private Strings() {
  }

  static boolean isEmpty(String s) {
    return s.isBlank();
  }

  static boolean startsWith(String s, String prefix) {
    return s.toLowerCase().startsWith(prefix.toLowerCase());
  }

}
