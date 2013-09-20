package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public class Fighter extends Card implements Serializable {
	public static final String SIZE_SMALL = "SMALL";
	public static final String SIZE_MEDIUM = "MEDIUM";
	public static final String SIZE_LARGE = "LARGE";
	public static final float ATK_DELAY_SLOW = 3F;
	public static final float ATK_DELAY_NORMAL = 2F;
	public static final float ATK_DELAY_FAST = 1.5F;
	public static final float MOV_SPEED_SLOW = 0.10F;
	public static final float MOV_SPEED_NORMAL = 0.20F;
	public static final float MOV_SPEED_FAST = 0.3F;

	public int health;
	public int maxHealth;
	public int damage;
	public float speed;
	public int range;
	public boolean hasEffect;
	public float attackDelay;
	public String size;
	public float delay;
	public float moveTimer;
	public boolean attacking;

	public Fighter() {
		super();
		this.attackDelay = 0;
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		ArrayList<String> skipFields = new ArrayList<String>();
		skipFields.add("SIZE_SMALL");
		skipFields.add("SIZE_MEDIUM");
		skipFields.add("SIZE_LARGE");
		skipFields.add("ATK_DELAY_SLOW");
		skipFields.add("ATK_DELAY_NORMAL");
		skipFields.add("ATK_DELAY_FAST");
		skipFields.add("MOV_SPEED_SLOW");
		skipFields.add("MOV_SPEED_NORMAL");
		skipFields.add("MOV_SPEED_FAST");
		try {
			SerializationHelper.read(this, arg0, arg1, skipFields);
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(Json arg0) {
		ArrayList<String> skipFields = new ArrayList<String>();
		skipFields.add("SIZE_SMALL");
		skipFields.add("SIZE_MEDIUM");
		skipFields.add("SIZE_LARGE");
		skipFields.add("ATK_DELAY_SLOW");
		skipFields.add("ATK_DELAY_NORMAL");
		skipFields.add("ATK_DELAY_FAST");
		skipFields.add("MOV_SPEED_SLOW");
		skipFields.add("MOV_SPEED_NORMAL");
		skipFields.add("MOV_SPEED_FAST");
		try {
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>());
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
	
	

}
