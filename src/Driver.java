import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

  public static void main(String[] args) {
    try {
      String filePath = "test.txt";
      LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
      lexicalAnalyzer.initialize(filePath);
      Parser parser = new Parser();
      System.out.println( "output: "+parser.P());
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
   
  }

}
