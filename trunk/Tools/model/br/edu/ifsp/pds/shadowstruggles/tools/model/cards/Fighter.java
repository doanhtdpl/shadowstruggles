package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.logging.Logger;
import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public class Fighter extends Card implements Serializable {
	public static enum FighterSize {
		SMALL, MEDIUM, LARGE
	};
	
	public static final float ATK_DELAY_SLOW = 3F;
	public static final float ATK_DELAY_NORMAL = 2F;
	public static final float ATK_DELAY_FAST = 1.5F;
	public static final float MOV_SPEED_SLOW = 0.10F;
	public static final float MOV_SPEED_NORMAL = 0.20F;
	public static final float MOV_SPEED_FAST = 0.3F;
	
	public int health;
	public int damage;	
	public int range;
	public boolean hasEffect;
	public float speed;
	public float attackDelay;
	public FighterSize size;

	

	public Fighter() {
		super();
		this.attackDelay = 0;
	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		ArrayList<String> skipFields = new ArrayList<String>();		
		skipFields.add("ATK_DELAY_SLOW");
		skipFields.add("ATK_DELAY_NORMAL");
		skipFields.add("ATK_DELAY_FAST");
		skipFields.add("MOV_SPEED_SLOW");
		skipFields.add("MOV_SPEED_NORMAL");
		skipFields.add("MOV_SPEED_FAST");
		skipFields.add("platform");
		skipFields.add("action");
		try {
			SerializationHelper.read(this, arg0, arg1, skipFields);
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
		this.action = arg0.readValue("action", CardAction.class, arg1);
	}
	
	@Override
	public void write(Json arg0) {
		ArrayList<String> skipFields = new ArrayList<String>();		
		skipFields.add("ATK_DELAY_SLOW");
		skipFields.add("ATK_DELAY_NORMAL");
		skipFields.add("platform");
		skipFields.add("ATK_DELAY_FAST");
		skipFields.add("MOV_SPEED_SLOW");
		skipFields.add("MOV_SPEED_NORMAL");
		skipFields.add("MOV_SPEED_FAST");
		skipFields.add("action");
		try {
			SerializationHelper.writeToJson(this, arg0, skipFields);
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
		arg0.writeValue("action", this.action, CardAction.class);
	}
	
	

}
