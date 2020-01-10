package serialization.compare.jackson;

import java.util.ArrayList;
import java.util.List;

public class CardPile {

	private List<CardState> cards = new ArrayList<>();

	public List<CardState> getCards() {
		return cards;
	}

	public void setCards(List<CardState> cards) {
		this.cards = cards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
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
		CardPile other = (CardPile) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		return true;
	}

}
