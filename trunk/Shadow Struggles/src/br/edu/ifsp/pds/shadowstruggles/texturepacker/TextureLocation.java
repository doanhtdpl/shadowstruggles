package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;

public class TextureLocation {
	private FileHandle file;
	private Rectangle textureRect;
	
	public TextureLocation(FileHandle file, Rectangle textureRect) {
		this.file = file;
		this.textureRect = textureRect;
	}
	
	public FileHandle getFile() {
		return file;
	}
	
	public void setFile(FileHandle file) {
		this.file = file;
	}
	
	public Rectangle getTextureRect() {
		return textureRect;
	}
	
	public void setTextureRect(Rectangle textureRect) {
		this.textureRect = textureRect;
	}
}
