package serialization.compare.json.jackson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import serialization.compare.jackson.Solitaire;

public class JacksonJSONAdapter {

	private final ObjectMapper mapper;

	public JacksonJSONAdapter() {
		mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
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
