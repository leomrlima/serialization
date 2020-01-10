package serialization.compare.xml.jackson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import serialization.compare.jackson.Solitaire;

public class JacksonXMLTest {

	@Test
	public void saneTest() {
		Solitaire gameState = Solitaire.newSolitaireGame();

		JacksonXMLAdapter adapter = new JacksonXMLAdapter();

		byte[] marshalled = adapter.toByteArray(gameState);

		System.out.println(new String(marshalled));
		
		Solitaire unmarshalled = adapter.fromByteArray(marshalled);

		assertEquals(gameState, unmarshalled);
	}
}
