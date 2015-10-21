package com.ipe.impl;

public class Variable {

    private String var ;
    private String FI_var;
    public Variable(String variable ) {
        this.var = variable;
        impl();
    }
    public String getFinalImport() {
        return FI_var;
    }
    private void impl() {

            //a as int = 1
            String[] ab = var.split("as");
            String var = ab[0];
            String type = "";
            String value = "";
            if (!ab[1].contains("=")) {
                type = ab[1];
            }
            else {
                String[] cd = ab[1].split("=");

                type = cd[0];
                value = cd[1];
            }
            if (value .equals(""))
            FI_var =  ("public static "+type+" "+var+";");
            else if (!value.equals("")) {
                FI_var = ("public static " + type + " " + var + " =" + " " + value + ";");
            }else if (value.contains("new")) {

            }
        }

}
