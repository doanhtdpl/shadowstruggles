package br.edu.ifsp.pds.shadowstruggles.model.rpg;

import com.badlogic.gdx.math.Rectangle;

import br.edu.ifsp.pds.shadowstruggles.model.rpg.pathfinder.Mover;

public class CharacterMover implements Mover {

	public static enum Type {
		NORMAL_CHARACTER
	};

	private Type type;
	private Rectangle rectangle;

	public CharacterMover() {
		this(Type.NORMAL_CHARACTER);
	}

	public CharacterMover(Type type) {
		this.type = type;
		this.rectangle = new Rectangle();
	}

	public Type getType() {
		return type;
	}

	public Rectangle getRectangle() {
		return this.rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public void setRectanglePos(float x, float y) {
		this.rectangle.setX(x);
		this.rectangle.setY(y);
	}

	public void setRectangleDimension(float width, float height) {
		this.rectangle.setWidth(width);
		this.rectangle.setHeight(height);
	}
}
