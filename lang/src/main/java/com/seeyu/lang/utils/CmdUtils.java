package com.seeyu.lang.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/1/25
 */
public class CmdUtils {

	public static final int WINDOWS = 1;

	public static final int LINUX = 2;

	/**
	 * 执行命令并返回消息
	 * @param commands
	 * @return
	 * @throws IOException
	 * @throws CmdException
	 */
	public static String[] exec(String[] commands, int os) throws IOException, InterruptedException, CmdException{
		return exec(commands, "UTF-8", os);
	}

	/**
	 * 执行命令并返回消息
	 * @param commands
	 * @param encode 指定的编码方式
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws CmdException
	 */
	public static String[] exec(String[] commands, String encode, int os) throws IOException, InterruptedException, CmdException{
		return exec(commands, encode, os, false);
	}


	/**
	 * 执行命令并返回消息
	 * @param commands
	 * @param encode 指定的编码方式
	 * @param ignoreError 忽略异常
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws CmdException
	 */
	public static String[] exec(String[] commands,String encode, int os, boolean ignoreError) throws IOException, InterruptedException, CmdException{
		Process process = null;
		List<String> outputList = new ArrayList<String>();
		List<String> errorOutputList = new ArrayList<String>();
		try{
			process = execCmd(commands, os);
			ProcessOutputThread outputThread = new ProcessOutputThread(process, process.getInputStream(), encode);
			ProcessOutputThread errorOutputThread = new ProcessOutputThread(process, process.getErrorStream(), encode);
			outputThread.start();
			errorOutputThread.start();
			process.waitFor();
			errorOutputThread.join();
			outputThread.join();
			outputList = outputThread.getOutputList();
			errorOutputList = errorOutputThread.getOutputList();
		}finally{
			if(process != null){
				process.destroy();
			}
			//cmd执行出现错误时
			if(!ignoreError && errorOutputList.size() != 0){
//				if(!ignoreError){
					throw new CmdException(StringUtils.StringArrays2String(errorOutputList.toArray(new String[]{}),"\n"));
//				}
//				else if(outputList.size() == 0){
//					return errorOutputList.toArray(new String[]{});
//				}
			}
		}
		outputList.addAll(errorOutputList);
		return outputList.toArray(new String[0]);
	}

	/**
	 * 拼装多条命令
	 * @param commands
	 * @return
	 */
	private static String genWindowsCommands(String[] commands){
		StringBuilder cmd = new StringBuilder("cmd /c ");
		for(int i = 0; i < commands.length; i++){
			if(i != 0){
				cmd.append("&&");
			}
			cmd.append(commands[i]);
		}
		return cmd.toString();
	}

	/**
	 * 拼装多条命令
	 * @param commands
	 * @return
	 */
	private static String[] genLinuxCommands(String[] commands){
		List<String> cmdList = new ArrayList<>(commands.length + 2);
		cmdList.add("/bin/bash");
		cmdList.add("-c");
		StringBuilder cmd = new StringBuilder();
		for(int i = 0; i < commands.length; i++){
			if(i != 0){
				cmd.append(";");
			}
			cmd.append(commands[i]);
		}
		cmdList.add(cmd.toString());
		return cmdList.toArray(new String[]{});
	}


	private static Process execCmd(String[] commands, int os) throws IOException {
		if(WINDOWS == os){
			return windowsExec(commands);
		}
		else if(LINUX == os){
			return linuxExec(commands);
		}
		else{
			throw new RuntimeException("未知os: " + os);
		}
	}

	private static Process windowsExec(String[] commands) throws IOException {
		String cmdStr = genWindowsCommands(commands);
		Process process = Runtime.getRuntime().exec(cmdStr);
		return process;
	}

	private static Process linuxExec(String[] commands) throws IOException {
		String[] cmd = genLinuxCommands(commands);
		Process process = Runtime.getRuntime().exec(cmd);
		return process;
	}



	/**
	 * 异步读取输出流
	 * @author zhaoyongchao
	 */
	static class ProcessOutputThread extends Thread {
		private Process process;
		private InputStream is;
		private String encode;
		private List<String> outputList;

		public ProcessOutputThread(Process process, InputStream is, String encode) throws IOException {
			if (null == is) {
				throw new IOException("the provided InputStream is null");
			}
			this.process = process;
			this.encode = encode;
			this.is = is;
			this.outputList = new ArrayList<String>();
		}

		public List<String> getOutputList() {
			return this.outputList;
		}

		@Override
		public void run() {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(this.is, encode));
				String output = null;
				while (null != (output = br.readLine())) {
					this.outputList.add(output);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != br) {
						br.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 执行cmd 命令时抛出的异常
	 * @author zhaoyongchao
	 *
	 */
	public static class CmdException extends Exception{
		public CmdException(){
			super();
		}

		public CmdException(String message){
			super(message);
		}

		public CmdException(String message, Throwable cause){
			super(message, cause);
		}

		public CmdException(Throwable cause){
			super(cause);
		}

	}

}
