package serialization.compare.moxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

public class MoxyAdapter {

	private final JAXBContext jaxbContext;
	private final Marshaller marshaller;
	private final Unmarshaller unmarshaller;
	
	public MoxyAdapter() {
		this(false);
	}
	
	public MoxyAdapter(boolean formatted) {
        try {
			jaxbContext = JAXBContext.newInstance(Solitaire.class);
	        marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);
	        unmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public byte[] toByteArray(Solitaire gameState) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
	        JAXBElement<Solitaire> jaxbElement = new JAXBElement<Solitaire>(new QName(null, "solitaire"), Solitaire.class, gameState);
			marshaller.marshal(jaxbElement, baos);
			return baos.toByteArray();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public Solitaire fromByteArray(byte[] gameState) {
		try {
			return (Solitaire) unmarshaller.unmarshal(new ByteArrayInputStream(gameState));
		} catch (JAXBException e) {
			throw new IllegalStateException(e);
		}
	}

}
