package br.edu.ifsp.pds.shadowstruggles.tools.model.cards;

import java.util.ArrayList;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
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

	public int health;
	public int maxHealth;
	public int damage;
	public float speed;
	public int range;
	public boolean hasEffect;
	public float attackDelay;
	public String size;
	public float delay;
	public float moveTimer;
	public boolean attacking;

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

		this.attackDelay = 0;
		this.moveTimer = 0;
	}

}
