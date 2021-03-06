package serialization.compare.java;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Names were inspired from https://bicyclecards.com/how-to-play/solitaire/
 * 
 */
public class Solitaire implements Serializable {

	public static enum State {
		UP, DOWN;
	}

	public static class CardState implements Serializable {
		private Card card;
		private State state;

		public CardState(Card card, State state) {
			this.card = card;
			this.state = state;
		}

		public Card getCard() {
			return card;
		}

		public State getState() {
			return state;
		}

		public void flip() {
			switch (state) {
			case DOWN:
				state = State.UP;
				break;
			case UP:
				state = State.DOWN;
				break;
			}
		}

		@Override
		public String toString() {
			return "CardState [card=" + card + ", state=" + state + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((card == null) ? 0 : card.hashCode());
			result = prime * result + ((state == null) ? 0 : state.hashCode());
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
			CardState other = (CardState) obj;
			if (card == null) {
				if (other.card != null)
					return false;
			} else if (!card.equals(other.card))
				return false;
			if (state != other.state)
				return false;
			return true;
		}

	}

	private List<List<CardState>> tableauPiles;

	/**
	 * Four piles on which a whole suit or sequence must be built up. In most
	 * Solitaire games, the four aces are the bottom card or base of the
	 * foundations. The foundation piles are hearts, diamonds, spades, and clubs.
	 */
	private Map<Suit, List<Card>> foundationPiles;

	private List<Card> handPile;

	private List<Card> wastePile;

	public static Solitaire newSolitaireGame() {
		// create deck
		List<Card> deck = Card.streamCards().collect(toList());

		// shuffle
		Collections.shuffle(deck);

		// set starting piles
		ListIterator<Card> dealer = deck.listIterator();

		Solitaire game = new Solitaire();
		game.wastePile = new ArrayList<>();
		game.foundationPiles = new HashMap<>();
		for (Suit s : Suit.values()) {
			game.foundationPiles.put(s, new ArrayList<>());
		}
		game.tableauPiles = new ArrayList<>();
		for (int tableau = 0; tableau < 7; tableau++) {
			game.tableauPiles.add(new ArrayList<>());
		}
		for (int round = 0; round < 7; round++) {
			for (int tableau = 0; tableau < 7; tableau++) {
				if (round <= tableau) {
					Card card = dealer.next();
					if (round == tableau) {
						game.tableauPiles.get(tableau).add(new CardState(card, State.UP));
					} else {
						game.tableauPiles.get(tableau).add(new CardState(card, State.DOWN));
					}
				}
			}
		}

		game.handPile = new ArrayList<Card>(deck.subList(dealer.nextIndex(), deck.size()));

		return game;
	}

	Map<Suit, List<Card>> getFoundationPiles() {
		return foundationPiles;
	}

	List<Card> getHandPile() {
		return handPile;
	}

	List<List<CardState>> getTableauPiles() {
		return tableauPiles;
	}

	List<Card> getWastePile() {
		return wastePile;
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
