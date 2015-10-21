package com.ipe.impl;

public class Structure {
    public String Result;
    public Structure(String className) {
        impl(className);
    }
    private void impl(String className) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" //`2`535234`1`2 \n");
        stringBuilder.append("public class ");
        stringBuilder.append(className);
        stringBuilder.append(" { \n");
        stringBuilder.append(" //`2`6348351242`2");
        stringBuilder.append("\n");
        stringBuilder.append("public static void main(String[] args) " + " throws Exception");
        stringBuilder.append("{ \n");
        stringBuilder.append(" //`2`6348352`2");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("} \n");
        stringBuilder.append("}");

        Result = stringBuilder.toString();

    }
}
