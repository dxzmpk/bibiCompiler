/**
 *
 */
package syntacticAnalyzer.LrOne;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class Main {
	private static final Logger logger = Main.getLogger(Main.class);

	public static Logger getLogger(Class<?> cls) {
		return Logger.getLogger(cls.getSimpleName());
	}



	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		if(args.length < 1) {
			System.exit(-1);
		}
		
		Main instance = new Main();

		try {
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(42);
		}
	}
}
