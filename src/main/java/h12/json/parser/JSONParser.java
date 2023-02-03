package h12.json.parser;

import h12.exceptions.JSONParseException;
import h12.json.JSONElement;

import java.io.IOException;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A class handling the parsing of JSON files.
 */
public class JSONParser {

    private final JSONElementParser elementParser;

    /**
     * Creates a new {@link JSONParser}-Instance.
     *
     * @param elementParser The {@link JSONElementParser} used to parse the JSON file.
     */
    public JSONParser(JSONElementParser elementParser) {
        this.elementParser = elementParser;
    }

    /**
     * Parses the content of a JSON file.
     *
     * @return the root {@link JSONElement} of the parsed JSON file.
     * @throws JSONParseException If an exception occurs while trying to parse the contents of the JSON file.
     */
    public JSONElement parse() throws JSONParseException {
        //TODO H4.1 - remove if implemented
        try {
            JSONElement element = elementParser.parse();
            elementParser.checkEndOfFile();
            return element;
        } catch (IOException e) {
            throw new JSONParseException(e.getMessage());
        }
    }
}
