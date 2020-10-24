package zed2020;

import com.carrotsearch.sizeof.RamUsageEstimator;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;

import static javax.measure.MetricPrefix.KILO;
import static zed2020.Functional.avg_speed;

public class ObjectSizeEstimation {

    public static void main(String[] args) {
        Quantity[] quantities = new Quantity[10000];
        for (int i = 0; i < quantities.length; ++i) {
            quantities[i] = avg_speed(Quantities.getQuantity(Math.random() * 1000, KILO(Units.METRE)), Quantities.getQuantity(Math.random() * 10, Units.HOUR));
        }
        System.out.println("Size of 10000 Quantity elements array: " + RamUsageEstimator.sizeOf(quantities) + " bytes");
        System.out.println("Scales are shared: " + (quantities[1].getScale() == quantities[0].getScale()));
        System.out.println("Units are shared: " + (quantities[1].getUnit() == quantities[0].getUnit()));
        System.out.println(RamUsageEstimator.sizeOf(quantities[1]));
        System.out.println(RamUsageEstimator.sizeOf(quantities[2]));
        System.out.println(RamUsageEstimator.sizeOf(quantities[3]));
        System.out.println(RamUsageEstimator.sizeOf(quantities[4]));

    }
}
