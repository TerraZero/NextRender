import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import tz.source.json.JSONO;

public class Start {
	
	public static void main(String[] args) {
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get("source/data/test.json")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
		}
		JSONO t = new JSONO(text);
		if (t.type() == JSONO.Type.OBJECT) {
			System.out.println("cool");
		}
		System.out.println(t.get("sdfjsdfijl").type());
		System.out.println(t.get("test-string").get());
	}
	
}
