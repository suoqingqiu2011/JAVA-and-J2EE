package notebook.gl.uvsq.notebook.gl;

public class Add extends Command {
	
	private Receiver fileReceiver;

	public Add(Receiver fileReceiver) {
		this.fileReceiver = fileReceiver;
	}



	@Override
	public void execute(String fileName) {
		((FileReceiver)fileReceiver).add(fileName);
	}

}
