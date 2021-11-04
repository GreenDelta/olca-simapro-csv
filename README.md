# olca-simapro-csv

https://github.com/laurepatou/IMPACT-World-in-Simapro
https://github.com/massimopizzol/Simapro-CSV-converter

* `;;;;;`

## TODO:
* are sub-compartments really a close list? -> test them
* support new format fields: `PlatformId`, UUIDs?
* what is the 4th column of a WasteFractionRow?
* what are the separators for function parameters in formulas?
* parse date-fields into type `Date`

* remove the Pedigree fields and the Pedigree matcher; this should go into the
  openLCA import then
* header: check which separators could occur
* header: add a write-method, and write necessary defaults
* need a clean definition how we handle decimal separators and how this
  works with function parameter separators
* make it `null` friendly: **no** getter should **ever** return `null` (use
  `Optional` or default values like `""` or `0` ) but accept
  `null` in the setters
