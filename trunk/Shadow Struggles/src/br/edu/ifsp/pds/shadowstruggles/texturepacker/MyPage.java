package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import com.badlogic.gdx.utils.Array;

public class MyPage {
	private Array<TextureLocation> textures;
	private int width, height;
	
	public MyPage(Array<TextureLocation> textures, int width, int height) {
		this.textures = textures;
		this.width = width;
		this.height = height;
	}

	public Array<TextureLocation> getTextures() {
		return textures;
	}

	public void setTextures(Array<TextureLocation> textures) {
		this.textures = textures;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
