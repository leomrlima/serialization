package serialization.compare.json.jackson;

import serialization.compare.jackson.Solitaire;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();

		System.out.println("Jackson JSON Size\t" + new JacksonJSONAdapter().toByteArray(game).length);

	}

}
