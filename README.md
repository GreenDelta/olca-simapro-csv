# olca-simapro-csv

`olca-simapro-csv` is an API for reading and writing the [SimaPro CSV format](./format.md).

## Usage

Include this dependency in your project:

```xml
<dependency>
  <groupId>org.openlca</groupId>
  <artifactId>olca-simapro-csv</artifactId>
  <version>3.0.5</version>
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

There is also a more low-level API to write data sets to a `CsvBuffer`:

```
CsvDataSet ds = ...
try (var writer = new FileWriter(file, SimaProCsv.defaultCharset())) {
  ds.write(new CsvBuffer(writer, ds.header()));
}
```
