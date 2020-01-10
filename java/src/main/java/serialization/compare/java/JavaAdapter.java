package serialization.compare.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaAdapter {

	public byte[] toByteArray(Solitaire gameState) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
			objectOutputStream.writeObject(gameState);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
		return baos.toByteArray();
	}

	public Solitaire fromByteArray(byte[] gameState) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(gameState));
			return (Solitaire) objectInputStream.readObject();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
