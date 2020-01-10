package serialization.compare.protobuf;

public class Compare {

	public static void main(String[] args) {
		SolitaireProtos.Solitarie game = SolitaireGame.newSolitaireGame();

		System.out.println("Protobuf Size\t" + game.toByteArray().length);

	}

}
