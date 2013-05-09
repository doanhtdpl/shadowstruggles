package br.edu.ifsp.pds.shadowstruggles.screens;

import br.edu.ifsp.pds.shadowstruggles.Controller;
import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A screen which informs the user about an error occurred during any moment. It
 * succinctly explains about what happened and which actions the user should
 * take according to the thrown exception. Experienced users may also view
 * additional, technical details.
 */

public class ErrorScreen extends BaseScreen {
	private boolean viewDetails = false;
	private String error;
	private String errorDescription;
	private Label message;
	private Exception exception;

	public ErrorScreen(ShadowStruggles game, Controller controller,
			Exception exception, String errorDescription) {
		super(game, controller);
		this.setException(exception);
		this.errorDescription = errorDescription;

		TextButton okButton = new TextButton(
				game.getManager().getMenuText().terminate, this.getSkin());
		okButton.setHeight(okButton.getHeight()-200);
		okButton.setX(480 - okButton.getWidth() / 2);
		okButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		TextButton detailsButton = new TextButton(game.getManager()
				.getMenuText().details, this.getSkin());
		detailsButton.setHeight(okButton.getHeight());
		detailsButton.setWidth(okButton.getWidth());
		detailsButton.setX(okButton.getX());
		detailsButton.setY(okButton.getHeight());
		detailsButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				viewDetails = true;
				updateLabel();
			}

		});

		error = errorDescription + "\n\n";
		BitmapFont font = this.getFont();
		font.scale(1.0f);
		message = new Label(error, new Label.LabelStyle(font, Color.WHITE));
		message.setX(480 - message.getWidth() / 2);
		message.setY(640 - message.getHeight());

		stage.addActor(detailsButton);
		stage.addActor(okButton);
		stage.addActor(message);
	}

	protected void updateLabel() {
		if (viewDetails) {
			stage.removeActor(message);

			error = errorDescription + "\n\n" + exception.toString();
			BitmapFont font = this.getFont();
			message = new Label(error, new Label.LabelStyle(font, Color.WHITE));
			message.setX(480 - message.getWidth() / 2);
			message.setY(640 - message.getHeight());

			stage.addActor(message);
		}
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
