package br.edu.ifsp.pds.shadowstruggles.texturepacker;

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
	 *            The output directory.
	 * @param baseName
	 *            Base name for the image files. Each file is generated
	 *            sequentially in the following format: baseNamei.png, where i
	 *            ranges from 1 to the total number of pages.
	 */
	public static void createImages(Array<MyPage> pages, String destinyDir,
			String baseName) {
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
			file.mkdirs();
			PixmapIO.writePNG(file, pixmap);
		}

		// FIM

		/*
		 * // System.out.println("a"); // this.imagePath = imagePath; //
		 * System.out.println("b"); // pixmap = new Pixmap( //
		 * Gdx.files.internal("data/images/cards/advanced_carbon.png")); // //
		 * System.out.println("c"); // // pixmap = PixmapIO.readCIM(new
		 * FileHandle(new File(imagePath)));
		 * 
		 * System.out.println("Nome de todos os arquivos de imagens:\n");
		 * FileHandle handle = Gdx.files.internal("data/images");
		 * explore(handle); System.out.println("End of explore, qtdArquivos = "
		 * + qtdArquivos); imagens = new FileHandle[qtdArquivos];
		 * getImages(handle); System.out.println("End of getImages, i = " + i);
		 * getTextures(); System.out.println("End of getTextures, i = " + i);
		 * continuePacking(); System.out.println("End of continuePacking, i = "
		 * + i);
		 * 
		 * // System.out.println("Tentativa de manipular imagem!"); // //
		 * FileHandle image = // //
		 * Gdx.files.internal("data/images/card_effects/cloning.png"); //
		 * FileHandle image = Gdx.files //
		 * .internal("data/images/card_images/cloning.jpg"); // if
		 * (image.exists()) // System.out.println("O arquivo existe!"); // //
		 * Texture tex = new Texture(image); //
		 * System.out.println("Imagem lida!\nAltura: " + tex.getHeight() // +
		 * "\nLargura: " + tex.getWidth());
		 */
	}

	/*
	 * private void continuePacking() { // find longest edge and total area of
	 * all source textures! int longest_width = 0; int longest_height = 0; long
	 * total_area = 0; for (Texture t : textures) { if (t != null) { if
	 * (t.getWidth() > longest_width) longest_width = t.getWidth(); if
	 * (t.getHeight() > longest_height) longest_height = t.getHeight();
	 * total_area = +t.getWidth() * t.getHeight(); } }
	 * 
	 * // Create a single large rectangle big enough to fit all textures; round
	 * // up to the nearest power of two if necessary. int aux = 1; while
	 * ((longest_width * aux * longest_height) < total_area) { aux = aux * 2; }
	 * Rectangle r = new Rectangle(0, 0, longest_width * aux, longest_height);
	 * System.out.println("Rectangle width: " + r.width + ", height:" + r.height
	 * + ".");
	 * 
	 * // Place each texture, first by finding the texture with the largest //
	 * area and longest edge that has not yet been placed. boolean placed[] =
	 * new boolean[qtdArquivos]; for (boolean b : placed) { b = false; } i = 0;
	 * int selected_index = 0; for (i = 0; i < qtdArquivos; i++) {
	 * longest_height = 0; longest_width = 0; if (textures[i] != null) { if
	 * (textures[i].getWidth() > longest_width && !placed[i]) longest_width =
	 * textures[i].getWidth(); if (textures[i].getHeight() > longest_height &&
	 * !placed[i]) longest_height = textures[i].getHeight(); total_area =
	 * +textures[i].getWidth() * textures[i].getHeight(); } }
	 * 
	 * }
	 * 
	 * // Pega as imagens e coloca no array textures private void getTextures()
	 * { i = 0; textures = new Texture[qtdArquivos]; for (FileHandle imagem :
	 * imagens) { try { textures[i] = new Texture(imagem);
	 * System.out.println("Imagem " + imagem.name() + " adicionada!"); } catch
	 * (Exception ex) { System.out.println("Imagem " + imagem.name() +
	 * " NÃO adicionada! (" + ex.getMessage() + ")"); }
	 * 
	 * i++; } }
	 * 
	 * // Estava usando para aprender a usar FileHandle, agora este método só //
	 * conta o número de imagens private void explore(FileHandle handle) { for
	 * (FileHandle file : handle.list()) { if (file.isDirectory() &&
	 * file.name().compareTo(".svn") != 0) { System.out.println("\nFolder: --" +
	 * file.name() + "--"); explore(file); System.out.println("End of Folder: "
	 * + file.name() + "\n");
	 * 
	 * } else { if (file.extension().compareTo("png") == 0 ||
	 * file.extension().compareTo("jpg") == 0) {
	 * System.out.println(file.name()); qtdArquivos++; } } } }
	 * 
	 * // Pega as imagens e coloca no array imagens[] private void
	 * getImages(FileHandle handle) { for (FileHandle file : handle.list()) { if
	 * (file.isDirectory() && file.name().compareTo(".svn") != 0) {
	 * getImages(file); } else { if (file.extension().compareTo("png") == 0 ||
	 * file.extension().compareTo("jpg") == 0) { // add to array imagens[i] =
	 * file; System.out.println(file.name() + " added to array!"); i++; } } } }
	 */
}
