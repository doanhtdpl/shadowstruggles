package br.edu.ifsp.pds.shadowstruggles.data;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

public class TexturePacker {
	Pixmap pixmap;
	String imagePath;
	

	public TexturePacker(String imagePath) {
		FileHandle handle = Gdx.files.local("encoding-test.json");
		System.out.println("a");
		this.imagePath = imagePath;
		System.out.println("b");
		pixmap = PixmapIO.readCIM(new FileHandle(new File(
				"ShadowStruggles/data/images/cards/advanced_carbon.png")));
		System.out.println("c");
		// pixmap = PixmapIO.readCIM(new FileHandle(new File(imagePath)));
	}

}
