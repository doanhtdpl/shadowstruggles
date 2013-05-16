package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles;

import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.Json.Serializable;
import com.esotericsoftware.jsonbeans.JsonValue;

public class EnemyDefeat implements Serializable {
	public Enemy enemy;
	public int timesDefeated;

	public EnemyDefeat() {
		this.enemy = new Enemy();
		this.timesDefeated = 0;
	}
	
	public EnemyDefeat(Enemy enemy, int timesDefeated) {
		this.enemy = enemy;
		this.timesDefeated = timesDefeated;
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
