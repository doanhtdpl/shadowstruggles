package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Page;
import com.badlogic.gdx.math.Rectangle;

public class TexturePacker {
	Pixmap pixmap;
	String imagePath;
	private ShadowStruggles game;
	FileHandle[] imagens;
	Texture[] textures;
	int qtdArquivos = 0, i = 0;
	long totalArea = 0;

	public TexturePacker(Page pages[], ShadowStruggles game) {
		//INICIO
		this.game = game;
		for (Page page : pages) {
			// pegar as texturas e suas localizações e colocar no pixmap, depois
			// gravar em png, não esquecendo de gravar também as localizações
		}
		//FIM

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
	private void continuePacking() {
		// find longest edge and total area of all source textures!
		int longest_width = 0;
		int longest_height = 0;
		long total_area = 0;
		for (Texture t : textures) {
			if (t != null) {
				if (t.getWidth() > longest_width)
					longest_width = t.getWidth();
				if (t.getHeight() > longest_height)
					longest_height = t.getHeight();
				total_area = +t.getWidth() * t.getHeight();
			}
		}

		// Create a single large rectangle big enough to fit all textures; round
		// up to the nearest power of two if necessary.
		int aux = 1;
		while ((longest_width * aux * longest_height) < total_area) {
			aux = aux * 2;
		}
		Rectangle r = new Rectangle(0, 0, longest_width * aux, longest_height);
		System.out.println("Rectangle width: " + r.width + ", height:"
				+ r.height + ".");

		// Place each texture, first by finding the texture with the largest
		// area and longest edge that has not yet been placed.
		boolean placed[] = new boolean[qtdArquivos];
		for (boolean b : placed) {
			b = false;
		}
		i = 0;
		int selected_index = 0;
		for (i = 0; i < qtdArquivos; i++) {
			longest_height = 0;
			longest_width = 0;
			if (textures[i] != null) {
				if (textures[i].getWidth() > longest_width && !placed[i])
					longest_width = textures[i].getWidth();
				if (textures[i].getHeight() > longest_height && !placed[i])
					longest_height = textures[i].getHeight();
				total_area = +textures[i].getWidth() * textures[i].getHeight();
			}
		}

	}

	// Pega as imagens e coloca no array textures
	private void getTextures() {
		i = 0;
		textures = new Texture[qtdArquivos];
		for (FileHandle imagem : imagens) {
			try {
				textures[i] = new Texture(imagem);
				System.out.println("Imagem " + imagem.name() + " adicionada!");
			} catch (Exception ex) {
				System.out.println("Imagem " + imagem.name()
						+ " NÃO adicionada! (" + ex.getMessage() + ")");
			}

			i++;
		}
	}

	// Estava usando para aprender a usar FileHandle, agora este método só
	// conta o número de imagens
	private void explore(FileHandle handle) {
		for (FileHandle file : handle.list()) {
			if (file.isDirectory() && file.name().compareTo(".svn") != 0) {
				System.out.println("\nFolder: --" + file.name() + "--");
				explore(file);
				System.out.println("End of Folder: " + file.name() + "\n");

			} else {
				if (file.extension().compareTo("png") == 0
						|| file.extension().compareTo("jpg") == 0) {
					System.out.println(file.name());
					qtdArquivos++;
				}
			}
		}
	}

	// Pega as imagens e coloca no array imagens[]
	private void getImages(FileHandle handle) {
		for (FileHandle file : handle.list()) {
			if (file.isDirectory() && file.name().compareTo(".svn") != 0) {
				getImages(file);
			} else {
				if (file.extension().compareTo("png") == 0
						|| file.extension().compareTo("jpg") == 0) {
					// add to array
					imagens[i] = file;
					System.out.println(file.name() + " added to array!");
					i++;
				}
			}
		}
	}*/
}
