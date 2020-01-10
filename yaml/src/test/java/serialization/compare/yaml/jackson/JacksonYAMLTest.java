package serialization.compare.yaml.jackson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import serialization.compare.jackson.Solitaire;

public class JacksonYAMLTest {

	@Test
	public void saneTest() {
		Solitaire gameState = Solitaire.newSolitaireGame();

		JacksonYAMLAdapter adapter = new JacksonYAMLAdapter();

		byte[] marshalled = adapter.toByteArray(gameState);

		System.out.println(new String(marshalled));
		
		Solitaire unmarshalled = adapter.fromByteArray(marshalled);

		assertEquals(gameState, unmarshalled);
	}
}
