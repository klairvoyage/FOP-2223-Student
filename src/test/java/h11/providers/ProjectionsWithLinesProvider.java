package h11.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ProjectionsWithLinesProvider extends ProjectionsProvider implements ArgumentsProvider {

    private final LSystemToRandomLinesConverter converter = new LSystemToRandomLinesConverter(random);

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return generateProjections()
        .map(s ->
                Arguments.of(s, converter.lSystemAsLines(s)));
    }
}
