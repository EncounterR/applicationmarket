package com.oranth.applicationmarket.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecuteCommand {
	
    private ProcessBuilder mBuilder;
	public ExecuteCommand() {
        mBuilder = new ProcessBuilder();
        mBuilder.redirectErrorStream(true);
    }
	/**
	 * 
	 * @param command windows命令
	 * @return
	 * @throws IOException
	 */
	public BufferedReader windowsCommand(String ... command) throws IOException{
		Process process = mBuilder.command(command).start();        
        InputStream is = null;
        is = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf8"));
        return br;
	}
	/**
	 * 
	 * @param Command linux命令
	 * @return
	 * @throws IOException
	 */
	public BufferedReader linuxCommand(String Command) throws IOException{
		Process process = Runtime.getRuntime().exec(Command);			
		InputStreamReader ips = new InputStreamReader(process.getInputStream(),"utf8");
		BufferedReader br = new BufferedReader(ips);
		return br;
	}
}
