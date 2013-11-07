package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;



/***
 * A representation of an effect card on the field.
 */

public class Effect extends Card {
	private float duration;
	private boolean isImmediateEffect;
	private boolean isActivated = false;
	private boolean onFighter;

	public Effect() {
	}

	public boolean isImmediateEffect() {
		return isImmediateEffect;
	}

	public void setImmediateEffect(boolean isImmediateEffect) {
		this.isImmediateEffect = isImmediateEffect;
	}

	public boolean isOnFighter() {
		return onFighter;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		ArrayList<String> skipFields = new ArrayList<String>();
		skipFields.add("isActivated");
		
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
		skipFields.add("isActivated");
		
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
