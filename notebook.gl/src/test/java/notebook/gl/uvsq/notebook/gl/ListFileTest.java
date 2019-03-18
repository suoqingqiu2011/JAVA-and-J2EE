package notebook.gl.uvsq.notebook.gl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListFileTest {

	private Command comm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Receiver directoryReceiver = new DirectoryReceiver();
		comm = new ListFile(directoryReceiver);
	}

	@Test
	public void testExecute() {
		comm.execute();
	}

	@Test
	public void testExecuteString() {
		comm.execute("1.txt");
	}

}
