package br.edu.ifsp.pds.shadowstruggles.dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.ShadowStruggles;
import br.edu.ifsp.pds.shadowstruggles.data.TexturePacker;

public class TexturePackerTest {

	@Test
	public void test(ShadowStruggles game) {
			TexturePacker tp = new TexturePacker("hi", game);
			System.out.println("Success");

	}

}
