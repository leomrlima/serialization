package serialization.compare.avro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public class SolitaireGame {

	public static List<Card> newDeck() {
		List<Card> newDeck = new ArrayList<>(52);
		for (Rank rank : EnumSet.allOf(Rank.class)) {
			for (Suit suit : EnumSet.allOf(Suit.class)) {
				newDeck.add(Card.newBuilder().setRank(rank).setSuit(suit).build());
			}
		}
		return newDeck;
	}

	public static Solitaire newSolitaireGame() {
		// create deck
		List<Card> deck = newDeck();

		// shuffle
		Collections.shuffle(deck);

		// set starting piles
		ListIterator<Card> dealer = deck.listIterator();

		List<List<CardState>> tableauPiles = new ArrayList<>();
		for (int tableau = 0; tableau < 7; tableau++) {
			tableauPiles.add(new ArrayList<>());
		}
		for (int round = 0; round < 7; round++) {
			for (int tableau = 0; tableau < 7; tableau++) {
				if (round <= tableau) {
					Card card = dealer.next();
					if (round == tableau) {
						tableauPiles.get(tableau).add(CardState.newBuilder().setCard(card).setState(State.UP).build());
					} else {
						tableauPiles.get(tableau).add(CardState.newBuilder().setCard(card).setState(State.DOWN).build());
					}
				}
			}
		}

		Solitaire.Builder game = Solitaire.newBuilder();
		
		List<Pile> builtTableauPiles = new ArrayList<Pile>();
		
		for (List<CardState> tableauPile : tableauPiles) {
			builtTableauPiles.add(Pile.newBuilder().setCards(tableauPile).build());
		}
		game.setTableauPiles(builtTableauPiles);
		
		List<CardState> handPile = new ArrayList<>();
		while (dealer.hasNext()) {
			handPile.add(CardState.newBuilder().setCard(dealer.next()).setState(State.DOWN).build());
		}
		game.setHandPile(Pile.newBuilder().setCards(handPile).build());
		
		
		Map<CharSequence, Pile> foundationPiles = new TreeMap<CharSequence, Pile>();
		for (Suit s : Suit.values()) {
			foundationPiles.put(s.name(), Pile.newBuilder().setCards(new ArrayList<CardState>()).build());
		}
		game.setFoundationPiles(foundationPiles);
		
		game.setWastePile(Pile.newBuilder().setCards(new ArrayList<CardState>()).build());
		
		return game.build();
	}
}
