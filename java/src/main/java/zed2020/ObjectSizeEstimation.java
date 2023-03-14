package zed2020;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Speed;

import java.util.logging.Logger;

import static javax.measure.MetricPrefix.KILO;
import static zed2020.Safety_2.avg_speed;

public class ObjectSizeEstimation {

    private static final Logger LOGGER = Logger.getLogger(ObjectSizeEstimation.class.getName());

    public static void main(String[] args) {
        Quantity<Speed>[] quantities = new Quantity[10000];
        for (int i = 0; i < quantities.length; ++i) {
            quantities[i] = avg_speed(Quantities.getQuantity(Math.random() * 1000, KILO(Units.METRE)), Quantities.getQuantity(Math.random() * 10, Units.HOUR));
        }
        LOGGER.info(ClassLayout.parseInstance(quantities).toPrintable());

        long size = GraphLayout.parseInstance(quantities).totalSize();
        long deepSize = GraphLayout.parseInstance(quantities).totalSize();
        LOGGER.info(String.format("Shallow size: %d", size));
        LOGGER.info(String.format("Deep size: %d", deepSize));

        LOGGER.info(ClassLayout.parseInstance(quantities[0]).toPrintable());

        long objectSize = GraphLayout.parseInstance(quantities[0]).totalSize();
        long objectDeepSize = GraphLayout.parseInstance(quantities[0]).totalSize();
        LOGGER.info(String.format("Object shallow size: %d", objectSize));
        LOGGER.info(String.format("Object deep size: %d", objectDeepSize));

    }
}
