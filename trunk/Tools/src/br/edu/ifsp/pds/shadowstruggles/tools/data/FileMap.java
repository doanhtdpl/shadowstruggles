package br.edu.ifsp.pds.shadowstruggles.tools.data;

import java.util.HashMap;

import br.edu.ifsp.pds.shadowstruggles.tools.model.BattleMap;
import br.edu.ifsp.pds.shadowstruggles.tools.model.BattlePlatform;
import br.edu.ifsp.pds.shadowstruggles.tools.model.TutorialDialog;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Card;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Deck;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Effect;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Fighter;
import br.edu.ifsp.pds.shadowstruggles.tools.model.cards.Trap;
import br.edu.ifsp.pds.shadowstruggles.tools.model.enemies.Enemy;
import br.edu.ifsp.pds.shadowstruggles.tools.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Item;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Pack;
import br.edu.ifsp.pds.shadowstruggles.tools.model.items.Shop;
import br.edu.ifsp.pds.shadowstruggles.tools.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.tools.model.quests.Quest;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Ending;
import br.edu.ifsp.pds.shadowstruggles.tools.model.scenes.Scene;

/**
 * Provides an easy access to the directory structure by mapping classes and
 * resources to their respective files and directories, without considering the
 * languages.
 */
public class FileMap {

	public static HashMap<Class<?>, String> classToFile;
	public static HashMap<String, String> resourcesToDirectory;
	/**
	 * Global objects are not localized, being language-independent (e.g.,
	 * {@link Settings} and {@link Languages}).
	 */
	public static HashMap<Class<?>, String> globalClassToFile;

	public static void initMap() {
		classToFile = new HashMap<Class<?>, String>();
		resourcesToDirectory = new HashMap<String, String>();
		globalClassToFile = new HashMap<Class<?>, String>();

		resourcesToDirectory.put("sound_effects", "data/audio/sound_effects/");
		resourcesToDirectory.put("soundtrack", "data/audio/soundtrack/");

		classToFile.put(BattleMap.class, "data/files/battle_maps.json");
		classToFile.put(Card.class, "data/files/cards.json");
		classToFile.put(Deck.class, "data/files/decks.json");
		classToFile.put(Fighter.class, "data/files/fighters.json");
		classToFile.put(Trap.class, "data/files/traps.json");
		classToFile.put(Effect.class, "data/files/effects.json");
		classToFile.put(Ending.class, "data/files/endings.json");
		classToFile.put(Enemy.class, "data/files/enemies.json");
		classToFile.put(Event.class, "data/files/events.json");
		classToFile.put(Item.class, "data/files/items.json");
		classToFile.put(MenuText.class, "data/files/menu_text.json");
		classToFile.put(Pack.class, "data/files/packs.json");
		classToFile.put(Quest.class, "data/files/quests.json");
		classToFile.put(Scene.class, "data/files/scenes.json");
		classToFile.put(Shop.class, "data/files/shops.json");
		classToFile.put(TutorialDialog.class, "data/files/tutorial.json");

		resourcesToDirectory.put("battle_maps", "data/images/battle_maps/");
		resourcesToDirectory.put("sprites", "data/images/sprites/");
		resourcesToDirectory.put("card_attacking",
				"data/images/card_attacking/");
		resourcesToDirectory.put("card_effects", "data/images/card_effects/");
		resourcesToDirectory.put("card_images", "data/images/card_images/");
		resourcesToDirectory.put("card_walking", "data/images/card_walking/");
		resourcesToDirectory.put("cards", "data/images/cards/");
		resourcesToDirectory.put("item_icons", "data/images/item_icons/");
		resourcesToDirectory.put("game_ui_images",
				"data/images/game_ui_images/");
		resourcesToDirectory.put("novel_images", "data/images/novel_images/");
		resourcesToDirectory.put("rpg_maps", "data/rpg_maps/");
		resourcesToDirectory.put("skin", "data/images/skin/");

		globalClassToFile.put(Languages.class, "data/languages.json");
		globalClassToFile.put(Settings.class, "data/game_settings.json");
		globalClassToFile.put(Profile.class, "data/profiles.json");
		globalClassToFile.put(BattlePlatform.class, "data/battles.json");
	}
}
