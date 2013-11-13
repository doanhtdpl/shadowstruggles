package br.edu.ifsp.pds.shadowstruggles.texturepacker;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PackingAlgorithm {

	private static class Rect implements Comparable<Rect> {
		public TextureLocation texture;
		public float longestEdge;
		public float area;
		public boolean placed = false;

		public Rect(TextureLocation texture) {
			this.texture = texture;

			Rectangle rect = texture.getTextureRect();
			this.longestEdge = Math.min(rect.width, rect.height);
			this.area = rect.width * rect.height;
		}

		@Override
		public int compareTo(Rect o) {
			return (int) (o.longestEdge - this.longestEdge);
		}

		public void place(float x, float y) {
//			System.out.println("Placing " + texture.getFile().name() + " at "
//					+ x + " - " + y);
			texture.getTextureRect().x = x;
			texture.getTextureRect().y = y;
			placed = true;
		}
	}

	public static Array<MyPage> calculateAtlas(Array<TextureLocation> textures,
			int maxWidth, int maxHeight) {
		Array<MyPage> pages = new Array<MyPage>();
		int maxArea = maxWidth * maxHeight;

		Array<Rect> rects = fillRects(textures);
		pages = createPages(rects, maxArea, maxWidth, maxHeight);

		return pages;
	}

	/**
	 * Fills rectangles based on the specified textures and sorts them by
	 * descending order of longest edge.
	 */
	private static Array<Rect> fillRects(Array<TextureLocation> textures) {
		Array<Rect> rects = new Array<Rect>();
		for (TextureLocation texture : textures) {
			rects.add(new Rect(texture));
		}

		rects.sort();
		return rects;
	}

	private static Array<MyPage> createPages(Array<Rect> rects, int maxArea,
			int maxWidth, int maxHeight) {
		Array<MyPage> pages = new Array<MyPage>();

		float totalArea = 0;
		MyPage currentPage = new MyPage();
		currentPage.newFree(0, 0, maxWidth, maxHeight);
		pages.add(currentPage);

		for (Rect rect : rects) {
			if (!rect.placed) {
				// Make sure to pick the texture with the greatest area.
				int index = rects.indexOf(rect, true);
				int rectsSize = rects.size;
				for (int i = index; i < rectsSize; i++) {
					Rect rect2 = rects.get(i);
					if (rect2.longestEdge < rect.longestEdge)
						break;
					if (rect2.area > rect.area)
						rect = rect2;
				}

				totalArea += rect.area;
				boolean lastRect = rects.indexOf(rect, true) == rects.size - 1;

				if (totalArea > maxArea) {
					currentPage.calculateDimensions();

					if (!lastRect) {
						// Create a new page and continue.
						currentPage = new MyPage();
						currentPage.newFree(0, 0, maxWidth, maxHeight);
						pages.add(currentPage);
						totalArea = rect.area;
					}
				}

				rect = allocateNextSpot(rect, currentPage);
				currentPage.getTextures().add(rect.texture);
				if (lastRect)
					currentPage.calculateDimensions();
			}
		}

		return pages;
	}

	private static Rect allocateNextSpot(Rect rect, MyPage currentPage) {
		float leastY = 0, leastX = 0;
		int edgeCount = 0;

		Node next = currentPage.getNextFreeNode();
		Node bestFit = null;
		Node previousNode = null;
		Node previousBestFit = null;

		while (next != null) {
			Rectangle r = rect.texture.getTextureRect();
			int ec = next.fits(r.width, r.height);

			if (ec >= 0) {
//				System.out.println("Texture " + rect.texture.getFile().name()
//						+ " - " + r + " fits in node " + next.rect + " with "
//						+ ec + " edges");
				edgeCount = ec;

				if (ec == 2) {
					previousBestFit = previousNode;
					bestFit = next;
					break;
				}

				if (next.rect.y < leastY || leastY == 0) {
//					System.out.println("Potential bestFit found because next.rect.y < leastY");
					leastX = next.rect.x;
					leastY = next.rect.y;
					previousBestFit = previousNode;
					bestFit = next;
				} else if (next.rect.y == leastY && next.rect.x < leastX) {
//					System.out.println("Potential bestFit found because next.rect.x < leastX");
					leastX = next.rect.x;
					previousBestFit = previousNode;
					bestFit = next;
				}
			}

			previousNode = next;
			next = next.nextNode;
		}

		if (bestFit != null) {
			Rectangle textureRect = rect.texture.getTextureRect();

			do {
				switch (edgeCount) {
				case 0:
					rect.place(bestFit.rect.x, bestFit.rect.y);
					currentPage.newFree(bestFit.rect.x, bestFit.rect.y
							+ textureRect.height, bestFit.rect.width,
							bestFit.rect.height - textureRect.height);

					bestFit.rect.x += textureRect.width;
					bestFit.rect.width -= textureRect.width;
					bestFit.rect.height = textureRect.height;
					break;
				case 1:
					if (textureRect.width == bestFit.rect.width) {
						rect.place(bestFit.rect.x, bestFit.rect.y);
						bestFit.rect.y += textureRect.height;
						bestFit.rect.height -= textureRect.height;
					} else if (textureRect.height == bestFit.rect.height) {
						rect.place(bestFit.rect.x, bestFit.rect.y);
						bestFit.rect.x += textureRect.width;
						bestFit.rect.width -= textureRect.width;
					}
					break;
				case 2:
					rect.place(bestFit.rect.x, bestFit.rect.y);
					if (previousBestFit != null) {
						previousBestFit.nextNode = bestFit.nextNode;
					} else {
						currentPage.setNextFreeNode(bestFit.nextNode);
					}
				}
			} while (currentPage.mergeNodes());
		}

		return rect;
	}
}
