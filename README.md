# High-level Abstractions, Safety, or Performance?

This project provides a source code for my talk at [EPAM's Zed 2020](https://community-z.com/events/zed-conference)
conference.

During the talk I compare safety, user experience, and performance of a similar scenario in 3 different
programming languages: Java, Python, and C++. The discussed feature is the physical units library.

## Libraries

- Java: [JSR 385 - Units of Measurement](http://unitsofmeasurement.github.io/unit-api)
- Python: [Pint](https://pint.readthedocs.io/en/stable/index.html)
- C++: [mp-units](https://mpusz.github.io/units)

## Scenarios

1. Functional
    - implement `avg_speed` function that takes `length` and `time` arguments and returns `speed`
      in the unit derived from the units of function arguments
    - calculate `avg_speed(220 km, 2 h)` and print the result in `km/h` and `m/s`
    - calculate `avg_speed(140 mi, 2 h)` and print the result in `mi/h` and `m/s`

2. Safety
    - ensure that for reordered arguments `avg_speed(2 h, 220 km)` returns an error
    - ensure that an error is reported when `avg_speed` returns the result of an invalid calculation
        - function multiplies the arguments instead of dividing them
        - the result is not a quantity of speed

3. Efficiency
    - benchmark the following scenarios both for operations on fundamental/primitive types and on high-level
      abstractions
        - create the quantities of `length` and `time` and divide them to obtain `speed`
        - create a quantity of `speed` and convert the unit from `km/h` to `m/s`
    - check how much more memory is needed for a high-level abstraction (quantity class) compared to the
      fundamental/primitive magnitude type
