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

## TODO:
* are sub-compartments really a close list? -> test them
* support new format fields: `PlatformId`, flow UUIDs
* what is the 4th column of a WasteFractionRow?
* what are the separators for function parameters in formulas?
* parse date-fields into type `Date`
* remove the Pedigree fields and the Pedigree matcher; this should go into the
  openLCA import then
* header: add a write-method, and write necessary defaults
* need a clean definition how we handle decimal separators and how this
  works with function parameter separators
* make it `null` friendly: **no** getter should **ever** return `null` (use
  `Optional` or default values like `""` or `0` ) but accept
  `null` in the setters
* status -> enum: `null`, `Temporary`, `Draft`, `To be revised`, `To be reviewed` and `Finished`
