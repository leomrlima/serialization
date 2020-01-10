package serialization.compare.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.ListIterator;

import serialization.compare.protobuf.SolitaireProtos.Card;
import serialization.compare.protobuf.SolitaireProtos.CardState;
import serialization.compare.protobuf.SolitaireProtos.Pile;
import serialization.compare.protobuf.SolitaireProtos.Rank;
import serialization.compare.protobuf.SolitaireProtos.Solitarie;
import serialization.compare.protobuf.SolitaireProtos.State;
import serialization.compare.protobuf.SolitaireProtos.Suit;

public class SolitaireGame {

	public static List<Card> newDeck() {
		List<Card> newDeck = new ArrayList<>(52);
		for (Rank rank : EnumSet.allOf(Rank.class)) {
			if (rank == Rank.UNRECOGNIZED) {
				continue;
			}
			for (Suit suit : EnumSet.allOf(Suit.class)) {
				if (suit == Suit.UNRECOGNIZED) {
					continue;
				}
				newDeck.add(Card.newBuilder().setRank(rank).setSuit(suit).build());
			}
		}
		return newDeck;
	}

	public static Solitarie newSolitaireGame() {
		// create deck
		List<Card> deck = newDeck();

		// shuffle
		Collections.shuffle(deck);

		// set starting piles
		ListIterator<Card> dealer = deck.listIterator();

		List<Pile.Builder> tableauPiles = new ArrayList<>();
		for (int tableau = 0; tableau < 7; tableau++) {
			tableauPiles.add(Pile.newBuilder());
		}
		for (int round = 0; round < 7; round++) {
			for (int tableau = 0; tableau < 7; tableau++) {
				if (round <= tableau) {
					Card card = dealer.next();
					if (round == tableau) {
						tableauPiles.get(tableau).addCards(CardState.newBuilder().setCard(card).setState(State.UP).build());
					} else {
						tableauPiles.get(tableau).addCards(CardState.newBuilder().setCard(card).setState(State.DOWN).build());
					}
				}
			}
		}

		SolitaireProtos.Solitarie.Builder game = SolitaireProtos.Solitarie.newBuilder();
		
		for (Pile.Builder tableauPile : tableauPiles) {
			game.addTableauPiles(tableauPile);
		}
		
		Pile.Builder handPile = Pile.newBuilder();
		while (dealer.hasNext()) {
			handPile.addCards(CardState.newBuilder().setCard(dealer.next()).setState(State.DOWN).build());
		}
		game.setHandPile(handPile.build());
		
		return game.build();
	}
}
