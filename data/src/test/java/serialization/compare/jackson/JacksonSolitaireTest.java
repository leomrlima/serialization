package serialization.compare.jackson;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JacksonSolitaireTest {

	@Test
	public void saneTest() {
		Solitaire solitaire = Solitaire.newSolitaireGame();

		assertEquals(7, solitaire.getTableauPiles().size());
		assertEquals(0, solitaire.getWastePile().getCards().size());
		assertEquals(4, solitaire.getFoundationPiles().size());
		for (Suit s : Suit.values()) {
			assertEquals(0, solitaire.getFoundationPiles().get(s).getCards().size());
		}

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
