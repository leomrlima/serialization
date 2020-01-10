package serialization.compare.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JavaAdapterTest {

	@Test
	public void saneTest() {
		Solitaire gameState = Solitaire.newSolitaireGame();

		JavaAdapter adapter = new JavaAdapter();

		byte[] marshalled = adapter.toByteArray(gameState);

		Solitaire unmarshalled = adapter.fromByteArray(marshalled);

		assertEquals(gameState, unmarshalled);
	}

}
