package h11.h7;

import h11.parse.Projection;

import java.util.List;

public record LSystemAsLinesTestCase(long seed, List<Projection> projections, List<String> lines) {
}
