package br.edu.ifsp.pds.shadowstruggles.tools.model.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import br.edu.ifsp.pds.shadowstruggles.tools.data.SerializationHelper;
import br.edu.ifsp.pds.shadowstruggles.tools.model.modifiers.Modifier;

public class ModifierAction extends EventAction {
	public Modifier modifier;
	
	@Override
	public void read(Json arg0, JsonValue arg1) {		
			try {
				SerializationHelper.read(this, arg0, arg1, new ArrayList<String>(Arrays.asList(new String[]{"modifier"})));
			} catch (IllegalArgumentException e) {
				Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
				e.printStackTrace();
			}
			this.modifier=arg0.readValue("modifier", Modifier.class, arg1);
	}
	
	
	@Override
	public void write(Json arg0) {
		try {
			SerializationHelper.writeToJson(this, arg0, new ArrayList<String>(Arrays.asList(new String[]{"modifier"})));
		} catch (IllegalArgumentException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).severe(e.toString());
			e.printStackTrace();
		}
		arg0.writeValue("modifier", this.modifier,Modifier.class);
	}
}



