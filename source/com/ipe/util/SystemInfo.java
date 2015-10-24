package com.ipe.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SystemInfo {

    public static int SUPPORTED_OS = 1;
    public static int LINUX_OS = 2;


    public static int getOS_ID() {

        String a = java.lang.System.getProperty("os.name");

        if (a.contains("lin")|| a.contains("ux"))
        {
            return LINUX_OS;
        }
            return SUPPORTED_OS;
    }
    public static synchronized boolean Is_Python_Usable() {

        try {
            write("#!/usr/bin/env sh \n python --help","ps.sh");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Command.commandIt("chmod +x ps.sh");
        String a = Command.commandIt("./ps.sh");
        Command.commandIt("rm ps.sh");
        if (a.contains("usage: python [option]")) {
            return true;
        }
        else {
            return false;
        }

    }
    public static void write(String contentX,String path) throws IOException {


        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(contentX);
        bufferedWriter.close();
    }
}
