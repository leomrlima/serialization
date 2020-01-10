package serialization.compare.bson.jackson;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

public class Card implements Comparable<Card> {
	public Rank rank;
	public Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Card() {

	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return this.rank;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public static Stream<Card> streamCards() {
		return Card.cartesianProduct(EnumSet.allOf(Rank.class), EnumSet.allOf(Suit.class));
	}

	private static Stream<Card> cartesianProduct(Set<Rank> set1, Set<Suit> set2) {
		return set1.stream().flatMap(first -> set2.stream().map(second -> new Card(first, second)));
	}

	@Override
	public int compareTo(Card o) {
		return Comparator.comparing(Card::getSuit).thenComparing(Card::getRank).compare(this, o);
	}

	public boolean isSameRank(Rank rank) {
		return this.rank == rank;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Card)) {
			return false;
		}
		Card card = (Card) object;
		return this.rank == card.rank && this.suit == card.suit;
	}

	public int hashCode() {
		int result = 31 + this.rank.hashCode();
		result = 31 * result + this.suit.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return this.rank + " of " + this.suit;
	}

}
