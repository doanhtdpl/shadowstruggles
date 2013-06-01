package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

/**
 * Auxiliary class for automatic serialization of objects in a way such that
 * compatibility between systems (Java core libraries and libgdx' libraries) is
 * maintained.
 */

public class SerializationHelper {

	/**
	 * Serializes an object into a Json object.
	 * 
	 * @param skipFields
	 *            A list containing the names of the object's attributes which
	 *            should not be automatically serialized, requiring a specific
	 *            writing method or no writing at all. It must not be null.
	 */
	public static void writeToJson(Object object, Json json,
			ArrayList<String> skipFields) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] fields = object.getClass().getFields();

		for (Field field : fields) {
			if (!skipFields.contains(field.getName())) {
				json.writeValue(field.getName(), field.get(object));
			}
		}
	}

	/**
	 * Reads a Json object into the given object.
	 * 
	 * @param skipFields
	 *            A list containing the names of the object's attributes which
	 *            should not be automatically read, requiring a specific reading
	 *            method or no reading at all. It must not be null.
	 */
	public static void read(Object object, Json json, JsonValue jsonValue,
			ArrayList<String> skipFields) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] fields = object.getClass().getFields();

		for (Field field : fields) {
			if (!skipFields.contains(field.getName()))
				field.set(object, json.readValue(field.getName(),
						field.getType(), jsonValue));
		}
	}

}
