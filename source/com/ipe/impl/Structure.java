package com.ipe.impl;

public class Structure {
    public String Result;
    public Structure(String className) {
        impl(className);
    }
    private void impl(String className) {

        Result = " //`2`535234`1`2 \n" + "public class "
                + className + " { \n" + " //`2`6348351242`2"
                + "\n" + "public static void main(String[] args) "
                + " throws Exception" + "{ \n" + " //`2`6348352`2"
                + "\n" + "\n" + "} \n" + "}";

    }
}
