package notebook.gl.uvsq.notebook.gl;

import java.io.File;
import java.util.List;

import org.asciidoctor.AsciiDocDirectoryWalker;
import org.asciidoctor.DirectoryWalker;

public abstract class Command {

	public abstract void execute(String fileName);
	public void update(DirectoryReceiver dr) {
		DirectoryWalker directoryWalker = new AsciiDocDirectoryWalker(".");
		List<File> asciidocFiles = directoryWalker.scan();
		System.out.println(asciidocFiles.size());
	}

}
