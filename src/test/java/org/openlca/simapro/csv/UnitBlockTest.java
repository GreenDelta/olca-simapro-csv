package org.openlca.simapro.csv;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;
import org.openlca.simapro.csv.refdata.UnitBlock;

import static org.junit.Assert.*;

public class UnitBlockTest {

  @Test
  public void testRead() throws Exception {
    var stream = getClass().getResourceAsStream("process.csv");
    assertNotNull(stream);
    try (var reader = Csv.readerOf(stream, StandardCharsets.UTF_8)) {
      var scanner = CsvScanner.of(reader);
      var ref = new AtomicReference<UnitBlock>();
      scanner.eachWhile(line -> {
        if (line.isUnitsStart()) {
          var block = UnitBlock.read(scanner);
          ref.set(block);
          return false;
        }
        return true;
      });

      var block = ref.get();
      assertNotNull(block);

      for (var unit : block.units()) {
        System.out.println(unit.name());
      }

    }
  }

}
