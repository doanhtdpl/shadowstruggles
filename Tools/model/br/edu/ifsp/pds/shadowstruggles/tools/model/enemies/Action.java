package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;



/**
 * An Action indicates what the Enemy should do through value attribution.
 * Examples of Actions are: use the bottom lane; invoke card DR-002; use the
 * nearest available tile to the base.
 */
public class Action implements Serializable {
	public static enum Attribute {
		LANE, TILE, CARD
	};

	/**
	 * Dynamic values such as random ones are retrieved during battles.
	 */
	public static enum DynamicValue {
		RANDOM_TILE, RANDOM_LANE, ATTACKED_LANE, NEXT_AVAILABLE_LANE, LANE_WITH_MORE_ENEMIES, LANE_WITH_LESS_ALLIES, LANES_WITH_MORE_ENEMIES_INVADING
	};

	public Attribute type;
	public Object value;
	public DynamicValue dynamicType;

	public Action() {
		this.type = Attribute.LANE;
		this.value = new Object();
		this.dynamicType = null;
	}

	public Action(Attribute type, Object value, DynamicValue dynamicType) {
		this.type = type;
		this.value = value;
		this.dynamicType = dynamicType;
	}

	@Override
	public String toString() {
		String attributeString = "";
		String valueString = "";

		if (this.type == Attribute.CARD) {
			attributeString = "card";
			valueString = ((Card) value).name;
		}

		if (this.type == Attribute.LANE) {
			attributeString = "lane";
			if (this.value != null)
				valueString = ((Integer) value).toString();
			else {
				if (this.dynamicType == DynamicValue.RANDOM_LANE)
					valueString = "randomly";
			}
		}

		if (this.type == Attribute.TILE) {
			attributeString = "column";
			if (this.value != null)
				valueString = ((Integer) value).toString();
			else {
				if (this.dynamicType == DynamicValue.RANDOM_TILE)
					valueString = "randomly";
			}
		}

		return "Choose " + attributeString + " " + valueString;
	}

	@Override
	 public void read(Json arg0, JsonValue arg1) {
	  try {
	   ArrayList<String> skipFields =  new ArrayList<String>( Arrays.asList(new String[] { "value" }));
	   SerializationHelper.read(this, arg0, arg1, skipFields);
	   this.value = arg0.readValue("value", null, arg1);
	  } catch (IllegalArgumentException e) {
	   Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
	   e.printStackTrace();
	  } catch (IllegalAccessException e) {
	   Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
	   e.printStackTrace();
	  }
	 }

	@Override
	 public void write(Json json) {
	  try {
	   ArrayList<String> skipFields = new ArrayList<String>( Arrays.asList(new String[] { "value" }));
	   SerializationHelper.writeToJson(this, json, skipFields);
	   json.writeValue("value", this.value, Action.class);
	  } catch (IllegalArgumentException e) {
	   Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
	   e.printStackTrace();
	  } catch (IllegalAccessException e) {
	   Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
	   e.printStackTrace();
	  }
	 }
}
