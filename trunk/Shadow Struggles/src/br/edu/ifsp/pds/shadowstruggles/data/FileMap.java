package br.edu.ifsp.pds.shadowstruggles.data;

import com.badlogic.gdx.utils.ObjectMap;

import br.edu.ifsp.pds.shadowstruggles.model.Deck;
import br.edu.ifsp.pds.shadowstruggles.model.Effect;
import br.edu.ifsp.pds.shadowstruggles.model.Fighter;
import br.edu.ifsp.pds.shadowstruggles.model.Trap;
import br.edu.ifsp.pds.shadowstruggles.model.TutorialDialog;
import br.edu.ifsp.pds.shadowstruggles.model.events.Event;
import br.edu.ifsp.pds.shadowstruggles.model.profiles.Profile;
import br.edu.ifsp.pds.shadowstruggles.model.Scene;

/**
 * Provides an easy access to the directory structure by mapping classes and
 * resources to their respective files and directories, without considering the
 * languages.
 */
public class FileMap {

	public static ObjectMap<Class<?>, String> classToFile;
	public static ObjectMap<String, String> resourcesToDirectory;
	/**
	 * Global objects are not localized, being language-independent (e.g.,
	 * {@link Settings} and {@link Languages}).
	 */
	public static ObjectMap<Class<?>, String> globalClassToFile;

	public static void initMap() {
		classToFile = new ObjectMap<Class<?>, String>();
		resourcesToDirectory = new ObjectMap<String, String>();
		globalClassToFile = new ObjectMap<Class<?>, String>();

		resourcesToDirectory.put("sound_effects", "data/audio/sound_effects/");
		resourcesToDirectory.put("soundtrack", "data/audio/soundtrack/");

		// classToFile.put(BattlePlatform.class, "data/files/battles.json");
		classToFile.put(Fighter.class, "data/files/fighters.json");
		classToFile.put(Effect.class, "data/files/effects.json");
		classToFile.put(Trap.class, "data/files/traps.json");
		classToFile.put(Deck.class, "data/files/decks.json");
		// classToFile.put(Ending.class, "data/files/endings.json");
		// classToFile.put(Enemy.class, "data/files/enemies.json");
		classToFile.put(Event.class, "data/files/events.json");
		// classToFile.put(Item.class, "data/files/items.json");
		classToFile.put(MenuText.class, "data/files/menu_text.json");
		// classToFile.put(Pack.class, "data/files/packs.json");
		// classToFile.put(Quest.class, "data/files/quests.json");
		classToFile.put(Scene.class, "data/files/scenes.json");
		// classToFile.put(Shop.class, "data/files/shops.json");
		classToFile.put(TutorialDialog.class, "data/files/tutorial.json");

		resourcesToDirectory.put("battle_maps", "data/images/battle_maps/");
		resourcesToDirectory.put("sprites", "data/images/sprites/");
		resourcesToDirectory.put("card_attacking",
				"data/images/card_attacking/");
		resourcesToDirectory.put("card_effects", "data/images/card_effects/");
		resourcesToDirectory.put("card_images", "data/images/card_images/");
		resourcesToDirectory.put("card_walking", "data/images/card_walking/");
		resourcesToDirectory.put("cards", "data/images/cards/");
		resourcesToDirectory.put("game_ui_images",
				"data/images/game_ui_images/");
		resourcesToDirectory.put("novel_images", "data/images/novel_images/");
		resourcesToDirectory.put("rpg_maps", "data/images/rpg_maps/");
		resourcesToDirectory.put("skin", "data/images/skin/");

		globalClassToFile.put(Languages.class, "data/languages.json");
		globalClassToFile.put(Settings.class, "data/game_settings.json");
		globalClassToFile.put(Profile.class, "data/profiles.json");
	}
}
