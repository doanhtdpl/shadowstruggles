package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;
import com.esotericsoftware.jsonbeans.Json.Serializable;

public abstract class CardAction implements Serializable{
	/***
	 * Execution of the card action.
	 */
	
	public CardAction(){}
	public void doAction(BattlePlatform platform, String img, float delta) { }
	public void doAction(BattlePlatform platform, int lane, int tile){}
	public void doAction(BattlePlatform platform, Card card, float delta){}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		try {
			SerializationHelper.read(this, arg0, arg1, new ArrayList<String>(Arrays.asList(new String[] {"totalCardPoints"})));
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
		try {
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>(Arrays.asList(new String[] {})));
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
	}
}
