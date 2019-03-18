package notebook.gl.uvsq.notebook.gl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ListFile extends Command {

	private Receiver directoryReceiver;
	public ListFile(Receiver directoryReceiver) {
		this.directoryReceiver = directoryReceiver;
	}
	public void execute() {
		((DirectoryReceiver)directoryReceiver).list();
	}
	public void execute(String fileName) {
		((DirectoryReceiver)directoryReceiver).list(fileName);
	}

}
