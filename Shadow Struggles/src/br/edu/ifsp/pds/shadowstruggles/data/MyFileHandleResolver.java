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

		// If the file name starts with the prefix for local files on Android,
		// then we don't need to resolve it; just instantiate a new FileHandle.
		String localPrefix = "/data/data/br.edu.ifsp.pds.shadowstruggles.android/files/";
		if (fileName.startsWith(localPrefix))
			return new FileHandle(fileName);
		return Gdx.files.local(fileName);
	}
}
