package br.edu.ifsp.pds.shadowstruggles.tools.data;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Random;

import org.junit.Test;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class DataHelperTest {

	public static class TestClass implements Serializable {
		public int attribute;

		@Override
		public void read(Json arg0, JsonValue arg1) {
			try {
				TestClass read = (TestClass) DataHelper.read(this,
						TestClass.class, arg0, arg1);
				this.attribute = read.attribute;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void write(Json arg0) {
			 try {
			 Json json = DataHelper.writeToJson(this, arg0);
			 arg0 = json;
			 } catch (IllegalArgumentException e) {
			 e.printStackTrace();
			 } catch (IllegalAccessException e) {
			 e.printStackTrace();
			 }
		}

	}

	@Test
	public void testWriteReadJson() {
		TestClass testClass = new TestClass();
		int expectedValue = new Random().nextInt();
		testClass.attribute = expectedValue;

		Json json = new Json();
		json.toJson(testClass, new File("test.json"));
		TestClass retrievedObject = json.fromJson(TestClass.class, new File(
				"test.json"));
		assertEquals(expectedValue, retrievedObject.attribute);
	}

}