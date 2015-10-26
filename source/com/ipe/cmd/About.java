package com.ipe.cmd;

import com.ipe.Config;

public  class About extends CommandApp {

    protected About(String func_name, String args) {

        super(func_name, args);
    }

    @Override
    public void function(String argument) {

        System.out.println(" ipe  " + Config.VERSION+"\n"+
                "IPE Runtime Environment ");
    }

}