package notebook.gl.uvsq.notebook.gl;

public class Delete extends Command {

	private Receiver fileReceiver;
	
	public Delete(Receiver fileReceiver) {
		// TODO Auto-generated constructor stub
		this.fileReceiver = fileReceiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(String fileName) {
		((FileReceiver)fileReceiver).del(fileName);
	}

}
