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
    - calculate `avg_speed(220 km, 2 h)` and print the result in `km/h`
    - calculate `avg_speed(140 mi, 2 h)` and print the result in `mi/h`
    - calculate `avg_speed(220 km, 2 h)` and print the result in `m/s`
    - calculate `avg_speed(140 mi, 2 h)` and print the result in `m/s`

2. Safety
    - ensure that `avg_speed(2 h, 220 km)` returns an error
    - ensure that an error is reported when `avg_speed` will multiply the arguments instead of
      dividing them (the result is not a quantity of speed)

3. Performance
    - profile the time needed to create the quantity of `length` and `time` for `100` different
      values (obtained by incrementing the initial value) and to calculate `avg_speed` for each pair
    - check how much more memory is needed for a quantity class compared to the basic fundamental
      magnitude type
