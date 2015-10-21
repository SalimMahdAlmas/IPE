package com.ipe.impl;

public class Import {

    private String IMPORTS;
    private String FI_IMPORTS ;
    public Import(String imports) {
        this.IMPORTS = imports;

            impl();


    }
    public String getFinalImport() {
        return FI_IMPORTS;
    }
    private void impl() {

            String b = IMPORTS.replace("@import", "").replace(" ","").replace(" ","");


            try {
                Class c = Class.forName("java.io."+b);

                FI_IMPORTS = ("import "+c.getName() +";") ;

            } catch (ClassNotFoundException e) {
              //  e.printStackTrace();

                try {
                    Class d = Class.forName("java.lang."+b);
                    FI_IMPORTS = ("import "+d.getName() +";") ;

                } catch (ClassNotFoundException e1) {
                  //  e1.printStackTrace();

                    try {
                        Class ef = Class.forName("java.util."+b);
                        FI_IMPORTS =("import "+ef.getName() +";") ;

                    } catch (ClassNotFoundException e2) {
                        e2.printStackTrace();

                        FI_IMPORTS = ("import "+b+";");
                    }
                }




        }

    }


}
