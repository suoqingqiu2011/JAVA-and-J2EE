package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DirectoryReceiver extends Receiver {

	public void list() {
		cmd = "cmd /c \"dir\"";
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
	
	public void list(String fileName) {
		cmd = "cmd /c \"dir "+fileName+"\"";
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
