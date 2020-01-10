package serialization.compare.json.gson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import serialization.compare.jackson.Solitaire;
import serialization.compare.json.gson.GsonAdapter;

public class GsonTest {

	@Test
	public void saneTest() {
		Solitaire gameState = Solitaire.newSolitaireGame();

		GsonAdapter gsonAdapter = new GsonAdapter();

		byte[] marshalled = gsonAdapter.toByteArray(gameState);

		Solitaire unmarshalled = gsonAdapter.fromByteArray(marshalled);

		assertEquals(gameState, unmarshalled);
	}
}
