package h12.json.implementation.node;

import h12.json.JSONElement;

import java.io.BufferedWriter;
import java.io.IOException;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * An abstract class representing an JSON element implemented as a node.
 */
public abstract class JSONNode implements JSONElement {

    /**
     * Writes the current indentation to the given {@link java.io.BufferedWriter}.
     * <p> An indentation consists of two spaces.
     *
     * @param writer      The {@link BufferedWriter} to write the indentation to.
     * @param indentation The current indentation.
     * @throws IOException If an {@link IOException} occurs while writing to the writer.
     */
    public void writeIndentation(BufferedWriter writer, int indentation) throws IOException {
        //TODO H2 - remove if implemented
        for (int i=0;i<indentation*2;i++) writer.write(' ');
    }

}
