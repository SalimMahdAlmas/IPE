package com.ipe.util;

public class SystemInfo {

    public static int UNSUPPORTED_OS = 1;
    public static int LINUX_OS = 2;
    public static int WINDOWS_OS = 3;

    public static int getOS_ID() {

        String a = java.lang.System.getProperty("os.name");

        if (a.contains("lin")|| a.contains("ux"))
        {
            return LINUX_OS;
        }
        else if (a.contains("win") || a.contains("dows")) {
            return WINDOWS_OS;
        }
        else {
            return UNSUPPORTED_OS;
        }
    }


}
