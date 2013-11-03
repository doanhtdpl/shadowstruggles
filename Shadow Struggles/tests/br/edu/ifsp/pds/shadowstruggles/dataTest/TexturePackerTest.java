package br.edu.ifsp.pds.shadowstruggles.dataTest;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.data.TexturePacker;

public class TexturePackerTest {

	@Test
	public void test() {
		try {
			TexturePacker tp = new TexturePacker("hi");
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println("Fail");
			fail("não funcionou :(");
		}

	}

}
