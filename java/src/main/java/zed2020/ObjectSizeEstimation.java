package zed2020;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Speed;

import static javax.measure.MetricPrefix.KILO;
import static zed2020.Functional.avg_speed;

public class ObjectSizeEstimation {

    public static void main(String[] args) {
        final Quantity<Speed> s1 = avg_speed(Quantities.getQuantity(220., KILO(Units.METRE)), Quantities.getQuantity(2., Units.HOUR));
        System.out.println(InstrumentationAgent.getObjectSize(s1));
        System.out.println(InstrumentationAgent.getObjectSize(Quantities.getQuantity(220., KILO(Units.METRE))));

    }
}
