package serialization.compare.yaml.jackson;

import serialization.compare.jackson.Solitaire;

public class Compare {

	public static void main(String[] args) {
		Solitaire game = Solitaire.newSolitaireGame();

		System.out.println("Jackson YAML Size\t" + new JacksonYAMLAdapter().toByteArray(game).length);

		System.out.println(new String(new JacksonYAMLAdapter().toByteArray(game)));
	}

}
