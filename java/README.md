### How to run JMH benchmark

    mvn clean install
    java -jar target/benchmarks.jar

### How to check size of the object

    mvn clean install
    java -jar -Djol.magicFieldOffset=true target/size-estimation.jar

Scale object shared across different Quantity objects, though Unit is not

4961112 bytes for arrays of 10000 speed quantities

More details:
```
INFO: [Ljavax.measure.Quantity; object internals:
OFF  SZ                     TYPE DESCRIPTION               VALUE
  0   8                          (object header: mark)     0x0000000000000011 (non-biasable; age: 2)
  8   4                          (object header: class)    0x00c00c08
 12   4                          (array length)            10000
 16 40000   javax.measure.Quantity Quantity;.<elements>      N/A
Instance size: 40016 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

INFO: Deep size: 4961112

INFO: tech.units.indriya.quantity.NumberQuantity object internals:
OFF  SZ                           TYPE DESCRIPTION               VALUE
  0   8                                (object header: mark)     0x0000000000000011 (non-biasable; age: 2)
  8   4                                (object header: class)    0x00c10d58
 12   4             javax.measure.Unit AbstractQuantity.unit     (object)
 16   4   javax.measure.Quantity.Scale AbstractQuantity.scale    (object)
 20   4               java.lang.Number NumberQuantity.value      (object)
Instance size: 24 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

INFO: Object deep size: 1608
```
