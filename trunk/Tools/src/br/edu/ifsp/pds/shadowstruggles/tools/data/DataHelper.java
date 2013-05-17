package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class DataHelper {

	public static Json writeToJson(Object object, Json json,
			ArrayList<String> skipFields) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] fields = object.getClass().getFields();

		for (Field field : fields) {
			if (!skipFields.contains(field.getName()))
				json.writeValue(field.getName(), field.get(object));
		}

		return json;
	}

	public static Object read(Object object, Class<?> c, Json json,
			JsonValue jsonValue, ArrayList<String> skipFields)
			throws IllegalArgumentException, IllegalAccessException {
		Object obj = object;
		Field[] fields = c.getFields();

		for (Field field : fields) {
			if (!skipFields.contains(field.getName()))
				field.set(obj, json.readValue(field.getName(), field.getType(),
						jsonValue));
		}

		return obj;
	}

}
