package serialization.compare.json.gson;

import serialization.compare.jackson.Solitaire;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();
		
		System.out.println("Gson Size (unformatted)\t" + new GsonAdapter().toByteArray(game).length);
		System.out.println("Gson Size (formatted)\t" + new GsonAdapter(true).toByteArray(game).length);

		System.out.println(new String(new GsonAdapter(true).toByteArray(game)));

	}

}
