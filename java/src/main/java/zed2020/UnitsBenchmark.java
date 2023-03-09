package zed2020;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;

import static zed2020.Functional.avg_speed;
import static javax.measure.MetricPrefix.KILO;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(value = 1)
@Warmup(iterations = 3, time = 2)
@Measurement(iterations = 10, time = 2)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class UnitsBenchmark {

    double length = 220;
    double time = 2;
    double speed = 110;

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
    public double benchmarkAvgSpeedDouble() throws IOException {
        return avg_speed_double(length, time);
    }

    @Benchmark
    public Quantity<Speed> benchmarkAvgSpeedUnits() throws IOException {
        return avg_speed((Length) Quantities.getQuantity(length, KILO(Units.METRE)), (Time) Quantities.getQuantity(time, Units.HOUR));
    }

    @Benchmark
    public double benchmarkToMPSDouble() throws IOException {
        return to_mps_double(speed);
    }

    @Benchmark
    public Quantity<Speed> benchmarkToMPSUnits() throws IOException {
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
