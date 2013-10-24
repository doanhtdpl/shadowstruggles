package br.edu.ifsp.pds.shadowstruggles;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter {

	/**
	 * The application's entry point for desktop platforms.
	 */

	public static void main(String[] args) {
		// TexturePacker2.process("data/images/skin", "data/images/skin",
		// "skin");
		// System.out.println("Finish!");

		ApplicationListener listener = ShadowStruggles
				.getInstance(RunMode.TESTS);
		String title = "Shadow Struggles";
		int width = 960, height = 640;
		boolean useOpenGLES2 = false;
		try {
			new MyApplication(listener, title, width, height, useOpenGLES2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class MyApplication extends LwjglApplication {
		static private FileHandler fileTxt;
		static private SimpleFormatter formatterTxt;

		public MyApplication(ApplicationListener listener, String title,
				int width, int height, boolean useGL2) throws IOException {
			super(listener, title, width, height, useGL2);

			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

			logger.setLevel(Level.INFO);
			fileTxt = new FileHandler("Logging.txt", true);
			
			formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);
		}

		@Override
		public void debug(String tag, String message) {
			super.debug(tag, message);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINER);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).finer(
					tag + ": " + message);
		}

		@Override
		public void debug(String tag, String message, Throwable exception) {
			super.debug(tag, message, exception);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINER);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).finer(
					tag + ": " + message + " - " + exception.getMessage());
		}

		@Override
		public void error(String tag, String message) {
			super.error(tag, message);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.SEVERE);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(
					tag + ": " + message);
		}

		@Override
		public void error(String tag, String message, Throwable exception) {
			super.error(tag, message, exception);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.SEVERE);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(
					tag + ": " + message + " - " + exception.getMessage());
		}

		@Override
		public void log(String tag, String message) {
			super.log(tag, message);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).fine(
					tag + ": " + message);
		}

		@Override
		public void log(String tag, String message, Exception exception) {
			super.log(tag, message, exception);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).fine(
					tag + ": " + message + " - " + exception.getMessage());
		}
	}
}
