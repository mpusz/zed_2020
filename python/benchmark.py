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

from pint import UnitRegistry
import pyperf

l = 60000.
t = 3600.
ureg = UnitRegistry()

def avg_speed_fundamental(d, t):
  speed = d / t

def test_fundamental():
  length = l
  time = t
  for _ in range(100):
    speed = avg_speed_fundamental(length, time)
    length = length + 1
    time = time + 1

@ureg.check('[length]', '[time]')
def avg_speed_units(d, t):
  speed = d / t
  if not speed.check('[speed]'):
    raise RuntimeError("Not a [speed] dimension")
  return speed

def test_units():
  length = l * ureg.metre
  time = t * ureg.second
  for _ in range(100):
    speed = avg_speed_units(length, time)
    length = length + 1 * ureg.metre
    time = time + 1 * ureg.second

runner = pyperf.Runner()
runner.bench_func("avg_speed_fundamental", test_fundamental)
runner.bench_func("avg_speed_units", test_units)
