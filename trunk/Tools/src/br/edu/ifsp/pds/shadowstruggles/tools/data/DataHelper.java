package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.lang.reflect.Field;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class DataHelper {

	public static Json writeToJson(Object object, Json json)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = object.getClass().getFields();
		for (Field field : fields) {
			json.writeValue(field.getName(), field.get(object));
		}

		return json;
	}

	public static Object read(Object object, Class<?> c, Json json,
			JsonValue jsonValue) throws IllegalArgumentException,
			IllegalAccessException {
		Object obj = object;
		Field[] fields = c.getFields();

		for (Field field : fields) {
			field.set(obj,
					json.readValue(field.getName(), field.getType(), jsonValue));
		}

		return obj;
	}

}
