package br.edu.ifsp.pds.shadowstruggles.tools.model.scenes;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

public class BattleControl extends SceneItem {
	public BattlePlatform nextBattle;
	
	public BattleControl() {
		// TODO Auto-generated constructor stub
	}
	
	public BattleControl(BattlePlatform nextBattle) {
		this.nextBattle = nextBattle;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void read(Json arg0, JsonValue arg1) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void write(Json arg0) {
		// TODO Auto-generated method stub
	}

}
