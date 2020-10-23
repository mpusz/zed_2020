package zed2020;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static javax.measure.MetricPrefix.KILO;
import static zed2020.Functional.avg_speed;

public class UnitsBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmarkAvgSpeed() throws IOException {
        avg_speed(Quantities.getQuantity(Math.random() * 1000, KILO(Units.METRE)), Quantities.getQuantity(Math.random() * 10, Units.HOUR));

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(UnitsBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
