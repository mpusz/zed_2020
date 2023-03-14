package zed2020;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Speed;
import java.util.concurrent.TimeUnit;

import static javax.measure.MetricPrefix.KILO;
import static zed2020.Functional.avg_speed;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 10, time = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class UnitsBenchmark {

    final double length = 220;
    final double time = 2;
    final double speed = 110;

    public static double avg_speed_double(double l, double t) {
        return l / t;
    }

    public static double to_mps_double(double s) {
        return s * (1000. / 3600.);
    }

    public static Quantity<Speed> to_mps_units(Quantity<Speed> s) {
        return s.to(Units.METRE_PER_SECOND);
    }

    @Benchmark
    public double benchmarkAvgSpeedDouble() {
        return avg_speed_double(length, time);
    }

    @Benchmark
    public Quantity<Speed> benchmarkAvgSpeedUnits() {
        return avg_speed(Quantities.getQuantity(length, KILO(Units.METRE)), Quantities.getQuantity(time, Units.HOUR));
    }

    @Benchmark
    public double benchmarkToMPSDouble() {
        return to_mps_double(speed);
    }

    @Benchmark
    public Quantity<Speed> benchmarkToMPSUnits() {
        return to_mps_units(Quantities.getQuantity(speed, Units.KILOMETRE_PER_HOUR));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(UnitsBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
