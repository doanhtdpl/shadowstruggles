package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.cards.Trap;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

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

	private Attribute type;
	private Object value;
	private DynamicValue dynamicType;

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

	/**
	 * If the action contains a dynamic value, it must be updated during the
	 * battle.
	 */
	public void update() {
		if (dynamicType != null) {
			BattleMap map = ShadowStruggles.getInstance().getController()
					.getPlatform().getMap();

			if (dynamicType == DynamicValue.RANDOM_LANE)
				value = MathUtils.random(3);
			if (dynamicType == DynamicValue.RANDOM_TILE)
				value = 35 - MathUtils.random(5);
			if (dynamicType == DynamicValue.ATTACKED_LANE)
				value = map.enemyBaseAttacked();
			if (dynamicType == DynamicValue.NEXT_AVAILABLE_LANE)
				value = map.nextAvailableLane(BattleMap.COMPUTER_PLAYER);
			if (dynamicType == DynamicValue.LANE_WITH_MORE_ENEMIES)
				value = map.laneWithMoreEnemies(BattleMap.COMPUTER_PLAYER);
			if (dynamicType == DynamicValue.LANE_WITH_LESS_ALLIES)
				value = map.laneWithLessAllies(BattleMap.COMPUTER_PLAYER);
			if (dynamicType == DynamicValue.LANES_WITH_MORE_ENEMIES_INVADING)
				value = map
						.laneWithMoreEnemiesInvading(BattleMap.COMPUTER_PLAYER);
		}
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		this.type = json.readValue("type", Attribute.class, jsonData);
		this.value = json.readValue("value", Object.class, jsonData);
		this.dynamicType = json.readValue("dynamicType", DynamicValue.class,
				jsonData);

		if (value instanceof Fighter) {
			Fighter f = (Fighter) value;
			f.setAction(f.getAction().copy());
			this.value = new Fighter(f.getName(), f.getNameVisualization(),
					f.getEnergyCost(), f.getDescription(), f.getBuyCost(),
					f.getAction(), f.getHealth(), f.getDamage(), f.getSpeed(),
					f.getRange(), f.isHasEffect(), f.getSize(),
					f.getAttackDelay(), f.getPreRequisites());
		} else if (value instanceof Effect) {
			Effect effect = (Effect) value;
			effect.setAction(effect.getAction().copy());
			this.value = new Effect(effect.getName(),
					effect.getNameVisualization(), effect.getEnergyCost(),
					effect.getDescription(), effect.getBuyCost(),
					effect.getAction(), effect.getDuration(),
					effect.isImmediateEffect(), effect.isOnFighter());
		} else if (value instanceof Trap) {
			Trap card = (Trap) value;
			card.setAction(card.getAction().copy());
			this.value = new Trap(card.getName(), card.getNameVisualization(),
					card.getEnergyCost(), card.getDescription(),
					card.getBuyCost(), card.getAction(), card.getDuration(),
					card.isHasImmediateEffect());
		}
	}

	@Override
	public void write(Json json) {
		json.writeValue("type", this.type);
		json.writeValue("value", this.value);
		json.writeValue("dynamicType", this.dynamicType);

		update();
	}

	public Attribute getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}
}
