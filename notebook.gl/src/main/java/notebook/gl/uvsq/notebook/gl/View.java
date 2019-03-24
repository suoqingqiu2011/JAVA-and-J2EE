package notebook.gl.uvsq.notebook.gl;

public class View extends Command {

	private Receiver fileReceiver;
	
	public View(Receiver fileReceiver) {
		this.fileReceiver = fileReceiver;
	}

	@Override
	public void execute(String fileName) {
		((FileReceiver)fileReceiver).view(fileName);
		
	}

}
