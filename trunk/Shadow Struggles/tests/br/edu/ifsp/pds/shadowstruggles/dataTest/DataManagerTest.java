package br.edu.ifsp.pds.shadowstruggles.dataTest;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class DataManagerTest {

	public static class SerializedObject implements Serializable {
		private int num;

		public SerializedObject() {
			this.num = 0;
		}

		public SerializedObject(int num) {
			this.num = num;
		}

		public int getNum() {
			return num;
		}

		@Override
		public void read(Json json, JsonValue jsonData) {
			this.num = json.readValue("num", Integer.class, jsonData);
		}

		@Override
		public void write(Json json) {
			json.writeValue("num", this.num);
		}
	}

	@Test
	public void testEncodingDecoding() {
		int expected = MathUtils.random(1000);
		FileHandle handle = Gdx.files.local("encoding-test.json");

		SerializedObject obj = new SerializedObject(expected);
		String encodedText = DataManager.encodeObject(new Json(), obj);
		handle.writeString(encodedText, false);

		String decodedText = DataManager.decodeFile(handle);
		SerializedObject retrievedObj = new Json().fromJson(
				SerializedObject.class, decodedText);

		try {
			if (retrievedObj.getNum() == expected)
				System.out.println("testEncodingDecoding: Success");
			else
				System.out.println("testEncodingDecoding: Fail");
		} catch (Exception ex) {
			System.out.println("testEncodingDecoding: Fail");
			ex.printStackTrace();
		}
	}

}
