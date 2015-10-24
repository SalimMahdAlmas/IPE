package com.ipe.cmd;

public abstract class CommandApp {

    /**
     * To Store function names
     */
    private String mFunctionName;

    /**
     * To Store Argument
     */
    private String mArgument;

    /**
     * Initialing the class
     * @param functionName The function name you want
     * @param argument We push the argument to here
     */
    protected CommandApp(String functionName,String argument) {
        this.mArgument = argument;
        this.mFunctionName = functionName;
    }

    /**
     * If the command executed and with argument
     *
     *
     *
     * @param argument The argument comes to the method
     */
    public abstract void function(String argument);

    /**
     *
     * @return Function Name we will use
     */
    public String getFunctionName() {
        return mFunctionName;
    }

    /**
     *
     * @return Argument that will be returned here
     */
    public String getArgument() {
        return mArgument;
    }



}
