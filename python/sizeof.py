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

from pympler import asizeof
from pint import UnitRegistry
from numpy.random import default_rng
from sys import getsizeof

def test_data(count, min, max):
  values = default_rng().uniform(min, max, count)
  return list(map(lambda s: s * ureg.kilometre / ureg.hour, values))

if __name__ == '__main__':
    ureg = UnitRegistry()

    i = 123.13
    q = 123. * ureg.kilometre / ureg.hour
    data = test_data(10000, 40, 140)
    print("i:", asizeof.asizeof(i), "bytes")
    print("q:", asizeof.asizeof(q), "bytes")
    print("data:", asizeof.asizeof(data), "bytes")
    print("ureg:", asizeof.asizeof(ureg), "bytes")
