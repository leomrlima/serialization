package serialization.compare.yaml.jackson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

import serialization.compare.jackson.Solitaire;

public class JacksonYAMLAdapter {

	private final ObjectMapper mapper;

	public JacksonYAMLAdapter() {
		mapper = new ObjectMapper(new YAMLFactory().disable(Feature.WRITE_DOC_START_MARKER));
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
