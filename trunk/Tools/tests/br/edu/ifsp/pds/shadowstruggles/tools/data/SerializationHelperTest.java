package br.edu.ifsp.pds.shadowstruggles.tools.data;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SerializationHelperTest {

	public static class TestClass implements Serializable {
		public int attribute;
		public ArrayList<Integer> numbers;

		public TestClass() {
			this.numbers = new ArrayList<Integer>();
		}

		@Override
		public void read(Json arg0, JsonValue arg1) {
			try {
				SerializationHelper.read(this, arg0, arg1,
						new ArrayList<String>());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void write(Json arg0) {
			try {
				SerializationHelper.writeToJson(this, arg0,
						new ArrayList<String>());
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
		Random randomizer = new Random();
		int expectedValue = randomizer.nextInt();
		int num1 = randomizer.nextInt();
		int num2 = randomizer.nextInt();

		testClass.attribute = expectedValue;
		testClass.numbers.add(num1);
		testClass.numbers.add(num2);

		Json json = new Json();
		json.toJson(testClass, new File("test.json"));
		TestClass retrievedObject = json.fromJson(TestClass.class, new File(
				"test.json"));
		assertTrue(expectedValue == retrievedObject.attribute
				&& retrievedObject.numbers.get(0) == num1
				&& retrievedObject.numbers.get(1) == num2);
	}

}
