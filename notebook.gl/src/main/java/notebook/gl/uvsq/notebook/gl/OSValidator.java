package notebook.gl.uvsq.notebook.gl;

import java.io.IOException;

/**
 * Cette class {@code OSValidator} contient des fonctions qui sont toutes statiques et configurent sur des différents systèmes .
 *
 * @author Administrator
 * @since JDK1.8
 */
public class OSValidator {
	
private static String OS = System.getProperty("os.name").toLowerCase();
	
/*	public static void main(String[] args) {
		
		System.out.println(OS);
		
		if (isWindows()=="win") {
			System.out.println("This is Windows");
		} else if (isMac()) {
			System.out.println("This is Mac");
		} else if (isUnix()=="unix") {
			System.out.println("This is Unix or Linux");
		} else if (isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
	}
*/
	/**
	 * configuration pour windows
	 * @return ""
	 */
	public static String isWindows() {

		if (OS.indexOf("win") >= 0)
			return "win";
		return "";
	}
	
	/**
	 * configuration pour mac
	 * @return OS.indexOf("mac") 
	 */
	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}
	/**
	 * configuration pour unix
	 * @return "unix"
	 */
	public static String isUnix() {

		if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 )
			return "unix";
		return "";
	}
	/**
	 * configuration pour solaris
	 * @return OS.indexOf("sunos") 
	 */
	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}
