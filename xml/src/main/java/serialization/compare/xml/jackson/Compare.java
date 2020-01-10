package serialization.compare.xml.jackson;

import serialization.compare.jackson.Solitaire;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();

		byte[] contents = new JacksonXMLAdapter().toByteArray(game);
		
		System.out.println("Jackson XML Size\t" + contents.length);
		
		System.out.println(new String(contents));

	}

}
