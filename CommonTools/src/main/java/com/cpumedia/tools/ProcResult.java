package com.cpumedia.tools;

import java.io.IOException;

/**
 * Get command line output
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class ProcResult {

    public ProcResult() { ; }

	/**
	 * Execute process from command line and return output as a String
	 *
	 * @param cmd	command line to be used for launching process
	 * @return output from process returned as String
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    public static String consumeAsString(String cmd) 	{
    
        String result = null;

    	try	{
	        final Process p = Runtime.getRuntime().exec(cmd);
    	    final ProcResultReader stderr = new ProcResultReader(p.getErrorStream(), "STDERR");
        	final ProcResultReader stdout = new ProcResultReader(p.getInputStream(), "STDOUT");
        	stderr.start();
        	stdout.start();
        	final int exitValue = p.waitFor();
        	if (exitValue == 0)	{
            	result = stdout.toString();
        	}
	        else{
    	        result = stderr.toString();
        	}
        }
    	catch(IOException ioe)	{
			ioe.printStackTrace();
        }
    	catch(InterruptedException ie)	{
			ie.printStackTrace();
        }

        return result;
    }

}
