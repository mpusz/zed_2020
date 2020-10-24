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

package zed2020;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Time;
import javax.measure.quantity.Speed;

import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

import static javax.measure.MetricPrefix.KILO;

public class Safety_1 {
    public static Quantity<Speed> avg_speed(Quantity<Length> length, Quantity<Time> time) {
        return length.divide(time).asType(Speed.class);
    }

    public static void main(String[] args) {
        final Quantity<Speed> s = avg_speed(Quantities.getQuantity(2., Units.HOUR), Quantities.getQuantity(220., KILO(Units.METRE)));
    }
}
