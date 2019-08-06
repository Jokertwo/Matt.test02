package oks;

/**
 * Valid types of studies and their abbreviations
 * 
 * @author P.Herout
 *
 */

public enum TypStudia {
  BACHELOR("B", "Bakalářský"), FOLLOW_UP("N", "Navazující"),
  DOCTORAL("P", "Doktorský"), MASTER("M", "Magisterský"),
  INVALID(Constants.SIGN_ERROR, "neplatný");
  
  /** abbreviation of the type of study */
  private final String shortCut;
  
  /** name of the type of study */
  private final String name;
  
  /**
   * Fills an enum type value with additional information
   * 
   * @param shortCut abbreviation of the type of study
   * @param name name of the type of study
   */
  private TypStudia(String shortCut, String name) {
    this.shortCut = shortCut;
    this.name = name;
  }

  /**
   * Returns the type of study abbreviation
   * 
   * @return abbreviation of the type of study
   */
  public String getShortCut() {
    return shortCut;
  }
  
  /**
   * Returns the name of the study type
   * 
   * @return name of the type of study
   */
  public String getName() {
    return name;
  }
}
