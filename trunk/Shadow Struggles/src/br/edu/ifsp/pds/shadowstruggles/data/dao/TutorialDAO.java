package br.edu.ifsp.pds.shadowstruggles.data.dao;

import br.edu.ifsp.pds.shadowstruggles.data.DataManager;
import br.edu.ifsp.pds.shadowstruggles.model.TutorialDialog;

import com.badlogic.gdx.utils.Array;

public class TutorialDAO {
	public static TutorialDialog getDialog(int key) {
		TutorialDialog dialog = null;

		@SuppressWarnings("unchecked")
		Array<TutorialDialog> dialogs = DataManager.getInstance().getObjectSet(
				TutorialDialog.class);
		for (TutorialDialog d : dialogs) {
			if (d.getId() == key) {
				/*
				 * This makes a deep copy of the fighter so that similar
				 * fighters don't share the same memory blocks.
				 */
				dialog = new TutorialDialog(d.getId(), d.getText(),
						d.getIndicatorX(), d.getIndicatorY(),
						d.isIndicatorVisible(), d.getEventType(),
						d.getEventTarget());
			}
		}

		return dialog;
	}

	@SuppressWarnings("unchecked")
	public static Array<TutorialDialog> getDialogs() {
		return DataManager.getInstance().getObjectSet(TutorialDialog.class);
	}

	// public static void writeDialog(TutorialDialog dialog, DataManager
	// manager)
	// throws SerializationException {
	// manager.writeTutorialDialog(dialog);
	// }
}
