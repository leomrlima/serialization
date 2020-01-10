package serialization.compare.bson.jackson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class BsonAdapter {

	private final ObjectMapper mapper;

	public BsonAdapter() {
		mapper = new ObjectMapper(new BsonFactory());
		mapper.findAndRegisterModules();
	}

	public byte[] toByteArray(Solitaire gameState) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			mapper.writeValue(baos, gameState);
			return baos.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public Solitaire fromByteArray(byte[] gameState) {
		try {
			return mapper.readValue(new ByteArrayInputStream(gameState), Solitaire.class);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
