package notebook.gl.uvsq.notebook.gl;

public class Add extends Command {
	
	private Receiver fileReceiver;
	private Receiver directoryReceiver;
	private Receiver windowReceiver;

	public Add(Receiver fileReceiver, Receiver directoryReceiver, Receiver windowReceiver) {
		this.fileReceiver = fileReceiver;
		this.directoryReceiver = directoryReceiver;
		this.windowReceiver = windowReceiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(String fileName) {
		((FileReceiver)fileReceiver).add(fileName);
		((WindowReceiver)windowReceiver).open();
	}

}
