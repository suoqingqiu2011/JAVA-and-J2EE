package notebook.gl.uvsq.notebook.gl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class AppTest {
	App app;
	@Before
	public void setUp() {
		App app = new App(null);
		this.app = app;
	}
	@Test
	public void testPrintJansiMenuDemo() throws IOException {
		app.printJansiMenuDemo();
	}

	@Test
	public void testPrintJansiLogoDemo() throws IOException {
		app.printJansiLogoDemo();
	}

}
