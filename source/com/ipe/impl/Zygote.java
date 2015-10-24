package com.ipe.impl;

import com.ipe.util.Command;
import com.ipe.util.SystemInfo;

import java.io.*;
import java.util.ArrayList;

public class Zygote {
    public static String content;
    public String Class_Name;
    public static String Result;

    public Zygote(String file) {

        if (file.contains(".ipe")) {
            if (new File(file).exists()) {

                readFile((new File(file)));

            }
        }else {
            try {
                throw new Exception("illegal file not allow");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void impl() {

        String class_Name = "";
        String class_Period = "";

        ArrayList<String> variables = new ArrayList<>();
        ArrayList<String> imports = new ArrayList<>();
        ArrayList<String> methods = new ArrayList<>();
        ArrayList<String> un_Tas = new ArrayList<>();
        for (String a : content.split("\n")) {

            if (a.startsWith("@class")) {
                class_Name = a.replace("@class ","");

                class_Period = new Structure(a.replace("@class","")).Result;


            }else if (a.contains(" as ")) {
                Variable variable = new Variable(a);
                variables.add(variable.getFinalImport());

            }else if (a.contains("(") && !a.contains("=") && a.contains(")") || a.contains("+") || a.contains("-") || a.contains("*") || a.contains("/") ) {
                Method method = new Method(a);

                methods.add(method.getFinalMethod());

            }else if (a.startsWith("@import")) {
                Import importS = new Import(a);

                imports.add(importS.getFinalImport());

            }else {
                if (!a.contains(""))
                un_Tas.add(a);
            }

        }
        Class_Name = class_Name;
        for (String a : variables) {
            class_Period = class_Period.replace("//`2`6348351242`2",a+"\n //`2`6348351242`2");
        }
        for (String b : methods ) {
            class_Period = class_Period.replace("//`2`6348352`2",b+"\n //`2`6348352`2");

        }// //`2`535234`1`2
        for (String c : imports) {
            class_Period = class_Period.replace("//`2`535234`1`2 ",c+"\n //`2`535234`1`2 ");
        }

        for (String b : un_Tas ) {
          //  class_Period = class_Period.replace("//`2`6348352`2",b+"\n //`2`6348352`2");

            System.out.println("Unrecognised tags ("+b+")");

        }// //`2`535234`1`2



        try {
            File file = new File(class_Name+".java");
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            write(class_Period, class_Name + ".java");

            if (!SystemInfo.Is_Python_Usable()) {


                if (SystemInfo.getOS_ID() == SystemInfo.LINUX_OS) {
                    write("#!/usr/bin/env sh \n javac " + class_Name + ".java \n" + "java " + class_Name, "run.sh");


                    System.out.println(Command.commandIt("chmod +x run.sh"));
                    Result = (Command.commandIt("./run.sh"));
                } else if (SystemInfo.getOS_ID() == SystemInfo.SUPPORTED_OS) {

                    Command.commandIt("javac " + class_Name + ".java");
                    Result = (Command.commandIt("java" + class_Name));


                }
            }else {


                Command.commandIt("chmod +x ./python/execute_ipe ");
                Result =    Command.commandIt("./python/execute_ipe "+class_Name);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private   void readFile(File file){

        BufferedReader bufferedReader = null;
        StringBuilder stringBuffer = new StringBuilder();
        String curr;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            while ((curr = bufferedReader.readLine() )!= null ) {
             stringBuffer.append(curr+"\n");
            }

            content = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    impl();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void write(String contentX,String path) throws IOException {


        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(contentX);
        bufferedWriter.close();
    }


    public static void execute(String a ) {
        Zygote zygote;
        synchronized (zygote = new Zygote(a)) {

            new File(zygote.Class_Name+".java").delete();
            new File(zygote.Class_Name+".class").delete();
            new File("run.sh").delete();

        }


    }

}
