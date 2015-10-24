package com.ipe.cmd;

import com.ipe.impl.Zygote;

import java.io.File;


public class Execute extends CommandApp {
    public Execute(String s, String mStuffArgs) {
        super(s,mStuffArgs);
    }

    @Override
    public void function(String argument) {

        if (new File(argument).exists()) {
            Zygote.execute(argument);

            System.out.print(Zygote.Result);
        }else {
            System.out.print("No Such file found");
        }
    }
}
