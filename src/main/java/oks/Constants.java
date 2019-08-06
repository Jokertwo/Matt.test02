package oks;

/**
 * Library class for setting all project constants
 * 
 * @author P.Herout
 *
 */
public class Constants {
  
  /**
   * private constructor to avoid instantiating
   */
  private Constants() {
    // actually no code
  }
  
  /** encoding of all used files */
  public static final String KODOVANI = "UTF-8";
  
  /** the name of the result file */
  public static final String RESULT_FILE = "vysledky.txt";

  /** log file name */
  public static final String LOGGING_FILE = "oks-01-log.txt";

  /** comment introducing character */
  public static final String SIGN_COMMENTARY = "#";

  /** value separator character in the entry line */
  public static final String SIGN_SEPARATOR = ",";

  /** a character that replaces an incorrect or missing information */
  public static final String SIGN_ERROR = "?";

  /** empty person number */
  public static final String ENPTY = "";
  
  /** number of parts that consist of one assignment "Novák, Josef, fav, 2014, b, p, something" */
  public static final int NUMBER_OF_PARTS = 7;

  /** faculties and their features */
  public static final String[][] VALID_FACULTY = { {"FAV", "A"},
                                                    {"FEL", "E"},
                                                    {"FST", "S"},
                                                    {"FPE", "P"},                                        
                                                    {"FEK", "K"},
                                                    {"FF",  "F"},
                                                    {"FPR", "R"},
                                                    {"FZS", "Z"},
                                                    {"FDU", "D"},
                                                  };

  /**
   * Finds a character valid for the faculty
   * 
   * @param faculty faculty abbreviation
   * @return character if the faculty abbreviation is valid or <code> SIGN_ERROR </code> in opposite cases
   */
  public static String najdiZnakFakulty(String faculty) {
    faculty = faculty.toUpperCase();
    for (String[] fakulty : VALID_FACULTY) {
      if (fakulty[0].equals(faculty) == true) {
        return fakulty[1];
      }     
    }
    return SIGN_ERROR;
  }
  
  /** signs of valid forms of study */
  public static final String[] VALID_FORMS_OF_STUDY = {"P", "K", "D"};

  
  /** text in the result file - correctly */
  public static final String TEXT_RIGHT_FORMAT = "správně zadáno";

  /** text in result file - misspelled */
  public static final String TEXT_WRONG_FORMAT = "chybně zadáno";
}
