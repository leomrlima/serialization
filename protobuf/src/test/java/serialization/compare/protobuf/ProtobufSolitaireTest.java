package serialization.compare.protobuf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import serialization.compare.protobuf.SolitaireProtos.State;

public class ProtobufSolitaireTest {

	
	@Test
	public void serializationTest() {
		SolitaireProtos.Solitarie solitaire = SolitaireGame.newSolitaireGame();

		try {
			SolitaireProtos.Solitarie unmarshalled = SolitaireProtos.Solitarie.parseFrom(solitaire.toByteArray());
			
			assertEquals(solitaire, unmarshalled);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Test
	public void saneTest() {
		SolitaireProtos.Solitarie solitaire = SolitaireGame.newSolitaireGame();

		assertEquals(7, solitaire.getTableauPilesCount());
		assertEquals(0, solitaire.getWastePile().getCardsCount());
		assertEquals(0, solitaire.getFoundationPilesMap().size());

		for (int tableau = 0; tableau < 7; tableau++) {
			assertEquals(tableau + 1, solitaire.getTableauPilesList().get(tableau).getCardsCount());
			assertEquals(State.UP, solitaire.getTableauPilesList().get(tableau).getCardsList().get(tableau).getState());
			for (int down = 0; down < tableau; down++) {
				assertEquals(State.DOWN, solitaire.getTableauPilesList().get(tableau).getCardsList().get(down).getState());
			}
		}
		
		assertEquals(24, solitaire.getHandPile().getCardsCount());
	}
}
