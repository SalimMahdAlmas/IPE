package com.ipe.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Command {

    public static String commandIt(String cmdline) {
        StringBuffer stringBuffer =new StringBuffer();
        ArrayList<String> output = command(cmdline, ".");
        if (null == output)
            System.out.println("\n\n Something Went Wrong With Your IPE File ");
        else
            for (String line : output)
                stringBuffer.append(line+"\n");

        return stringBuffer.toString();
    }
    public static ArrayList<String> command(final String cmdline,
                                     final String directory) {
        try {
            Process process =
                    new ProcessBuilder("bash", "-c", cmdline)
                            .redirectErrorStream(true)
                            .directory(new File(directory))
                            .start();

            ArrayList<String> output = new ArrayList<>();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ( (line = br.readLine()) != null )
                output.add(line);

            //There should really be a timeout here.
            if (0 != process.waitFor())
                return null;

            return output;

        } catch (Exception e) {
            //Warning: doing this is no good in high quality applications.
            //Instead, present appropriate error messages to the user.
            //But it's perfectly fine for prototyping.

            return null;
        }
    }
}
