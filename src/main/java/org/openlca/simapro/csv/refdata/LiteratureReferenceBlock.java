package org.openlca.simapro.csv.refdata;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

import java.util.function.Supplier;

public class LiteratureReferenceBlock implements CsvBlock, CsvRecord {
  private String name;
  private String documentationLink;
  private String category;
  private String description;

  public String name() { return name; }

  public LiteratureReferenceBlock name(String name) {
    this.name = name;
    return this;
  }

  public String documentationLink() { return documentationLink; }

  public LiteratureReferenceBlock documentationLink(String documentationLink) {
    this.documentationLink = documentationLink;
    return this;
  }

  public String category() { return category; }

  public LiteratureReferenceBlock category(String category) {
    this.category = category;
    return this;
  }

  public String description() { return description; }

  public LiteratureReferenceBlock description(String description) {
    this.description = description;
    return this;
  }

  public static LiteratureReferenceBlock read(Iterable<CsvLine> lines) {
    var block = new LiteratureReferenceBlock();
    var iter = lines.iterator();
    Supplier<String> nextFirst = () -> CsvLine.nextOf(iter)
      .map(CsvLine::first)
      .orElse("");

    while (iter.hasNext()) {

      var line = iter.next();
      if (line.isEmpty())
        continue;
      var header = line.first();
      if (header.equalsIgnoreCase("End"))
        break;
      if (header.isEmpty())
        continue;

      switch (header) {

        case "Name":
          block.name(nextFirst.get());
          break;

        case "Documentation link":
          block.documentationLink(nextFirst.get());
          break;

        case "Category":
          block.category(nextFirst.get());
          break;

        case "Description":
          block.description(nextFirst.get());
          break;

      }
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Literature reference").writeln()
      .writeln()
      .putString("Name").writeln()
      .putString(name).writeln()
      .writeln()
      .putString("Documentation link").writeln()
      .putString(documentationLink).writeln()
      .writeln()
      .putString("Category").writeln()
      .putString(category).writeln()
      .writeln()
      .putString("Description").writeln()
      .putString(description).writeln()
      .writeln()
      .putString("End").writeln()
      .writeln();
  }

}
