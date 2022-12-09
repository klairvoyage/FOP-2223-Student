package h06;

import static org.tudalgo.algoutils.student.Student.crash;
import static h06.EvaluationResult.Type.*;
//comments?

public class BracketExpression {

    private final char[] expression;

    public BracketExpression(String expression) {
        this.expression = expression.toCharArray();
    }

    public final EvaluationResult evaluate() {
        //return evaluate(0);
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NO LOOPS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int index = 0;
        while (index<expression.length) {
            EvaluationResult yo = evaluate(index);
            if (yo.type()!=CORRECT) return yo;
            index = yo.nextIndex();
        }
        for (int i=index;i<expression.length;i++) if (!isBracket(i)) return new EvaluationResult(INVALID_CHARACTER, i);
        return new EvaluationResult(CORRECT, index);
    }

    public final EvaluationResult evaluate(int i) {
        // TODO: H3 - remove if implemented
        if (!isBracket(i)) return new EvaluationResult(INVALID_CHARACTER, i);
        else if (closingBracket(i)) return new EvaluationResult(NO_OPENING_BRACKET, i);
        else if (i==expression.length-1) return new EvaluationResult(NO_CLOSING_BRACKET, i);
        if (matchingClosingBracket(i+1, i)) return new EvaluationResult(CORRECT, i+2);
        else if (closingBracket(i+1)) return new EvaluationResult(INVALID_CLOSING_BRACKET, i+1);
        EvaluationResult r = evaluate(i+1);
        if (r.type()!=CORRECT) return r;
        else if (r.nextIndex()>=expression.length) return new EvaluationResult(NO_CLOSING_BRACKET, r.nextIndex());
        else if (matchingClosingBracket(r.nextIndex(), i)) return new EvaluationResult(CORRECT, r.nextIndex()+1);
        else if (closingBracket(r.nextIndex())) return new EvaluationResult(INVALID_CLOSING_BRACKET, r.nextIndex());
        else if (openingBracket(r.nextIndex())) return new EvaluationResult(NO_CLOSING_BRACKET, r.nextIndex());
        else return new EvaluationResult(INVALID_CHARACTER, r.nextIndex());
    }

    private boolean isBracket(int i) { return (openingBracket(i) || closingBracket(i)); }

    private boolean openingBracket(int i) { return (expression[i]=='(' || expression[i]=='[' || expression[i]=='{'); }

    private boolean closingBracket(int i) { return (expression[i]==')' || expression[i]==']' || expression[i]=='}'); }

    private boolean matchingClosingBracket(int i, int match) {
        return ((expression[i]==')' && expression[match]=='(') || (expression[i]==']' && expression[match]=='[')
            || (expression[i]=='}' && expression[match]=='{'));
    }
}
