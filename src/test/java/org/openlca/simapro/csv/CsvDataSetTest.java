package org.openlca.simapro.csv;

import junit.framework.TestCase;
import org.junit.Test;
import org.openlca.simapro.csv.enums.ProcessCategory;
import org.openlca.simapro.csv.enums.ProcessType;
import org.openlca.simapro.csv.enums.Status;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvDataSetTest extends TestCase {

  @Test
  public void testFileSave() throws IOException, URISyntaxException {
    File file = File.createTempFile("tmp", "csv");
    file.deleteOnExit();
    CsvDataSet csvDataSet = Tests.testDataSet("process.csv");

    try (var writer = new FileWriter(file, SimaProCsv.defaultCharset())) {
      csvDataSet.write(new CsvBuffer(writer, csvDataSet.header()));
    } catch (IOException e) {
      throw new RuntimeException("failed to write file: " + file, e);
    }

    CsvDataSet dataSet = SimaProCsv.read(file);
    assertEquals(1, dataSet.processes().size());
    var process = dataSet.processes().get(0);
    assertEquals("298f6b5c-46f5-11ec-81d3-0242ac130003", process.platformId());
    assertEquals("24.02.2014", new SimpleDateFormat("dd.MM.yyyy").format(process.date()));
    assertEquals("Test process", process.name());
    assertEquals(ProcessCategory.MATERIAL, process.category());
    assertEquals("DefaultX25250700002", process.identifier());
    assertEquals(ProcessType.UNIT_PROCESS, process.processType());
    assertEquals(Status.DRAFT, process.status());
    assertEquals(Boolean.FALSE, process.infrastructure());
    assertEquals("data entry by: [System]", process.record());
    assertEquals("generated by: [System]", process.generator());
    assertEquals("text for collection method", process.collectionMethod());
    assertEquals("text for data treatment", process.dataTreatment());
    assertEquals("text for verification", process.verification());
    assertEquals("text for comment", process.comment());
    assertEquals("text for allocation rules", process.allocationRules());
    assertEquals("text for allocation rules", process.allocationRules());
  }

}