package h11.providers;

import h11.Random;
import h11.parse.Projection;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class ProjectionsProvider implements ArgumentsProvider {
    protected static final long MAX_STREAM_SIZE = 100;
    private static final int SEED = 0;
    protected final Random random = SEED != 0 ? new Random() : new Random(SEED);
    protected final RandomLSystemGenerator generator = new RandomLSystemGenerator(random);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return generateProjections()
            .map(Arguments::of);
    }

    protected Stream<List<Projection>> generateProjections() {
        return Stream
            .generate(generator::generate)
            .limit(MAX_STREAM_SIZE);
    }
}
