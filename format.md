# Format details and implementation

The sections below describe some details of the SimaPro CSV format and how it is
implemented in this library.


## File encoding

SimaPro uses [Windows-1252](https://en.wikipedia.org/wiki/Windows-1252) as
default file encoding for reading and writing. You can set the file encoding in
the respective methods of reading and writing, but you should make sure that you
use `Windows-1252` for files that directly come from SimaPro or should be read
by it.


## The file header

A SimaPro CSV file starts with a header which looks like this:

```
{SimaPro 8.5.0.0}
{methods}
{Date: 2019-10-24}
{Time: 18:35:10}
{Project: Methods}
{CSV Format version: 8.0.5}
{CSV separator: Semicolon}
{Decimal separator: .}
{Date separator: -}
{Short date format: yyyy-MM-dd}
{Selection: Selection (1)}
{Related objects (system descriptions, substances, units, etc.): Yes}
{Include sub product stages and processes: No}
{Open library: 'Methods'}
```

This header starts with the first line and each header entry is enclosed in
curly brackets. Before you read the actual data from the file you need this
information to parse the data into the correct format because things like the
column or decimal separator are defined in this header. This makes it a bit hard
to read the format because you need to jump back to the top when starting
reading the file with the assumption of another column separator until you come
to the `CSV separator` entry.

## Blocks, sections, rows

After the header, a SimaPro CSV file contains a set of blocks with data. All
data blocks start with a header and end with the keyword `End`. For example the
following is a block of quantities:

```
Quantities
Mass;Yes
Length;Yes

End
```

A block can contain data rows directly, like in the example above, or contain
sections with data rows. For example a process block starts with the header
`Process` and contains a set of sections like `Category type`, `Process
identifier`, etc:

```
Process

Category type
material

Process identifier
DefaultX25250700002

Type
Unit process

...

End
```

As for the blocks each section has a header, however it does not end with the
keyword `End` but with an empty line. Data rows of a block or section are
directly located in the next line under the header. A section of a block starts
with an empty line.


## Data types

### Booleans

Booleans are represented in the SimaPro CSV format with the following strings:

* `Yes` means `true`
* `No` means `false`

### Numeric data

Sometimes the value of an attribute can contain a formula string or a floating
point number. The data type of these fields is then `Numeric` which has specific
methods for testing and getting the field's value.


## Identification of entities

In general, things are identified by name in the SimaPro CSV format. Flows are
identified by their category type, which is an enumeration, and their name
(thus, you can have different flows with the same name for different category
types). Elementary, product, and product-stage flows have different category
types respectively.

There are some problems with this approach. For example, assemblies have a
section for inputs of materials or other assemblies, but there are only
the names of the flows available in the section and if there is an assembly
and material with the same name in the database, you have a problem.


## Reference data blocks

### System descriptions

A system description block in the CSV looks like this:

```
System description

Name
system name

Category
Others

Description
text for description

Sub-systems
text for sub-systems

Cut-off rules
text for cut-off rules

Energy model
text for energy model

Transport model
text for transport model

Waste model
text for waste model

Other assumptions
text for other assumptions

Other information
text for other information

Allocation rules
text for allocation rules

End
```

### Literature references

A literature reference block in the CSV looks like this:

```
Literature reference

Name
US EPA (1995)

Documentation link
http://www.epa.gov/ttnchie1/ap42/ch09/bgdocs/b9s13-2.pdf

Category
Others

Description
Test description

End
```

### Quantities

A block of quantities looks like this:

```
Quantities

Mass;Yes
Energy;Yes
Length;Yes

End
```

Each row in a quantity block represents a quantity. The attributes of a row are:

```
0    Name
1    Has dimension
```


### Units

A block of units looks like this:

```
Units

kg;Mass;1;kg
g;Mass;0,001;kg
kWh;Energy;3,6;MJ
MJ;Energy;1;MJ
ton;Mass;1000;kg
µg;Mass;0,000000001;kg
...

End
```

Each row in a unit block represents a unit. The attributes of a unit are:

```
0    Name
1    Quantity
2    Conversion factor
3    Reference unit
```

### Parameters

There are two levels of parameters:

* Database parameters
* Project parameters

Furthermore, there are two types of parameters:

* Input parameters: they are named values which can have an associated uncertainty.
* Calculates parameters: they are expressions which can refer other parameters.

#### Input parameters

A block of database input parameters looks like this:

```
Database Input parameters
db_input_param;1;Lognormal;1;0;0;No;database parameter

End
```

While a block of project input parameters is as follows:

```
Project Input parameters
proj_input_param;32;Uniform;0;10;35;No;project input parameter

End
```

Each row is an input parameter with the following attributes:

```
0    Name
1    Value
2-5  Uncertainty
6    Is hidden
7    Comment
```

#### Calculated parameters

A block including the calculated parameters in the database level and in the project level:

```
Database Calculated parameters
db_calc_param;db_input_param * 3;calculated database parameter

End

Project Calculated parameters
proj_calc_param;db_input_param *4;project calculated parameter

End
```

Each row is a calculated parameter with the following attributes:
```
0    name
1    expression
2    comment
```

### Elementary flows

Different blocks are presented for different elementary flow types:

```
Raw materials
Acids;kg;;

End


Airborne emissions
(+-)-Citronellol;kg;026489-01-0;

End


Waterborne emissions
(1r,4r)-(+)-Camphor;kg;000464-49-3;

End


Final waste flows
Asbestos;kg;;

End


Emissions to soil
1'-Acetoxysafrole;kg;034627-78-6;No formula available

End


Non material emissions
Noise from bus km;km;;

End


Social issues
venting of argon, crude, liquid;kg;;

End


Economic issues
Sample economic issue;kg;;

End
```

The SimaPro CSV format uses different names to identify these types in different contexts.

Each row of one of these elementary flow blocks represent an elementary flow metadata with the following attributes:

```
0    name
1    unit
2    cas
3    comment
4    platform ID
```

### Uncertainty record

An uncertainty distribution can be stored in 4 slots of a CSV row
(as in the previous input parameters examples). The first
slot contains the distribution type:

* Undefined
* Lognormal
* Normal
* Triangle
* Uniform

The other slots contain the distribution parameters.

### Processes

A process block has the following sections:

#### Single sections
Sections with only one entry (no rows). In code block their corresponding
type in the API:

* PlatformId `String`
* Category type `ProcessCategory`
* Process identifier `String`
* Type `ProcessType`
* Process name `String`
* Status `String`
* Time period `String`
* Geography `String`
* Technology `String`
* Representativeness `String`
* Multiple output allocation `String`
* Substitution allocation `String`
* Cut off rules `String`
* Capital goods `String`
* Boundary with nature `String`
* Infrastructure `Boolean`
* Date `String`
* Record `String`
* Generator `String`
* Collection method `String`
* Data treatment `String`
* Verification `String`
* Comment `String`
* Allocation rules `String`


#### Row sections

##### Literature references

A literature reference row looks like this in the CSV:

```
Literature references
Ecoinvent 3;is copyright protected: false
```

Attributes:

```
0    name
1    comment
```

##### System description

A system description row example in the CSV could be:

```
System description
U.S. LCI Database;system description comment
```

Attributes:

```
0    name
1    comment
```

##### Products

A product block in the CSV looks like follows. It can have multiple lines (one for each product):

```
Products
my product;kg;0,5;100;not defined;Agricultural;
```

Attributes:

```
0    name
1    unit
2    amount
3    allocation
4    waste type
5    category
6    comment
7    platform ID
```

##### Technosphere exchanges

Different blocks are presented for different technosphere exchange types:

```
Avoided products
Wool, at field/US;kg;1;Undefined;0;0;0;

Materials/fuels
Soy oil, refined, at plant/kg/RNA;kg;0;Undefined;0;0;0;

Electricity/heat
Electricity, biomass, at power plant/US;kWh;0,1;Undefined;0;0;0;
```

Attributes:

```
0    name
1    unit
2    amount
3-6  uncertainty
7    comment
8    platform ID
```

##### Elementary flow exchanges

There is one block for each elementary flow type:

```
Resources
Acids;;kg;1;Undefined;0;0;0;

Emissions to air
(+-)-Citronellol;low. pop.;kg;1;Lognormal;2;0;0;(1,2,3,4,5) with comment

Emissions to water
(1r,4r)-(+)-Camphor;lake;kg;1;Normal;3;0;0;

Emissions to soil
1'-Acetoxysafrole;forestry;kg;1;Triangle;0;1;5;
```

Attributes:

```
0    name
1    subcompartment
2    unit
3    amount
4-7  uncertainty
8    comment
9    platform ID
```

##### Input and calculated parameters

Analogous to the explained rows in the Reference data section, but applied to the process level.

#### Waste treatment and waste scenario

A waste treatment block looks like this:

```
Waste treatment
Waste treatment 1;kg;1;All waste types;Others;
```

While a waste scenario block is as follows:

```
Waste scenario
Waste scenario 1;kg;1;All waste types;Others;
```

Both have one line with the following attributes:

```
0    name
1    unit
2    amount
3    waste type
4    category
5    comment
6    platform ID
```

#### Waste fractions

There are two types of waste fractions:

* Separated waste: Defines the waste treatments of the separated fractions.
* Remaining waste: Defines the waste treatment of the remaining fractions after the separation processes.

A separated waste and a remaining waste blocks looks like this in the CSV:

```
Separated waste
Waste treatment 1;All waste types;90;

Remaining waste
Waste treatment 2;Plastics;100;
```

Each block could have several rows with the following attributes:

```
0    waste treatment
1    waste type
2    fraction (%)
3    comment
```

### Product stages
A product stage block has the following sections:

#### Single sections
Sections with only one entry (no rows). In code block their corresponding
type in the API:

* Category `ProductStageCategory`
* Status `String`

#### Row sections
##### Products

The products block looks like this:

```
Products
assembly;p;1;Others;;
```

Attributes:

```
0    name
1    unit
2    amount
3    category
4    comment
5    platform ID
```

##### Technosphere exchanges
Analogous to `Processes`, but with different types. On the one hand, there are single row exchanges:

* Assembly
* Reference assembly
* Waste or disposal scenario

On the other hand, there are technosphere exchange blocks:

* Materials and assemblies
* Processes
* Waste scenarios
* Dissassemblies
* Disposal scenarios
* Reuses
* Additional life cycles

##### Input and calculated parameters
Analogous to `Processes`.

### Methods
An impact method block has the following sections:

#### Single sections
Sections with only one entry (no rows). In code block their corresponding
type in the API:

* Name `String`
* Comment `String`
* Category `String`
* Use Damage Assessment `Boolean`
* Use Normalization `Boolean`
* Use Weighting `Boolean`
* Use Addition `Boolean`
* Weighting unit `String`

#### Row sections

##### Version

A version block looks like this:

```
Version
1;431
```

Attributes:

```
0    major
1    minor
```

##### Impact category

An impact category block is as the following example:

```
Impact category
Climate change, ecosystem quality, short term;PDF.m2.yr
```

Attributes:

```
0    name
1    unit
```

Each impact category has a `Substances` section with the characterization factors:

```
Substances
Air;(unspecified);(E)-HFC-1225ye;10/8/5595;0;kg
Air;(unspecified);(E)-HFC-1234ze;1645-83-6;0.177;kg
```

Each row has the following attributes:

```
0    compartment
1    subcompartment
2    flow
3    cas number
4    factor
5    unit
```

##### Damage category

A damage category block example could be:

```
Damage category
Ecosystem quality;PDF.m2.yr
```

Attributes:

```
0    name
1    unit
```

Each damage category has a `Impact categories` section with the damage factors:

```
Impact categories
Climate change, ecosystem quality, long term;1.00E+00
Climate change, ecosystem quality, short term;1.00E+00
```

Each row has the following attributes:

```
0    impact category
1    factor
```

## Normalization-Weighting set

A normalization and weighting set looks like this in the CSV file:

```
Normalization-Weighting set
IMPACT World+ (Stepwise 2006 values)
```

Attributes:

```
0    name
```

Each set has a `Normalization` and a `Weighting` sections:

```
Normalization
Human health;1.37E+01
Ecosystem quality;1.01E-04

Weighting
Human health;5401.459854
Ecosystem quality;1386.138614
```

With the following attributes:

```
0    impact category
1    factor
```
