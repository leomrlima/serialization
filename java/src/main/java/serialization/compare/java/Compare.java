package serialization.compare.java;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();
		
		JavaAdapter adapter = new JavaAdapter();
		
		byte[] persisted = adapter.toByteArray(game);
		
		System.out.println("Java OOS Size\t" + persisted.length);
		
		

	}

}
