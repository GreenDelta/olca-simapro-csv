# olca-simapro-csv

https://github.com/laurepatou/IMPACT-World-in-Simapro
https://github.com/massimopizzol/Simapro-CSV-converter

* `;;;;;`

## TODO:
* remove the Pedigree fields and the Pedigree matcher; this should go into the
  openLCA import then
* what is the 4th column of a WasteFractionRow?
* header: check which separators could occur
* header: add a write-method, and write necessary defaults
* need a clean definition how we handle decimal separators and how this
  works with function parameter separators
* make it `null` friendly: **no** getter should **ever** return `null` but accept
  `null` in the setters
