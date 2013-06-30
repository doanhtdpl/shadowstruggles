package br.edu.ifsp.pds.shadowstruggles;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles.RunMode;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class DesktopStarter {

	/** The application's entry point for desktop platforms.
	 * @param args
	 */
	public static void main(String[] args) {
//		TexturePacker2.process("data/images/skin", "data/images/skin", "skin");
//		TexturePacker2.process("data/images/game_ui_images", "data/images/game_ui_images", "game_ui_images");
//        TexturePacker2.process("data/images/card_attacking", "data/images/card_attacking", "card_attacking");
//        TexturePacker2.process("data/images/card_effects", "data/images/card_effects", "card_effects");
//        TexturePacker2.process("data/images/card_images", "data/images/card_images", "card_images");
//        TexturePacker2.process("data/images/card_walking", "data/images/card_walking", "card_walking");
//        TexturePacker2.process("data/images/cards", "data/images/cards", "cards");
//		TexturePacker2.process("data/images/char", "data/images/char", "char");
//		System.out.println("Finish!");
		
		ApplicationListener listener = new ShadowStruggles(RunMode.DEBUG);
		String title = "Shadow Struggles";
        int width = 960, height = 640;
        boolean useOpenGLES2 = false;
        new LwjglApplication( listener, title, width, height, useOpenGLES2 );
	}

}
