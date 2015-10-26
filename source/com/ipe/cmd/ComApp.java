package com.ipe.cmd;

import java.util.ArrayList;

/**
 *
 * Command Line App
 *
 * How to Play with Argument in Java
 *
 * And How to Parse
 *
 *
 * How this Works ??
 *
 * First of all We get argument from main method then we split it and get command
 * what are doing using some other class we check is it available functions if available
 * then we move to the work they want to do if not match then we print the usage
 *
 * Credits:
 * Allah
 *
 * @author Sahid Almas
 */

public class ComApp {

    private static final ArrayList<CommandApp> mCommands  = new ArrayList<>();
    private static String mArgsCommand = "";
    private static String mStuffArgs = "";

    public static void main(String[] args) {



        if (args.length > 0) {

            mArgsCommand = args[0];

        }

        if (args.length > 1) {
            StringBuilder stringBuffer = new StringBuilder();
            for (int i = 1 ; i < args.length ; i++) {

                if (i == 1 ) {
                    stringBuffer.append(args[i]);
                }else if (i >= 1 && i <=  args.length ) {
                    stringBuffer.append(" ").append(args[i]);
                } else if (i == args.length) {
                    stringBuffer.append(args[i]);
                }
            }
            mStuffArgs = stringBuffer.toString();
        }


        mCommands.add(new Execute("-e",mStuffArgs));
        mCommands.add(new About("-about",mStuffArgs));
        int CURSOR = 0;
        for (CommandApp command : mCommands) {

            if (command.getFunctionName().equals( mArgsCommand )) {
                command.function(mStuffArgs);
            }
            else {
                CURSOR++;
            }

        }
        if (!mArgsCommand.equals("")) {
            while (CURSOR == mCommands.size()) {
                String USAGE = " IPE \n" +
                        " Usage :" +
                        " ComApp -e [file path] (to execute a ipe) \n" +
                        " ComApp -about (about)\n" +
                        "";
                System.out.println(USAGE);
                CURSOR--;
            }
        }





    }





}