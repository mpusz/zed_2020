// MIT License
//
// Copyright (c) 2020 Mateusz Pusz
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package zed2020.storage_tank;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Force;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Length;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;
import javax.measure.quantity.Volume;

import si.uom.quantity.Density;

import static javax.measure.MetricPrefix.MILLI;

public class Main {
    public static void main(String[] args) {
        final Quantity<Length> height = Quantities.getQuantity(200, MILLI(Units.METRE));
        StorageTank tank = new RectangularStorageTank(Quantities.getQuantity(1000, MILLI(Units.METRE)),
                Quantities.getQuantity(500, MILLI(Units.METRE)), height);
        tank.setContentsDensity(
                Quantities.getQuantity(1000, Units.KILOGRAM.divide(Units.CUBIC_METRE))
                        .asType(Density.class));

        final Quantity<Time> fillTime = Quantities.getQuantity(200, Units.SECOND);
        final Quantity<Mass> measuredMass = Quantities.getQuantity(20, Units.KILOGRAM);

        final Quantity<Length> fillLevel = tank.fillLevel(measuredMass);
        final Quantity<Volume> spareCapacity = tank.spareCapacity(measuredMass);
        final Quantity<Force> filledWeight = tank.filledWeight();

        final Quantity<?> inputFlowRate = measuredMass.divide(fillTime); // TODO is there a mass change rate
                                                                         // quantity?
        final Quantity<Speed> floatRiseRate = fillLevel.divide(fillTime).asType(Speed.class);
        // final Quantity<Time> fillTimeLeft =
        // height.divide(fillLevel).subtract(Quantities.getQuantity(1,
        // AbstractUnit.ONE)).multiply(fillTime); // TODO How to do it?
        final Quantity<?> fillRatio = fillLevel.divide(height);

        System.out.println("fill height at " + fillTime + " = " + fillLevel.to(Units.METRE) + " ("
                + fillRatio.asType(Dimensionless.class).to(Units.PERCENT) + " full)");
        System.out.println("fill weight at " + fillTime + " = " + filledWeight + "("
                + filledWeight.to(Units.NEWTON) + ")");
        System.out.println("spare capacity at " + fillTime + " = " + spareCapacity.to(Units.CUBIC_METRE));
        System.out.println("input flow rate = " + inputFlowRate);
        // System.out.println("float rise rate = " +
        // floatRiseRate.to(Units.METRE.divide(Units.SECOND))); // TODO Why this does
        // not work?
        // System.out.println("tank full E.T.A. at current flow rate = " +
        // fillTimeLeft); // TODO Print when fillTimeLeft works
    }
}
