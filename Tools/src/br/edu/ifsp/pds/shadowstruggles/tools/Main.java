package br.edu.ifsp.pds.shadowstruggles.tools;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import br.edu.ifsp.pds.shadowstruggles.tools.data.DataHelperTest;
import br.edu.ifsp.pds.shadowstruggles.tools.utils.MyLogger;
import br.edu.ifsp.pds.shadowstruggles.tools.view.Window;

public class Main {

	public static void main(String[] args) {
//		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//
//		logger.setLevel(Level.INFO);
//		try {
//			FileHandler fileTxt = new FileHandler("Logging.txt");
//			SimpleFormatter formatterTxt = new SimpleFormatter();
//			fileTxt.setFormatter(formatterTxt);
//			logger.addHandler(fileTxt);
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Exception e = new Exception("Erro besta");
//		logger.info("Teste");
		
		 new Window();
	}

}
