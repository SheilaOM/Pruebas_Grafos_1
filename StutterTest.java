import static org.junit.Assert.*;
import org.junit.*;

import java.io.*;
import java.util.*;

public class StutterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        
        // static variables in class Stutter must be reinitialized
        Stutter.lastdelimit = true;
        Stutter.curWord = "";
        Stutter.prevWord = "";
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }


    // This test tours main:if (fileName == null)   
    //.It is difficult/impossible to execute, as it requires passing a command line 
    // argument that is null
    
    /* PARES-DU SATISFECHOS POR testFromStdinWithNullArgs
     * inFile -> M(llamada método):53-stut, LD(last def):44, FU(first use): 69
     * linecnt -> M: 86-checkDupes, LD: 68, FU: 105
     * linecnt -> M: 86-checkDupes, LD: 87, FU: 105
     * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * prevWord -> M: 86-checkDupes(return), LD: 110, FU: 103
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * lastdelimit -> M: 86-CheckDupes(return), LD: 102, FU: 99
     * curWord -> M: 86-CheckDupes(return), LD: 112, FU: 83
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
    @Test					
    public void testFromStdinWithNullArgs() {
        String[] args = {null};
        String string = "word\nword\nword\n";
        InputStream stringStream = new ByteArrayInputStream(string.getBytes());
        
        System.setIn(stringStream);

        try {
            Stutter.main(args);
        } catch (IOException e) {
            fail(e.getMessage());
        }
        
        assertEquals("Repeated word on line 2: word word\n" +
                     "Repeated word on line 3: word word\n", outContent.toString());
        
        System.setIn(System.in);

    }
    
    
    /* PARES-DU SATISFECHOS POR testFromFile
     * inFile -> M(llamada método):53-stut, LD(last def):49, FU(first use): 69
     * prevWord -> M: 86-checkDupes(return), LD: 110, FU: 103
     * linecnt -> M: 86-checkDupes, LD: 68, FU: 105
     * linecnt -> M: 86-checkDupes, LD: 87, FU: 105
     * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * lastdelimit -> M: 86-CheckDupes(return), LD: 102, FU: 99
     * curWord -> M: 86-CheckDupes(return), LD: 112, FU: 83
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
    @Test
    public void testFromFile() {
        try {
            Stutter.main(new String[] { "inputFile" });
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertEquals("Repeated word on line 2: word word\n", outContent.toString());
    }
    
    
    /* PARES-DU SATISFECHOS POR t1
     * inFile -> M(llamada método):53-stut, LD(last def):37, FU(first use): 69
     * prevWord -> M: 86-checkDupes(return), LD: 110, FU: 103
     * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * curWord -> M: 86-CheckDupes(return), LD: 112, FU: 83
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * lastdelimit -> M: 86-CheckDupes(return), LD: 102, FU: 99
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
    @Test
    public void t1() {
        String string = "hi";
        InputStream stringStream = new ByteArrayInputStream(string.getBytes());
        
        System.setIn(stringStream);

        try {
            Stutter.main(new String[] {});
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertEquals("", outContent.toString());
        
        System.setIn(System.in);
    }
    
    
    /* PARES-DU SATISFECHOS POR t2
     * inFile -> M(llamada método):53-stut, LD(last def):37, FU(first use): 69
     * linecnt -> M: 86-checkDupes, LD: 68, FU: 105
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * prevWord -> M: 78-checkDupes(return), LD: 110, FU: 103
     * curWord -> M: 78-CheckDupes, LD: 83, FU: 103
     * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * true -> M: 76-isDelimit(return), LD: 122, FU: 76
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
    @Test
    public void t2() {
    	String string = "hi hi";
    	InputStream stringStream = new ByteArrayInputStream(string.getBytes());   
    	System.setIn(stringStream);

    	try {
    		Stutter.main(new String[] {});
    	} catch (IOException e) {
    		fail(e.getMessage());
    	}
    	assertEquals("Repeated word on line 1: hi hi\n", outContent.toString());

    	System.setIn(System.in);
    }
    
  
    /* PARES-DU SATISFECHOS POR t3
     * inFile -> M(llamada método):53-stut, LD(last def):37, FU(first use): 69
     * linecnt -> M: 86-checkDupes, LD: 87, FU: 105
     * lastdelimit -> M: 78-CheckDupes, LD: 102, FU: 99
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * lastdelimit -> M: 86-CheckDupes, LD: 102, FU: 99
     * curWord -> M: 78-CheckDupes(return), LD: 112, FU: 83
     * prevWord -> M: 78-checkDupes(return), LD: 110, FU: 103
     * prevWord -> M: 86-checkDupes(return), LD: 110, FU: 103
	 * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * true -> M: 76-isDelimit(return), LD: 122, FU: 76
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
  	@Test
  	public void t3() {
      	String string = "yup\nhi hi";
      InputStream stringStream = new ByteArrayInputStream(string.getBytes());   
      System.setIn(stringStream);

      try {
          Stutter.main(new String[] {});
      } catch (IOException e) {
          fail(e.getMessage());
      }
      assertEquals("Repeated word on line 2: hi hi\n", outContent.toString());

      System.setIn(System.in);
  	}


    /* PARES-DU SATISFECHOS POR t4
     * inFile -> M(llamada método):53-stut, LD(last def):37, FU(first use): 69
     * linecnt -> M: 86-checkDupes, LD: 87, FU: 105
     * lastdelimit -> M: 78-CheckDupes, LD: 102, FU: 99
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * curWord -> M: 86-CheckDupes(return), LD: 112, FU: 83
     * prevWord -> M: 78-checkDupes(return), LD: 110, FU: 103
     * prevWord -> M: 86-checkDupes(return), LD: 110, FU: 103
 	 * curWord -> M: 76-CheckDupes, LD: 83, FU: 103
	 * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * true -> M: 76-isDelimit(return), LD: 122, FU: 76
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
  	@Test
  	public void t4() {
      	String string = "yup\n\nhi hi";
      InputStream stringStream = new ByteArrayInputStream(string.getBytes());   
      System.setIn(stringStream);

      try {
          Stutter.main(new String[] {});
      } catch (IOException e) {
          fail(e.getMessage());
      }
      assertEquals("Repeated word on line 3: hi hi\n", outContent.toString());

      System.setIn(System.in);
  }
  
    
    /* PARES-DU SATISFECHOS POR t5
     * inFile -> M(llamada método):53-stut, LD(last def):37, FU(first use): 69
     * linecnt -> M: 78-checkDupes, LD: 68, FU: 105
     * linecnt -> M: 78-checkDupes, LD: 87, FU: 105
     * lastdelimit -> M: 78-CheckDupes, LD: 82, FU: 99
     * lastdelimit -> M: 86-CheckDupes, LD: 82, FU: 99
     * curWord -> M: 78-CheckDupes(return), LD: 112, FU: 83
     * curWord -> M: 86-CheckDupes(return), LD: 112, FU: 83
     * prevWord -> M: 78-checkDupes(return), LD: 110, FU: 103
     * prevWord -> M: 86-checkDupes(return), LD: 110, FU: 103
 	 * curWord -> M: 76-CheckDupes, LD: 83, FU: 103
	 * curWord -> M: 86-CheckDupes, LD: 83, FU: 103
     * c -> M: 76-isDelimit, LD: 74, FU: 121
     * true -> M: 76-isDelimit(return), LD: 122, FU: 76
     * false -> M: 76-isDelimit(return), LD: 123, FU: 76
     */
    @Test
    public void t5() {
    	String string = "hi hi ho\n ho he";
        InputStream stringStream = new ByteArrayInputStream(string.getBytes());   
        System.setIn(stringStream);

        try {
            Stutter.main(new String[] {});
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertEquals("Repeated word on line 1: hi hi\n" +
                	 "Repeated word on line 2: ho ho\n", outContent.toString());
        System.setIn(System.in);
    }

}

