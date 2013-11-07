package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.utils.Array;

/**
 * Cross-platform alternative to {@link TexturePacker2}.
 */
public class MyTexturePacker {

	/**
	 * Processes a new atlas.
	 * 
	 * @param originDir
	 *            The parent directory of the image files to be packed (which
	 *            includes all children directories).
	 * @param destinyDir
	 *            The output directory for the generated image and atlas files.
	 * @param packName
	 *            The name (without extensions) of the generated atlas file.
	 */
	public static void process(String originDir, String destinyDir,
			String packName) {
		Array<TextureLocation> textures = retrieveTextures(originDir);
		Array<MyPage> pages = PackingAlgorithm.calculateAtlas(textures, 1024, 1024);
		MyImageProcessor.createImages(pages, destinyDir, packName);
		makeAtlasFile(pages, destinyDir, packName);
	}

	private static Array<TextureLocation> retrieveTextures(String originDir) {
		return retrieveTextures(Gdx.files.internal(originDir));
	}

	private static Array<TextureLocation> retrieveTextures(FileHandle parentDir) {
		Array<TextureLocation> textures = new Array<TextureLocation>();
		FileHandle[] childrenDirs = parentDir.list();

		for (FileHandle dir : childrenDirs) {
			if (dir.isDirectory())
				textures.addAll(retrieveTextures(dir));
			else if (dir.extension().equals(".png")
					|| dir.extension().equals(".jpg")) {
				Texture texture = new Texture(dir);
				Rectangle rect = new Rectangle(0, 0, texture.getWidth(),
						texture.getHeight());
				textures.add(new TextureLocation(dir, rect));
			}
		}

		return textures;
	}

	private static void makeAtlasFile(Array<MyPage> pages, String destinyDir,
			String packName) {
		String text = "";

		int i = 1;
		int pagesSize = pages.size;
		for (MyPage page : pages) {
			text += packName + i + ".png\n";
			text += "format: RGBA8888 \nfilter: Nearest,Nearest \nrepeat: none";

			Array<TextureLocation> textures = page.getTextures();
			for (TextureLocation texture : textures) {
				Rectangle rect = texture.getTextureRect();

				text += texture.getFile().nameWithoutExtension();
				text += "\n\trotate: false";
				text += "\n\txy: " + rect.x + ", " + rect.y;
				text += "\n\tsize: " + rect.width + ", " + rect.height;
				text += "\n\torig: " + rect.width + ", " + rect.height;
				text += "\n\toffset: 0, 0";
				text += "\n\tindex: -1";
			}

			i++;
			if (i < pagesSize)
				text += "\n";
		}

		FileHandle file = Gdx.files.local(destinyDir + packName + ".atlas");
		file.mkdirs();
		file.writeString(text, false);
	}
}
