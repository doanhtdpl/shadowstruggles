package br.edu.ifsp.pds.shadowstruggles.data;

import br.edu.ifsp.pds.shadowstruggles.model.TutorialDialog;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SerializationException;

public class TutorialDAO {
	public static TutorialDialog getDialog(int key, DataManager manager) {
		TutorialDialog dialog = null;

		Array<TutorialDialog> dialogs = manager.getTutorialDialogsList();
		for (TutorialDialog d : dialogs) {
			if (d.getId() == key) {
				/*
				 * This makes a deep copy of the fighter so that similar
				 * fighters don't share the same memory blocks.
				 */
				dialog = new TutorialDialog(d.getId(), d.getText(),
						d.getIndicatorX(), d.getIndicatorY(),
						d.isIndicatorVisible(), d.getEventType(), d.getEventTarget());
			}
		}

		return dialog;
	}

	public static Array<TutorialDialog> getDialogs(DataManager manager) {
		return manager.getTutorialDialogsList();
	}
	
	public static void writeDialog(TutorialDialog dialog, DataManager manager)
			throws SerializationException {
		manager.writeTutorialDialog(dialog);
	}
}
