import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main( String[] args ) {

    Scanner scan = new Scanner( System.in );
    boolean exit = false;

    System.out.printf(  "######################################%n" +
                        "# Simple-Calc - Type 'exit' to leave #%n" +
                        "######################################%n%n");

    while ( !exit ) {
      String input = scan.nextLine();
      if ( input.trim().equalsIgnoreCase( "exit" ) ) {
        exit = true;
        scan.close();
        continue;
      }
//      System.out.println( input.toUpperCase() );
      Pattern p = Pattern.compile( "(\\d*[,.]?\\d+)\\s*([/*-+])\\s*(\\d*[,.]?\\d+)" );
      Matcher m = p.matcher( input );
      if ( m.matches() ) {
        Double doubleResult = solve( m.group( 1 ), m.group( 3 ), m.group( 2 ) );
        if ((doubleResult % 1) == 0)
          System.out.println( "= " + doubleResult.intValue() );
        else
          System.out.println( "= " + new DecimalFormat().format( doubleResult ) );
      }
      else
        System.err.println("### No valid input! ###");

    }
  }

  private static double solve( String firstNumber, String secondNumber, String operator ) {
    if (firstNumber.contains( "," ))
      firstNumber = firstNumber.replace( ',', '.' );
    if (secondNumber.contains( "," ))
      secondNumber = secondNumber.replace( ',', '.' );

    double d1 = Double.parseDouble( firstNumber );
    double d2 = Double.parseDouble( secondNumber );
    double result = 0;
    switch ( operator ) {
      case "/" -> result = d1 / d2;
      case "*" -> result = d1 * d2;
      case "-" -> result = d1 - d2;
      case "+" -> result = d1 + d2;
    }
    return result;
  }
}