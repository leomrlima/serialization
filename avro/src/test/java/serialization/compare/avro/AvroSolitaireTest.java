package serialization.compare.avro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AvroSolitaireTest {

	@Test
	public void serializationTest() {
		Solitaire solitaire = SolitaireGame.newSolitaireGame();

		try {
			Solitaire unmarshalled = Solitaire.fromByteBuffer(solitaire.toByteBuffer());

			assertEquals(7, unmarshalled.getTableauPiles().size());
			assertEquals(0, unmarshalled.getWastePile().getCards().size());
			assertEquals(4, unmarshalled.getFoundationPiles().size());

			for (int tableau = 0; tableau < 7; tableau++) {
				assertEquals(tableau + 1, unmarshalled.getTableauPiles().get(tableau).getCards().size());
				assertEquals(State.UP, unmarshalled.getTableauPiles().get(tableau).getCards().get(tableau).getState());
				for (int down = 0; down < tableau; down++) {
					assertEquals(State.DOWN,
							unmarshalled.getTableauPiles().get(tableau).getCards().get(down).getState());
				}
			}

			assertEquals(24, unmarshalled.getHandPile().getCards().size());

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Test
	public void saneTest() {
		Solitaire solitaire = SolitaireGame.newSolitaireGame();

		assertEquals(7, solitaire.getTableauPiles().size());
		assertEquals(0, solitaire.getWastePile().getCards().size());
		assertEquals(4, solitaire.getFoundationPiles().size());

		for (int tableau = 0; tableau < 7; tableau++) {
			assertEquals(tableau + 1, solitaire.getTableauPiles().get(tableau).getCards().size());
			assertEquals(State.UP, solitaire.getTableauPiles().get(tableau).getCards().get(tableau).getState());
			for (int down = 0; down < tableau; down++) {
				assertEquals(State.DOWN, solitaire.getTableauPiles().get(tableau).getCards().get(down).getState());
			}
		}

		assertEquals(24, solitaire.getHandPile().getCards().size());
	}
}
