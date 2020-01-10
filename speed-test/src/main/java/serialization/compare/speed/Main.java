package serialization.compare.speed;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.google.protobuf.InvalidProtocolBufferException;

import serialization.compare.bson.jackson.BsonAdapter;
import serialization.compare.json.gson.GsonAdapter;
import serialization.compare.json.jackson.JacksonJSONAdapter;
import serialization.compare.xml.jackson.JacksonXMLAdapter;
import serialization.compare.yaml.jackson.JacksonYAMLAdapter;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Warmup(iterations = 2)
@Measurement(iterations = 5)
public class Main {

	@Param({ "1000" })
	private int N;

	private List<serialization.compare.jackson.Solitaire> TEXT_DATA_FOR_TESTING = new ArrayList<>();

	private List<serialization.compare.bson.jackson.Solitaire> BSON_DATA_FOR_TESTING = new ArrayList<>();

	private List<serialization.compare.avro.Solitaire> AVRO_DATA_FOR_TESTING = new ArrayList<>();

	private List<serialization.compare.protobuf.SolitaireProtos.Solitarie> PROTOBUF_DATA_FOR_TESTING = new ArrayList<>();

	private JacksonYAMLAdapter yamlAdapter;
	private JacksonJSONAdapter jsonAdapter;
	private GsonAdapter gsonAdapter;
	private JacksonXMLAdapter xmlAdapter;

	private BsonAdapter bsonAdapter;
	
	public static void main(String[] args) throws RunnerException {

		Options opt = new OptionsBuilder().include(Main.class.getSimpleName()).forks(1).build();

		new Runner(opt).run();

	}

	@Setup
	public void setup() {
		for (int i = 0; i < N; i++) {
			TEXT_DATA_FOR_TESTING.add(serialization.compare.jackson.Solitaire.newSolitaireGame());
			BSON_DATA_FOR_TESTING.add(serialization.compare.bson.jackson.Solitaire.newSolitaireGame());
			AVRO_DATA_FOR_TESTING.add(serialization.compare.avro.SolitaireGame.newSolitaireGame());
			PROTOBUF_DATA_FOR_TESTING.add(serialization.compare.protobuf.SolitaireGame.newSolitaireGame());
		}

		gsonAdapter = new GsonAdapter();
		jsonAdapter = new JacksonJSONAdapter();
		yamlAdapter = new JacksonYAMLAdapter();
		xmlAdapter = new JacksonXMLAdapter();
		bsonAdapter = new BsonAdapter();
	}

	@Benchmark
	public void gsonSerialize(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = gsonAdapter.toByteArray(game);
			bh.consume(ba);
		}
	}

	@Benchmark
	public void jsonSerialize(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = jsonAdapter.toByteArray(game);
			bh.consume(ba);
		}
	}

	@Benchmark
	public void xmlSerialize(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = xmlAdapter.toByteArray(game);
			bh.consume(ba);
		}
	}

	@Benchmark
	public void yamlSerialize(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = yamlAdapter.toByteArray(game);
			bh.consume(ba);
		}
	}
	
	@Benchmark
	public void bsonSerialize(Blackhole bh) {
		for (serialization.compare.bson.jackson.Solitaire game : BSON_DATA_FOR_TESTING) {
			byte[] ba = bsonAdapter.toByteArray(game);
			bh.consume(ba);
		}
	}
	
	@Benchmark
	public void avroSerialize(Blackhole bh) {
		for (serialization.compare.avro.Solitaire game : AVRO_DATA_FOR_TESTING) {
			Object ba;
			try {
				ba = game.toByteBuffer();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
			bh.consume(ba);
		}
	}
	
	@Benchmark
	public void protobufSerialize(Blackhole bh) {
		for (serialization.compare.protobuf.SolitaireProtos.Solitarie game : PROTOBUF_DATA_FOR_TESTING) {
			byte[] ba = game.toByteArray();
			bh.consume(ba);
		}
	}
	
	@Benchmark
	public void gsonRoundTrip(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = gsonAdapter.toByteArray(game);
			game = gsonAdapter.fromByteArray(ba);
			bh.consume(ba);
			bh.consume(game);
		}
	}

	@Benchmark
	public void jsonRoundTrip(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = jsonAdapter.toByteArray(game);
			game = jsonAdapter.fromByteArray(ba);
			bh.consume(ba);
			bh.consume(game);
		}
	}

	@Benchmark
	public void xmlRoundtrip(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = xmlAdapter.toByteArray(game);
			game = xmlAdapter.fromByteArray(ba);
			bh.consume(ba);
			bh.consume(game);
		}
	}

	@Benchmark
	public void yamlRoundtrip(Blackhole bh) {
		for (serialization.compare.jackson.Solitaire game : TEXT_DATA_FOR_TESTING) {
			byte[] ba = yamlAdapter.toByteArray(game);
			game = yamlAdapter.fromByteArray(ba);
			bh.consume(ba);
			bh.consume(game);
		}
	}
	
	@Benchmark
	public void bsonRoundtrip(Blackhole bh) {
		for (serialization.compare.bson.jackson.Solitaire game : BSON_DATA_FOR_TESTING) {
			byte[] ba = bsonAdapter.toByteArray(game);
			game = bsonAdapter.fromByteArray(ba);
			bh.consume(ba);
			bh.consume(game);
		}
	}
	
	@Benchmark
	public void avroRoundtrip(Blackhole bh) {
		for (serialization.compare.avro.Solitaire game : AVRO_DATA_FOR_TESTING) {
			ByteBuffer ba;
			try {
				ba = game.toByteBuffer();
				game = serialization.compare.avro.Solitaire.fromByteBuffer(ba);
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
			bh.consume(ba);
			bh.consume(game);
		}
	}
	
	@Benchmark
	public void protobufRoundtrip(Blackhole bh) {
		for (serialization.compare.protobuf.SolitaireProtos.Solitarie game : PROTOBUF_DATA_FOR_TESTING) {
			try {
				byte[] ba = game.toByteArray();
				game = serialization.compare.protobuf.SolitaireProtos.Solitarie.parseFrom(ba);
				bh.consume(ba);
				bh.consume(game);
			} catch (InvalidProtocolBufferException e) {
				throw new IllegalStateException(e);
			}
		}
	}

}
