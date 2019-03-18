package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReceiver extends Receiver {

	public void del(String fileName) {
		
		cmd = "cmd /c \"del "+fileName+"\"";
	
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk"); 
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null){
				System.out.println(line);
			}
			is.close();
			isr.close();
			br.close();
		}catch (IOException e){
			e.printStackTrace();
		}	
	}

	public void add(String fileName) {

		cmd = "cmd /c \"type nul>"+fileName+"\"";
		try {
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk"); 
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null){
				System.out.println(line);
			}
			is.close();
			isr.close();
			br.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
