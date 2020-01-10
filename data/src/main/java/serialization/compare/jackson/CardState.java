package serialization.compare.jackson;

public class CardState {
	private Card card;
	private State state;

	public CardState() {
		// TODO Auto-generated constructor stub
	}

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

	public void setCard(Card card) {
		this.card = card;
	}

	public void setState(State state) {
		this.state = state;
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
