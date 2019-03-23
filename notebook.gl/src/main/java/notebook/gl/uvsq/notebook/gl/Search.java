package notebook.gl.uvsq.notebook.gl;

import java.util.List;

import notebook.gl.uvsq.notebook.gl.App.Range;

public class Search extends Command {

	private Receiver directoryReceiver;
	private App.Range range = App.Range.UNSET;
	public Search(Receiver directoryReceiver,App.Range range) {
		this.directoryReceiver = directoryReceiver;
		this.range = range;
	}


	@Override
	public void execute(String fileName) {
		if(range != App.Range.UNSET) {
			((DirectoryReceiver)directoryReceiver).search(fileName,range.toString());
		}else {
			((DirectoryReceiver)directoryReceiver).search(fileName);
		}
	}
	
	@Override
	public void update(DirectoryReceiver dr, Range order) {
		this.range = App.Range.UNSET;
		super.update(dr, order);
	}

}
