package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import java.util.Comparator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class MyPage {
	private Array<TextureLocation> textures;
	private int width, height;
	private Node nextFreeNode;

	public MyPage(Array<TextureLocation> textures, int width, int height) {
		this.textures = textures;
		this.width = width;
		this.height = height;
	}

	public MyPage() {
		this.textures = new Array<TextureLocation>();
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Allocates a new free node.
	 */
	public void newFree(float x, float y, float width, float height) {
		nextFreeNode = new Node(x, y, width, height);
	}

	public boolean mergeNodes() {
		Node next = nextFreeNode;

		while (next != null) {
			Node previous = null;
			Node current = nextFreeNode;

			while (current != null) {
				if (next != current) {
					if (next.merge(current)) {
						previous.nextNode = current.nextNode;
						return true;
					}
				}
				previous = current;
				current = current.nextNode;
			}
			next = next.nextNode;
		}

		return false;
	}

	/**
	 * Sets the page's width and height according to the nodes, rounding to the
	 * next power of 2.
	 */
	public void calculateDimensions() {
		// Get the width by adding up all textures on the first row (i.e.,
		// textures with the largest y values).
		textures.sort(new Comparator<TextureLocation>() {
			@Override
			public int compare(TextureLocation o1, TextureLocation o2) {
				return (int) (o2.getTextureRect().y - o1.getTextureRect().y);
			}
		});

		float largestY = textures.get(0).getTextureRect().y;
		width = 0;
		for (TextureLocation texture : textures) {
			float y = texture.getTextureRect().y;
			if (y != largestY)
				break;
			width += texture.getTextureRect().width;
//			System.out.println("Found texture on first row: " + texture.getFile().name()
//					+ " - " + texture.getTextureRect()
//					+ " and my width is now " + width);
		}
		width = MathUtils.nextPowerOfTwo(width);

		// Get the height by adding up all textures on the first column (i.e.,
		// textures with the least x values).
		textures.sort(new Comparator<TextureLocation>() {
			@Override
			public int compare(TextureLocation o1, TextureLocation o2) {
				return (int) (o2.getTextureRect().x - o1.getTextureRect().x);
			}
		});

		float largestX = textures.get(0).getTextureRect().x;
		height = 0;
		for (TextureLocation texture : textures) {
			float x = texture.getTextureRect().x;
			if (x != largestX)
				break;
			height += texture.getTextureRect().height;
//			System.out.println("Found texture on first column " + texture.getFile().name()
//					+ " - " + texture.getTextureRect()
//					+ " and my height is now " + height);
		}
		height = MathUtils.nextPowerOfTwo(height);
	}

	public Array<TextureLocation> getTextures() {
		return textures;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Node getNextFreeNode() {
		return nextFreeNode;
	}

	public void setNextFreeNode(Node nextFreeNode) {
		this.nextFreeNode = nextFreeNode;
	}
}
