package h09.basic;

public class StringFactory/*TODO 1.3*/ implements BasicFactory<String> {
    // TODO: H1.3 - remove if implemented
    private int current;
    private final String[] text;

    public StringFactory(int start, String[] text) {
        this.current = start;
        this.text = text;
    }

    @Override
    public String create() {
        String s = text[current];
        if (current+1<text.length) current++;
        else current = 0;
        return s;
    }
}
