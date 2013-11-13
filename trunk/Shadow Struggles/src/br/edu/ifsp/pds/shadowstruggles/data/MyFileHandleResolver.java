package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

/**
 * A custom FileHandle resolver which returns primarily Local file handles,
 * except when an Internal file handle is available.
 */
public class MyFileHandleResolver implements FileHandleResolver {

	@Override
	public FileHandle resolve(String fileName) {
		if (Gdx.files.internal(fileName).exists())
			return Gdx.files.internal(fileName);
		return Gdx.files.local(fileName);
	}

}
