package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
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
	 *            includes all children directories). It must end with a forward
	 *            slash ('/').
	 * @param destinyDir
	 *            The output directory for the generated image and atlas files.
	 *            It must end with a forward slash ('/').
	 * @param packName
	 *            The name (without extensions) of the generated atlas file.
	 */
	public static void process(String originDir, String destinyDir,
			String packName) throws IOException {
		Array<TextureLocation> textures = retrieveTextures(Gdx.files
				.internal(originDir));
		Array<MyPage> pages = PackingAlgorithm.calculateAtlas(textures, 1024,
				1024);
		makeAtlasFile(pages, destinyDir, packName);
		MyImageProcessor.createImages(pages, destinyDir, packName);
	}

	private static Array<TextureLocation> retrieveTextures(FileHandle parentDir) {
		Array<TextureLocation> textures = new Array<TextureLocation>();
		FileHandle[] childrenDirs = parentDir.list();

		for (FileHandle dir : childrenDirs) {
			if (dir.isDirectory())
				textures.addAll(retrieveTextures(dir));
			else if (dir.extension().equals("png")
					|| dir.extension().equals("jpg")) {
				Pixmap pixmap = new Pixmap(dir);
				Rectangle rect = new Rectangle(0, 0, pixmap.getWidth(),
						pixmap.getHeight());
				textures.add(new TextureLocation(dir, rect));
			}
		}

		return textures;
	}

	private static void makeAtlasFile(Array<MyPage> pages, String destinyDir,
			String packName) throws IOException {
		String text = "";

		int i = 1;
		int pagesSize = pages.size;
		for (MyPage page : pages) {
			text += packName + i + ".png\n";
			text += "format: RGBA8888 \nfilter: Nearest,Nearest \nrepeat: none";

			Array<TextureLocation> textures = page.getTextures();
			for (TextureLocation texture : textures) {
				Rectangle rect = texture.getTextureRect();

				text += "\n"
						+ texture.getFile().pathWithoutExtension()
								.replace(destinyDir, "");
				text += "\n\trotate: false";
				text += "\n\txy: " + (int) rect.x + ", " + (int) rect.y;
				text += "\n\tsize: " + (int) rect.width + ", "
						+ (int) rect.height;
				text += "\n\torig: " + (int) rect.width + ", "
						+ (int) rect.height;
				text += "\n\toffset: 0, 0";
				text += "\n\tindex: -1";
			}

			i++;
			if (i <= pagesSize)
				text += "\n\n";
		}

		FileHandle file = Gdx.files.local(destinyDir + packName + ".atlas");
		file.file().createNewFile();
		file.writeString(text, false);
	}
}
