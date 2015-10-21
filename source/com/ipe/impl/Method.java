package com.ipe.impl;

public class Method {

    private String var;
    private String FI_var;
    public Method(String variable ) {
        this.var = variable;
        impl();
    }
    public String getFinalMethod() {
        return FI_var;
    }
    private void impl() {


            if (var.contains("print")) {
                String b= "";
                     b = var.replace("print", "").replace("(", "").replace(")", "");


                FI_var=("System.out.println("+b+");");

            }else {
                FI_var=(var+";");
            }



    }
}
