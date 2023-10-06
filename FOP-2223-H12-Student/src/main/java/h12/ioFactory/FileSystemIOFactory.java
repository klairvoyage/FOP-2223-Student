package h12.ioFactory;

import java.io.*;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A factory for creating {@link BufferedReader} and {@link BufferedWriter} linked to the file system.
 */
public class FileSystemIOFactory implements IOFactory {

    /**
     * {@inheritDoc}
     *
     * @param resourceName The resource to read from.
     * @return A {@link BufferedReader} to read from the resource.
     * @throws IOException If an {@link IOException} occurs while creating the {@link BufferedReader}.
     */
    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        //TODO H1.1 - remove if implemented
        FileReader fileReader = new FileReader(resourceName);
        return new BufferedReader(fileReader);
    }

    /**
     * {@inheritDoc}
     *
     * @param resourceName The resource to write to.
     * @return A {@link BufferedWriter} to write to the resource.
     * @throws IOException If an {@link IOException} occurs while creating the {@link BufferedWriter}.
     */
    @Override
    public BufferedWriter createWriter(String resourceName) throws IOException {
        //TODO H1.1 - remove if implemented
        FileWriter fileWriter = new FileWriter(resourceName);
        return new BufferedWriter(fileWriter);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true}.
     */
    @Override
    public boolean supportsReader() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true}.
     */
    @Override
    public boolean supportsWriter() {
        return true;
    }

}
