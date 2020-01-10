package serialization.compare.xml.jackson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import serialization.compare.jackson.Solitaire;

public class JacksonXMLAdapter {

	private final XmlMapper mapper;

	public JacksonXMLAdapter() {
		mapper = new XmlMapper();
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
