package br.edu.ifsp.pds.shadowstruggles.tools;

import br.edu.ifsp.pds.shadowstruggles.tools.view.Window;

public class Main {

	// public static class TestClass implements Serializable {
	//
	// public int attribute;
	// public ArrayList<Integer> numbers;
	//
	// public TestClass() {
	// this(0);
	// }
	//
	// public TestClass(int attribute) {
	// this.attribute = attribute;
	// numbers = new ArrayList<Integer>();
	// numbers.add(10);
	// numbers.add(200);
	// numbers.add(3);
	// }
	//
	// @Override
	// public void read(Json arg0, JsonValue arg1) {
	// this.attribute = arg0.readValue("attribute", Integer.class, arg1);
	// this.numbers = new
	// ArrayList<Integer>(Arrays.asList(arg0.readValue("numbers",
	// Integer[].class, arg1)));
	// }
	//
	// @Override
	// public void write(Json arg0) {
	// arg0.writeValue("attribute", this.attribute);
	// arg0.writeValue("numbers", numbers.toArray(), Integer[].class);
	// }
	// }

	public static void main(String[] args) {
		new Window();
		
		// Some tests with JSON for reference, they should be removed soon.
		// TestClass[] testClasses = new TestClass[] { new TestClass(5),
		// new TestClass(990), new TestClass(58) };
		//
		// Json json = new Json();
		// json.setElementType(TestClass.class, "numbers", Integer.class);
		// json.addClassTag("integer", Integer.class);
		// System.out.println(json.prettyPrint(testClasses));
	}

}
