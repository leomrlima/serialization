package serialization.compare.avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class SchemaBuild {

	public static void main(String[] args) {
		Schema card = 
				SchemaBuilder.record("Card").namespace("serialization.compare.avro").fields()
				
				.name("suit")
			    .type()
			    .enumeration("Suit")
			    .symbols("SPADES","DIAMONDS","HEARTS","CLUBS")
			    .noDefault()
			    
			    .name("rank")
			    .type()
			    .enumeration("Rank")
			    .symbols("ACE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","TEN","JACK","QUEEN","KING")
			    .noDefault()
				.endRecord();
		
		Schema cardState = 
				SchemaBuilder.record("CardState").namespace("serialization.compare.avro").fields()
				.name("card").type(card).noDefault()
				.name("state").type()
			    .enumeration("State")
			    .symbols("UP","DOWN")
			    .noDefault()
				.endRecord();
		
		Schema pile = SchemaBuilder.record("Pile").namespace("serialization.compare.avro").fields()
				.name("cards").type().array().items(cardState).noDefault().endRecord();
		
		Schema solitaire = SchemaBuilder.record("Solitaire").namespace("serialization.compare.avro").fields()
				.name("tableauPiles").type().array().items(pile).noDefault()
				.name("foundationPiles").type().map().values(pile).noDefault()
				.name("handPile").type(pile).noDefault()
				.name("wastePile").type(pile).noDefault()
				
				.endRecord();
		
		System.out.println(solitaire.toString(true));
	}

}
