package br.edu.ifsp.pds.shadowstruggles.data;

import java.io.File;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;

public class TexturePacker {
	Pixmap pixmap;
	String imagePath;
	private ShadowStruggles game;
	FileHandle[] imagens;
	Texture[] textures;
	int qtdArquivos = 0, i = 0;
	long totalArea = 0;

	public TexturePacker(String imagePath, ShadowStruggles game) {
		this.game = game;
		// System.out.println("a");
		// this.imagePath = imagePath;
		// System.out.println("b");
		// pixmap = new Pixmap(
		// Gdx.files.internal("data/images/cards/advanced_carbon.png"));
		//
		// System.out.println("c");
		// // pixmap = PixmapIO.readCIM(new FileHandle(new File(imagePath)));

		System.out.println("Nome de todos os arquivos de imagens:\n");
		FileHandle handle = Gdx.files.internal("data/images");
		explore(handle);
		System.out.println("End of explore, qtdArquivos = " + qtdArquivos);
		imagens = new FileHandle[qtdArquivos];
		getImages(handle);
		System.out.println("End of getImages, i = " + i);
		getTextures();
		System.out.println("End of getTextures, i = " + i);

		// System.out.println("Tentativa de manipular imagem!");
		// // FileHandle image =
		// // Gdx.files.internal("data/images/card_effects/cloning.png");
		// FileHandle image = Gdx.files
		// .internal("data/images/card_images/cloning.jpg");
		// if (image.exists())
		// System.out.println("O arquivo existe!");
		//
		// Texture tex = new Texture(image);
		// System.out.println("Imagem lida!\nAltura: " + tex.getHeight()
		// + "\nLargura: " + tex.getWidth());

	}

	// Pega as imagens e coloca no array textures
	private void getTextures() {
		System.out.println("a");
		i=0;
		System.out.println("b");
		textures = new Texture[qtdArquivos];
		System.out.println("c");
		for(FileHandle imagem : imagens){
			textures[i]= new Texture(imagem);
			System.out.println("d");
			i++;
			System.out.println("e");
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
	}
}
