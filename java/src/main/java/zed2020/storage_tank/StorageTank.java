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

import tech.units.indriya.ComparableQuantity;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.quantity.Acceleration;
import javax.measure.quantity.Area;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import si.uom.quantity.Density;
import si.uom.NonSI;

// import tech.uom.lib.common.function.QuantityFunctions;

public class StorageTank {
    private static final Quantity<Density> airDensity = Quantities
            .getQuantity(1.225, Units.KILOGRAM.divide(Units.CUBIC_METRE))
            .asType(Density.class);
    private static final Quantity<Acceleration> g = Quantities.getQuantity(1, NonSI.STANDARD_GRAVITY);

    private final Quantity<Area> base;
    private final Quantity<Length> height;
    private Quantity<Density> density = airDensity;

    public StorageTank(Quantity<Area> base, Quantity<Length> height) {
        this.base = base;
        this.height = height;
    }

    public void setContentsDensity(ComparableQuantity<Density> density) {
        assert density.isGreaterThan(airDensity);
        this.density = density;
    }

    public Quantity<Force> filledWeight() {
        final Quantity<Volume> volume = base.multiply(height).asType(Volume.class);
        final Quantity<Mass> mass = density.multiply(volume).asType(Mass.class);
        return mass.multiply(g).asType(Force.class);
    }

    public Quantity<Length> fillLevel(Quantity<Mass> measuredMass) {
        return height.multiply(measuredMass).multiply(g).divide(filledWeight()).asType(Length.class);
    }

    public Quantity<Volume> spareCapacity(Quantity<Mass> measuredMass) {
        return height.subtract(fillLevel(measuredMass)).multiply(base).asType(Volume.class);
    }
}
