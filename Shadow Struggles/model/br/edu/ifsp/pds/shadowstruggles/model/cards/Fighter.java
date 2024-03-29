package br.edu.ifsp.pds.shadowstruggles.model.cards;

import br.edu.ifsp.pds.shadowstruggles.scripts.CardAction;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/***
 * A representation of a fighter card on the field.
 */

public class Fighter extends Card {
	public static enum FighterSize {
		SMALL, MEDIUM, LARGE
	};

	public static final float ATK_DELAY_SLOW = 3F;
	public static final float ATK_DELAY_NORMAL = 2F;
	public static final float ATK_DELAY_FAST = 1.5F;
	public static final float MOV_SPEED_SLOW = 0.10F;
	public static final float MOV_SPEED_NORMAL = 0.20F;
	public static final float MOV_SPEED_FAST = 0.3F;

	private int health;
	private int maxHealth;
	private int damage;
	private float speed;
	private int range;
	private boolean hasEffect;
	private float attackDelay;
	private FighterSize size;

	private float moveTimer;
	private boolean attacking;

	public Fighter() {
		super();
		this.attackDelay = 0;
		this.direction = 1;
	}

	public Fighter(String name, String nameVisualization, int energyCost,
			String description, int buyCost, CardAction action, int health,
			int damage, float speed, int range, boolean hasEffect,
			FighterSize size, float attackDelay, Array<String> preRequisites) {
		super(name, nameVisualization, energyCost, description, buyCost, action);
		this.health = health;
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.preRequisites = preRequisites;
		this.hasEffect = hasEffect;
		this.size = size;
		this.attackDelay = attackDelay;
		this.direction = 1;
		this.maxHealth = health;
		this.moveTimer = 0;
	}

	public void move(float delta) {
		this.moveTimer += delta * direction * speed * 60;
		if (direction == 1) {
			if ((int) ((moveTimer - 96) / 48) > tile) {
				tile++;
			}
		} else {
			if ((int) ((moveTimer - 48) / 48) < tile) {
				tile--;
			}

		}
	}

	@Override
	public void write(Json json) {
		super.write(json);
		
		json.writeValue("health", this.health);
		json.writeValue("damage", this.damage);
		json.writeValue("speed", this.speed);
		json.writeValue("range", this.range);
		json.writeValue("hasEffect", this.hasEffect);
		json.writeValue("size", this.size);
		json.writeValue("attackDelay", attackDelay);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);

		this.health = json.readValue("health", Integer.class, jsonData);
		this.damage = json.readValue("damage", Integer.class, jsonData);
		this.speed = json.readValue("speed", Float.class, jsonData);
		this.range = json.readValue("range", Integer.class, jsonData);
		this.hasEffect = json.readValue("hasEffect", Boolean.class, jsonData);
		this.size = json.readValue("size", FighterSize.class, jsonData);
		this.attackDelay = json.readValue("attackDelay", Float.class, jsonData);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public float getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public boolean isHasEffect() {
		return hasEffect;
	}

	public void setHasEffect(boolean hasEffect) {
		this.hasEffect = hasEffect;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setSpeed(String speed) {
		if (speed.equals("FAST"))
			this.speed = MOV_SPEED_FAST;
		if (speed.equals("NORMAL"))
			this.speed = MOV_SPEED_NORMAL;
		if (speed.equals("SLOW"))
			this.speed = MOV_SPEED_SLOW;
	}

	public String getSpeedValue() {
		if (speed == MOV_SPEED_FAST)
			return "FAST";
		if (speed == MOV_SPEED_NORMAL)
			return "NORMAL";
		if (speed == MOV_SPEED_SLOW)
			return "SLOW";
		return "SLOW";
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setAttackDelay(float attackDelay) {
		this.attackDelay = attackDelay;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public FighterSize getSize() {
		return this.size;
	}

	public void setSize(FighterSize medium) {
		this.size = medium;
	}

}
