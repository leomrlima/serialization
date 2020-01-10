package serialization.compare.avro;

import java.io.IOException;

public class Compare {

	public static void main(String[] args) throws IOException {
		Solitaire game = SolitaireGame.newSolitaireGame();

		System.out.println("Avro Size\t" + game.toByteBuffer().remaining());

	}

}
