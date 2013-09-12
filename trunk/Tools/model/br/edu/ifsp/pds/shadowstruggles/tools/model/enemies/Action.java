package br.edu.ifsp.pds.shadowstruggles.tools.model.enemies;

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
		LANE, COLUMN, CARD
	};

	/**
	 * Dynamic values such as random ones are retrieved during battles.
	 */
	public static enum DynamicValue {
		RANDOM_COLUMN, RANDOM_LANE
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

		if (this.type == Attribute.COLUMN) {
			attributeString = "column";
			if (this.value != null)
				valueString = ((Integer) value).toString();
			else {
				if (this.dynamicType == DynamicValue.RANDOM_COLUMN)
					valueString = "randomly";
			}
		}

		return "Choose " + attributeString + " " + valueString;
	}

	@Override
	public void read(Json json, JsonValue jsonValue) {
		this.type = json.readValue("type", Attribute.class, jsonValue);
		this.value = json.readValue("value", Object.class, jsonValue);
		this.dynamicType = json.readValue("dynamicType", DynamicValue.class,
				jsonValue);
	}

	@Override
	public void write(Json json) {
		json.writeValue("type", this.type);
		json.writeValue("value", this.value);
		json.writeValue("dynamicType", this.dynamicType);
	}
}
