package com.ipe.util;

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



}
