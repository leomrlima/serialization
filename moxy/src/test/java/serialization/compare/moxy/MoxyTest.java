package serialization.compare.moxy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoxyTest {

	@Test
	public void saneTest() {
		Solitaire gameState = Solitaire.newSolitaireGame();

		MoxyAdapter adapter = new MoxyAdapter();

		byte[] marshalled = adapter.toByteArray(gameState);
		
		Solitaire unmarshalled = adapter.fromByteArray(marshalled);

		assertEquals(gameState, unmarshalled);
	}
}
