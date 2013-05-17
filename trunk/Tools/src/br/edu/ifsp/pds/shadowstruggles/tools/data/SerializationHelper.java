package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SerializationHelper {

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

	public static void read(Object object, Json json,
			JsonValue jsonValue, ArrayList<String> skipFields)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = object.getClass().getFields();

		for (Field field : fields) {
			if (!skipFields.contains(field.getName()))
				field.set(object, json.readValue(field.getName(), field.getType(),
						jsonValue));
		}
	}

}
