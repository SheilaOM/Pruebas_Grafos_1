import static org.junit.Assert.*;
import org.junit.*;

public class ThermostatTest {

	@Before
	public void setUpStreams() {
		Thermostat.partOfDay = "Wake";
		Thermostat.temp = "Low";
	}
	
	@Test
	public void test1() {
		Thermostat.advance();
		Thermostat.up();
		Thermostat.advance();
		Thermostat.down();
		Thermostat.up();
		Thermostat.advance();
		Thermostat.down();
		Thermostat.advance();
		assertEquals("Wake-Low", Thermostat.partOfDay + "-" + Thermostat.temp);
	}
}

