// MIT License

// Copyright (c) 2020 Mateusz Pusz

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

#include <units/physical/si/international/derived/speed.h>
#include <units/physical/si/derived/speed.h>
#include <iostream>

using namespace units::physical;
using namespace units::physical::si::literals;
using namespace units::physical::si::international::literals;

constexpr Speed auto avg_speed(Length auto d, Time auto t)
{
  return d / t;
}

int main()
{
  Speed auto s1 = avg_speed(si::length<si::kilometre>(220), si::time<si::hour>(2));
  Speed auto s2 = avg_speed(si::length<si::international::mile>(140), si::time<si::hour>(2));
  Speed auto s3 = avg_speed(220_q_km, 2_q_h);
  Speed auto s4 = avg_speed(140_q_mi, 2_q_h);

  std::cout << s1 << '\n';
  std::cout << s2 << '\n';
  std::cout << quantity_cast<si::metre_per_second>(s1) << '\n';
  std::cout << quantity_cast<si::dim_speed::coherent_unit>(s2) << '\n';
  std::cout << s3 << '\n';
  std::cout << s4 << '\n';
}
