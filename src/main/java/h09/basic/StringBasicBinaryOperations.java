package h09.basic;

public class StringBasicBinaryOperations/*TODO: H1.4*/ implements BasicBinaryOperations<String,Integer> {
    // TODO: H1.4 - remove if implemented
    @Override
    public String add(String a, String b) {
        return a+b;
    }

    @Override
    public String mul(String a, Integer b) {
        if (b<=0) return "";
        else return a.repeat(b);
    }
}
