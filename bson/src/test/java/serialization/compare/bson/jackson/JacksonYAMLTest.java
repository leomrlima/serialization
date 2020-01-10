package serialization.compare.bson.jackson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JacksonYAMLTest {

	@Test
	public void saneTest() {
		Solitaire gameState = Solitaire.newSolitaireGame();

		BsonAdapter adapter = new BsonAdapter();

		byte[] marshalled = adapter.toByteArray(gameState);

		System.out.println(new String(marshalled));
		
		Solitaire unmarshalled = adapter.fromByteArray(marshalled);

		assertEquals(gameState, unmarshalled);
	}
}
