package serialization.compare.bson.jackson;

import java.util.Base64;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();

		System.out.println("Jackson BSON Size\t" + new BsonAdapter().toByteArray(game).length);

		System.out.println(Base64.getEncoder().encodeToString(new BsonAdapter().toByteArray(game)));
	}

}
