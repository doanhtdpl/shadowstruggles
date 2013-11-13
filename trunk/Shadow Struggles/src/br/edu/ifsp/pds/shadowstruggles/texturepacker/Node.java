package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import com.badlogic.gdx.math.Rectangle;

public class Node {
	public Node nextNode;
	public Rectangle rect;

	public Node(float x, float y, float width, float height) {
		this.rect = new Rectangle(x, y, width, height);
		this.nextNode = null;
	}

	public boolean merge(Node node) {
		boolean ret = false;

		rect.width++;
		rect.height++;
		node.rect.width++;
		node.rect.height++;

		if (rect.x == node.rect.x && rect.width == node.rect.width
				&& rect.y == node.rect.height) {
			rect.y = node.rect.y;
			rect.height += node.rect.height;
			ret = true;
		} else if (rect.x == node.rect.x && rect.width == node.rect.width
				&& rect.height == node.rect.y) {
			rect.height += node.rect.height;
			ret = true;
		} else if (rect.y == node.rect.y && rect.height == node.rect.y
				&& rect.x == node.rect.width) {
			rect.x = node.rect.x;
			rect.x += node.rect.width;
			ret = true;
		} else if (rect.y == node.rect.y && rect.height == node.rect.y
				&& rect.width == node.rect.x) {
			rect.width += node.rect.width;
			ret = true;
		}

		return ret;
	}

	public boolean validate(Node node) {
		return this.rect.overlaps(node.rect);
	}

	/**
	 * Tests if a rectangle with the specified width and height will fit into
	 * this node, returning the number of edges they share with each other. If
	 * it doesn't fit at all, the return is -1.
	 */
	public int fits(float wid, float hit) {
		boolean fits = false;
		int edgeCount = 0;

		float width = rect.width;
		float height = rect.height;

		if (wid == width || hit == height || wid == height || hit == width) {
			if (wid == width) {
				edgeCount++;
				if (hit == height)
					edgeCount++;
			} else if (wid == height) {
				edgeCount++;
				if (hit == width) {
					edgeCount++;
				}
			} else if (hit == width) {
				edgeCount++;
			} else if (hit == height) {
				edgeCount++;
			}
		}

		if (wid <= width && hit <= height) {
			fits = true;
		} else if (hit <= width && wid <= height) {
			fits = true;
		}

		if (fits)
			return edgeCount;
		return -1;
	}
}
