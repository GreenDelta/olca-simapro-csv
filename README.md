# olca-simapro-csv

`olca-simapro-csv` is an API for reading and writing the [SimaPro CSV format](./format.md).

## Usage

Include this dependency in your project:

```xml
<dependency>
  <groupId>org.openlca</groupId>
  <artifactId>olca-simapro-csv</artifactId>
  <version>3.0.0</version>
</dependency>
```

You can directly read a CSV data set from a file:

```java
var dataSet = SimaProCsv.read(file);
```

Alternatively, you can read the content of a file block by block. This is useful
when you have large files that do not fit into memory:

```java
SimaProCsv.read(file, block -> {
  if (block.isProcessBlock()) {
    var process = block.asProcessBlock();
    // ...
  } else if (block.isUnitBlock()) {
    var unitBlock = block.asUnitBlock();
    // ...
  }
});
```

A data set can be written to a file:

```java
dataset.write(file);
```

A daset set can also be written to a csvbuffer:

```
CsvDataSet csvDataSet = ...
try (var writer = new FileWriter(file, SimaProCsv.defaultCharset())) {
  csvDataSet.write(new CsvBuffer(writer, csvDataSet.header()));
} catch (IOException e) {
  throw new RuntimeException("failed to write file: " + file, e);
}
```
