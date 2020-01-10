package serialization.compare.jackson;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Names were inspired from https://bicyclecards.com/how-to-play/solitaire/
 * 
 *
 */
public class Solitaire {
	private List<CardPile> tableauPiles;
	private Map<Suit, CardPile> foundationPiles;
	private CardPile handPile;
	private CardPile wastePile;

	public static Solitaire newSolitaireGame() {
		// create deck
		List<Card> deck = Card.streamCards().collect(toList());

		// shuffle
		Collections.shuffle(deck);

		// set starting piles
		ListIterator<Card> dealer = deck.listIterator();

		Solitaire game = new Solitaire();
		game.wastePile = new CardPile();
		game.handPile = new CardPile();
		game.foundationPiles = new HashMap<>();
		for (Suit s : Suit.values()) {
			game.foundationPiles.put(s, new CardPile());
		}
		game.tableauPiles = new ArrayList<>();
		for (int tableau = 0; tableau < 7; tableau++) {
			game.tableauPiles.add(new CardPile());
		}
		for (int round = 0; round < 7; round++) {
			for (int tableau = 0; tableau < 7; tableau++) {
				if (round <= tableau) {
					Card card = dealer.next();
					if (round == tableau) {
						game.tableauPiles.get(tableau).getCards().add(new CardState(card, State.UP));
					} else {
						game.tableauPiles.get(tableau).getCards().add(new CardState(card, State.DOWN));
					}
				}
			}
		}

		game.handPile.setCards(deck.subList(dealer.nextIndex(), deck.size()).stream()
				.map(c -> new CardState(c, State.DOWN)).collect(Collectors.toList()));

		return game;
	}

	public List<CardPile> getTableauPiles() {
		return tableauPiles;
	}

	public void setTableauPiles(List<CardPile> tableauPiles) {
		this.tableauPiles = tableauPiles;
	}

	public Map<Suit, CardPile> getFoundationPiles() {
		return foundationPiles;
	}

	public void setFoundationPiles(Map<Suit, CardPile> foundationPiles) {
		this.foundationPiles = foundationPiles;
	}

	public CardPile getHandPile() {
		return handPile;
	}

	public void setHandPile(CardPile handPile) {
		this.handPile = handPile;
	}

	public CardPile getWastePile() {
		return wastePile;
	}

	public void setWastePile(CardPile wastePile) {
		this.wastePile = wastePile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foundationPiles == null) ? 0 : foundationPiles.hashCode());
		result = prime * result + ((handPile == null) ? 0 : handPile.hashCode());
		result = prime * result + ((tableauPiles == null) ? 0 : tableauPiles.hashCode());
		result = prime * result + ((wastePile == null) ? 0 : wastePile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solitaire other = (Solitaire) obj;
		if (foundationPiles == null) {
			if (other.foundationPiles != null)
				return false;
		} else if (!foundationPiles.equals(other.foundationPiles))
			return false;
		if (handPile == null) {
			if (other.handPile != null)
				return false;
		} else if (!handPile.equals(other.handPile))
			return false;
		if (tableauPiles == null) {
			if (other.tableauPiles != null)
				return false;
		} else if (!tableauPiles.equals(other.tableauPiles))
			return false;
		if (wastePile == null) {
			if (other.wastePile != null)
				return false;
		} else if (!wastePile.equals(other.wastePile))
			return false;
		return true;
	}

}
