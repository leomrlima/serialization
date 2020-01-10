package serialization.compare.moxy;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();

		System.out.println("Moxy Size (unformatted)\t" + new MoxyAdapter(false).toByteArray(game).length);
		System.out.println("Moxy Size (formatted)\t" + new MoxyAdapter(true).toByteArray(game).length);

		System.out.println(new String(new MoxyAdapter(true).toByteArray(game)));
	}

}
