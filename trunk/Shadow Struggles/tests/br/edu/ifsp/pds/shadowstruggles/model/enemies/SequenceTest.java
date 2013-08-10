package br.edu.ifsp.pds.shadowstruggles.model.enemies;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions.Condition;
import br.edu.ifsp.pds.shadowstruggles.model.enemies.conditions.True;

import com.badlogic.gdx.utils.Array;

public class SequenceTest {

	@Test
	public void testEvaluateConditions() {
		Sequence trueSequence = new Sequence();
		Sequence falseSequence = new Sequence();

		Array<Condition> conditions = new Array<Condition>();
		conditions.add(new True());
		trueSequence.setConditions(conditions);

		Condition falseValue = new True();
		falseValue.setNot(true);
		conditions = new Array<Condition>();
		conditions.add(new True());
		conditions.add(falseValue);
		falseSequence.setConditions(conditions);

		assertTrue(trueSequence.evaluateConditions()
				&& !falseSequence.evaluateConditions());
	}

}
