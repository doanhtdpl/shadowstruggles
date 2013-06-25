package br.edu.ifsp.pds.shadowstruggles.model.profiles;

import br.edu.ifsp.pds.shadowstruggles.model.enemies.Enemy;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class EnemyDefeat implements Serializable {
	public Enemy enemy;
	public int timesDefeated;

	public EnemyDefeat() {
		this.enemy = null;
		this.timesDefeated = 0;
	}

	public EnemyDefeat(Enemy enemy, int timesDefeated) {
		this.enemy = enemy;
		this.timesDefeated = timesDefeated;
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.enemy = json.readValue("enemy", Enemy.class, jsonData);
		this.timesDefeated = json.readValue("timesDefeated", Integer.class,
				jsonData);
	}

	@Override
	public void write(Json json) {
		json.writeValue("enemy", this.enemy);
		json.writeValue("timesDefeated", this.timesDefeated);
	}
}
