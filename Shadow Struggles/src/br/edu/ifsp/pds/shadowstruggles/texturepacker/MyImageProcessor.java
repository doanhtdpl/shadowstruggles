package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class MyImageProcessor {

	/**
	 * Generates the images correspondent to the pages' content and outputs them
	 * to the destiny directory, using the baseName as a parameter for the
	 * files' names.
	 * 
	 * @param pages
	 *            The atlas pages.
	 * @param destinyDir
	 *            The output directory. It must end with a forward slash ('/').
	 * @param baseName
	 *            Base name for the image files. Each file is generated
	 *            sequentially in the following format: baseNamei.png, where i
	 *            ranges from 1 to the total number of pages.
	 */
	public static void createImages(Array<MyPage> pages, String destinyDir,
			String baseName) throws IOException {
		int index = 1;
		for (MyPage page : pages) {
			Pixmap pixmap = new Pixmap(page.getWidth(), page.getHeight(),
					Pixmap.Format.RGBA8888);

			Array<TextureLocation> textures = page.getTextures();
			for (TextureLocation texture : textures) {
				Pixmap textureMap = new Pixmap(texture.getFile());
				Rectangle textureRect = texture.getTextureRect();

				pixmap.drawPixmap(textureMap, (int) textureRect.x,
						(int) textureRect.y);
			}

			FileHandle file = Gdx.files.local(destinyDir + baseName + index
					+ ".png");
			file.file().createNewFile();
			PixmapIO.writePNG(file, pixmap);

			index++;
		}
	}
}
