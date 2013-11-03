package br.edu.ifsp.pds.shadowstruggles.data;

import java.io.File;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

public class TexturePacker {
	Pixmap pixmap;
	String imagePath;
	private ShadowStruggles game;

	public TexturePacker(String imagePath, ShadowStruggles game) {
		this.game = game;
		System.out.println("a");
		this.imagePath = imagePath;
		System.out.println("b");
		pixmap = new Pixmap(
				Gdx.files.internal("data/images/cards/advanced_carbon.png"));

		System.out.println("c");
		// pixmap = PixmapIO.readCIM(new FileHandle(new File(imagePath)));
	}

}
