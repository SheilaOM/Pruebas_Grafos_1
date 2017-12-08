import java.io.*;

public class Thermostat {
	static String partOfDay = new String("Wake");
	static String temp = new String("Low");

	public static void advance() {
		if (partOfDay == "Wake"){
			partOfDay = "Sleep";
		} else if (partOfDay == "Sleep") {
			partOfDay = "Wake";
		}
	}
	
	public static void up() {
		if (temp == "Low") {
			temp = "High";
		}
	}
	
	public static void down() {
		if (temp == "High") {
			temp = "Low";
		}
	}
}
