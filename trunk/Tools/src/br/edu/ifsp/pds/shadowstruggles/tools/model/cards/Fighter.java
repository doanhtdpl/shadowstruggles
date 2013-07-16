package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Action;

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

	private int health;
	private int maxHealth;
	private int damage;
	private float speed;
	private int range;
	private boolean hasEffect;
	private float attackDelay;
	private String size;
	private float delay;
	private float moveTimer;
	private boolean attacking;

	public Fighter() {
		super();
		this.attackDelay = 0;
	}

	public Fighter(int id, String name, String description, int buyCost,
			int sellCost, boolean sellable, String icon,
			boolean availableInShop, int energyCost, CardAction action,
			BattlePlatform platform, String illustration,
			ArrayList<String> preRequisites) {
		super(id, name, description, buyCost, sellCost, sellable, icon,
				availableInShop, false, energyCost, action, platform,
				illustration, preRequisites);

		this.size = size;
		this.attackDelay = 0;
		this.moveTimer = 0;
	}

}
