package com.ipe.lang;

import java.io.*;

public class File {

    private void write(String contentX,String path) throws IOException {


        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(contentX);
        bufferedWriter.close();
    }
    public static String readFile(String path) {

        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        String curr;

        try {
            bufferedReader = new BufferedReader(new FileReader(path));

            while ((curr = bufferedReader.readLine() )!= null ) {
                stringBuffer.append(curr+"\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

}
