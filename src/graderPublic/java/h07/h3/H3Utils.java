package h07.h3;

import spoon.reflect.code.CtLambda;

public class H3Utils {

    public static boolean isStandardLambda(CtLambda<?> lambdaExpression) {
        return lambdaExpression.getBody() != null && lambdaExpression.getExpression() == null;
    }

    public static boolean isShortLambda(CtLambda<?> lambdaExpression) {
        return lambdaExpression.getBody() == null && lambdaExpression.getExpression() != null;
    }

}
