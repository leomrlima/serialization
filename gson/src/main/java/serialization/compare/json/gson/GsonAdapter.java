package serialization.compare.json.gson;

import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import serialization.compare.jackson.Solitaire;

public class GsonAdapter {

	private final Gson gson;

	private static final Charset charset = Charset.forName("UTF-8");

	public GsonAdapter() {
		this(false);
	}
	
	public GsonAdapter(boolean formatted) {
		GsonBuilder builder = new GsonBuilder();
		
		if (formatted) {
			builder.setPrettyPrinting();
		}
		
		this.gson = builder.create();
	}
	
	public byte[] toByteArray(Solitaire gameState) {
		return gson.toJson(gameState).getBytes(charset);
	}

	public Solitaire fromByteArray(byte[] gameState) {
		return gson.fromJson(new String(gameState, charset), Solitaire.class);
	}

}
