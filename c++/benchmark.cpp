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

#define ANKERL_NANOBENCH_IMPLEMENT
#include <units/physical/si/derived/speed.h>
#include <nanobench.h>
#include <concepts>
#include <fstream>

namespace {

using namespace units::physical;
using namespace ankerl;

void benchmark(const std::string& name, std::invocable auto func)
{
  auto fout = std::ofstream("pyperf_" + name + ".json");
  nanobench::Bench()
      .epochs(100)
      .run(name, [&]{ nanobench::doNotOptimizeAway(func()); })
      .render(nanobench::templates::pyperf(), fout);
}

constexpr double avg_speed(double d, double t) { return d / t; }
constexpr Speed auto avg_speed(Length auto d, Time auto t) { return d / t; }

constexpr double to_mps(double d) { return d * (1000. / 3600.); }
constexpr si::speed<si::metre_per_second> to_mps(Speed auto s) { return units::quantity_cast<si::metre_per_second>(s); }

}  // namespace

int main()
{
  double length = 220;
  double time = 2;
  benchmark("avg_speed_double", [&]{ return avg_speed(length, time); });
  benchmark("avg_speed_units", [&]{ return avg_speed(si::length<si::kilometre>(length), si::time<si::hour>(time)); });

  double speed = 110;
  benchmark("kmph_to_mps_double", [&]{ return to_mps(speed); });
  benchmark("kmph_to_mps_units", [&]{ return to_mps(si::speed<si::kilometre_per_hour>(speed)); });
}
