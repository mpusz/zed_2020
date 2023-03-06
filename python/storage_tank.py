# MIT License
#
# Copyright (c) 2020 Mateusz Pusz
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.

import math
from pint import UnitRegistry

ureg = UnitRegistry()
ureg.default_format = "~P"

air_density = 1.225 * ureg.kg / ureg.m**3
g = ureg.standard_gravity


class StorageTank:
    @ureg.check(None, "[area]", "[length]")
    def __init__(self, base, height):
        self.base = base
        self.height = height
        self.density = air_density

    @ureg.check(None, "[density]")
    def set_contents_density(self, density):
        assert density > air_density
        self.density = density

    def filled_weight(self):
        volume = self.base * self.height
        mass = self.density * volume
        return mass * g

    @ureg.check(None, "[mass]")
    def fill_level(self, measured_mass):
        return self.height * measured_mass * g / self.filled_weight()

    @ureg.check(None, "[mass]")
    def spare_capacity(self, measured_mass):
        return (self.height - self.fill_level(measured_mass)) * self.base


class CylindricalStorageTank(StorageTank):
    @ureg.check(None, "[length]", "[length]")
    def __init__(self, radius, height):
        super().__init__(math.pi * radius * radius, height)


class RectangularStorageTank(StorageTank):
    @ureg.check(None, "[length]", "[length]", "[length]")
    def __init__(self, length, width, height):
        super().__init__(length * width, height)


height = 200 * ureg.mm
tank = RectangularStorageTank(1000 * ureg.mm, 500 * ureg.mm, height)
tank.set_contents_density(1000 * ureg.kg / ureg.m**3)

fill_time = 200 * ureg.s
measured_mass = 20 * ureg.kg

fill_level = tank.fill_level(measured_mass)
spare_capacity = tank.spare_capacity(measured_mass)
filled_weight = tank.filled_weight()

input_flow_rate = measured_mass / fill_time
float_rise_rate = fill_level / fill_time
fill_time_left = (height / fill_level - 1) * fill_time
fill_ratio = fill_level / height

print(
    "fill height at {} = {} ({} full)".format(
        fill_time, fill_level.to("m"), fill_ratio.to_reduced_units()
    )
)
print(
    "fill weight at {} = {} ({:.3~P})".format(
        fill_time, filled_weight.to_reduced_units(), filled_weight.to("N")
    )
)
print("spare capacity at {} = {}".format(fill_time, spare_capacity.to(ureg.m**3)))
print("input flow rate = {}".format(input_flow_rate))
print("float rise rate = {}".format(float_rise_rate.to("m/s")))
print("tank full E.T.A. at current flow rate = {}".format(fill_time_left))
