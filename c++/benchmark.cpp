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

constexpr double avg_speed(double d, double t) { return d / t; }
constexpr Speed auto avg_speed(Length auto d, Time auto t) { return d / t; }

void test(auto length, auto time)
{
  for (auto i = 0; i < 100; ++i) {
    const auto speed = avg_speed(length++, time++);
    nanobench::doNotOptimizeAway(speed);
  }
}

}  // namespace

int main()
{
  double length = 60'000;
  double time = 3'600;

  auto fout = std::ofstream("pyperf_avg_speed_double.json");
  nanobench::Bench()
      .run("avg_speed_double", [=] { test(length, time); })
      .render(nanobench::templates::pyperf(), fout);

  fout = std::ofstream("pyperf_avg_speed_units.json");
  nanobench::Bench()
      .run("avg_speed_units", [=] { test(si::length<si::metre>(length), si::time<si::second>(time)); })
      .render(nanobench::templates::pyperf(), fout);
}
