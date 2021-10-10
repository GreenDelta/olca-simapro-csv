package org.openlca.simapro.csv.process;

import java.util.regex.Pattern;

class PedigreeMatcher {

  private static Pattern _pattern;

  private static Pattern pattern() {
    if (_pattern != null)
      return _pattern;
    _pattern = Pattern.compile(
      "\\(\\s*([1-5]|na)\\s*,\\s*([1-5]|na)\\s*,\\s*([1-5]|na)"
      + "\\s*,\\s*([1-5]|na)\\s*,\\s*([1-5]|na)\\s*,\\s*([1-5]|na)\\s*\\)");
    return _pattern;
  }

  static String match(String comment) {
    if (comment == null || comment.length() < 3)
      return null;
    var matcher = pattern().matcher(comment);
    return matcher.find()
      ? matcher.group()
      : null;
  }
}
