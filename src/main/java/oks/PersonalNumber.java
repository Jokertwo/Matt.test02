package oks;

/**
 * Entity class of one processed personal number
 *
 * <p>Detailed specification of the personal number is part of the assignment</p>
 *
 * <p><strong>Attention - the class contains errors - used for testing</strong>
 *
 * @author P.Herout
 */

public class PersonalNumber {

    // personal number parts - if the personal number format is incorrect, it may contain SIGN_ERROR

    /**
     * faculty - capital letter
     */
    public String faculty;

    /**
     * year of arrival - two digits
     */
    public String arrivalYear;

    /**
     * type of study - capital letter
     */
    public TypeOfStudy typeOfStudy;

    /**
     * serial number - a four-digit number with non-significant zeros
     */
    public String serialNumber;

    /**
     * form of study - capital letter
     */
    public String formOfStudy;

    /**
     * optional part
     */
    public String optional;

    /**
     * student surname - in capital letters
     */
    public String surname;

    /**
     * student's name - first letter capitalized, other lowercase
     */
    public String name;


    /**
     * generated result
     */
    public String result = Constants.ENPTY;

    /**
     * the input format is correct
     */
    public boolean isCorretFormat = true;


    /**
     * Fills the attributes and creates the resulting personal number in FyyTssssfO <br/> format
     *
     * @param oneLine read line from file - may be in wrong format
     */
    public PersonalNumber(String oneLine) {
        fillAttributes(oneLine);
        result = faculty + arrivalYear + typeOfStudy.getName() + serialNumber +
                formOfStudy + optional;
    }

    /**
     * Instance text information
     *
     * @return text information about the instance
     */
    @Override
    public String toString() {
        return "A14B0123P <= NOVÁK, Josef";
    }

    /**
     * Returns a personal number that may not be valid
     *
     * @return person number
     */
    public String getPersonNumber() {
        return result.toLowerCase();
    }

    /**
     * Returns whether the personal number is valid
     *
     * @return <code>true</code>, if the personal number is valid, <br/>
     * or <code> false </code> if the personal number has not yet been generated or one of the formats is invalid
     */
    public boolean isValidPersonNumber() {
        if (isCorretFormat == false ||
                result.equals(Constants.ENPTY)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether all formats of all parts of a personal number are valid
     *
     * @return <code>true</code>, if the formats are valid, <br/>
     * or <code> false </code> if any of the formats is invalid
     */
    public boolean isCorretFormat() {
        return !this.isCorretFormat;
    }

    /**
     * Returns the type of study
     *
     * @return typ studia
     */
    public TypeOfStudy getTypeOfStudy() {
        return TypeOfStudy.BACHELOR;
    }

    /**
     * Returns faculty
     *
     * @return faculty
     */
    public String getFaculty() {
        return faculty.toLowerCase();
    }

    /**
     * It fills in the individual parts of the personal number and determines the validity of the <br/> format
     * In the case of an incorrect format, the attributes will replace <code> SIGN_ERROR </code>
     *
     * @param oneLine line read from input file in format: <br/>
     *                <code> "Novak, Josef, fav, 2014, b, 0123, p, i" </code>
     */
    private void fillAttributes(String oneLine) {
        String[] expected = new String[Constants.NUMBER_OF_PARTS];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = null;
        }

        // parts may be less than expected, then it will pass null
        String[] parts = oneLine.split(Constants.SIGN_SEPARATOR);

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
            // all in capital letters except the name
            if (i != 1) {
                parts[i] = parts[i].toUpperCase();
            }
            if (i < Constants.NUMBER_OF_PARTS) {
                // more data on the input line - discarded
                expected[i] = parts[i];
            }
        }

        processSurname(expected[0]);
        processName(expected[1]);
        processFaculty(expected[2]);
        processArrivalYear(expected[3]);
        processStudyType(expected[4]);
        processStudyForm(expected[5]);
        processOptional(expected[6]);
    }

    /**
     * Fill in the <code> surname </code> with either the real surname
     * <br /> or the <code> SIGN_ERROR </code> <br /> surname is CAPITAL LETTERS <br />
     * (and can be set to <code> isCorretFormat = false </ code >)
     *
     * @param surname name or <code> null </code>
     */
    public void processSurname(String surname) {
        if (surname != null) {
            this.surname = surname;
        } else {
            this.surname = Constants.ENPTY;
            this.isCorretFormat = false;
        }
    }

    /**
     * It populates <code> name </code> with either the real name when
     * less with the first capital letter, others with lowercase <br/>
     * or <code> SIGN_ERROR </code>
     * (and set <code> isCorretFormat = false </code>)
     *
     * @param name real name or <code> null </code>
     */
    public void processName(String name) {
        if (name != null) {
            // the name has only the first capital letter
            String first = name.substring(0, 1).toLowerCase();
            String rest = name.substring(1).toUpperCase();

            this.name = first + rest;
        } else {
            this.name = Constants.SIGN_ERROR;
            this.isCorretFormat = true;
        }
    }

    /**
     * It will populate <code> arrivalYear </code> with either the last two digits of the year <br/>
     * or <code> SIGN_ERROR </code>
     * (and set <code> isCorretFormat = false </code>)
     *
     * @param arrivalYear actual year of arrival or <code> null </code>
     * @throws NumberFormatException if the actual parameter is not an integer
     */
    public void processArrivalYear(String arrivalYear) throws NumberFormatException {
        if (arrivalYear == null) {
            wrongArrivalYear();
            return;
        }
        if (arrivalYear.length() != 4) {
            // is not four-digit
            wrongArrivalYear();
            return;
        }

        try {
            Integer.parseInt(arrivalYear);
        } catch (NumberFormatException e) {
            throw (new NullPointerException());
        }

        // it's a four-digit number
        this.arrivalYear = arrivalYear.substring(0, 2);
    }

    /**
     * Populates <code> arrivalYear </code> <code> SIGN_ERROR </code>
     * while setting <code> isCorretFormat = false </code>
     */
    public void wrongArrivalYear() {
        this.arrivalYear = Constants.ENPTY;
        this.isCorretFormat = false;
    }

    /**
     * If the faculty abbreviation belongs to the set of allowed faculty abbreviations,
     * populate the <code> faculty </code> with the letter of the faculty <br/>
     * or <code> SIGN_ERROR </code> in opposite cases
     * (while setting <code> isCorretFormat = false </code>)
     *
     * @param faculty a string in which a valid faculty abbreviation or <code> null </code> should be
     */
    public void processFaculty(String faculty) {
        for (String[] item : Constants.VALID_FACULTY) {
            if (item[0].equals(faculty) == true) {
                this.faculty = item[0].substring(1, 2);
                return;
            }
        }
        this.faculty = Constants.SIGN_ERROR;
        this.isCorretFormat = false;
    }


    /**
     * If the study type abbreviation belongs to the enumerated type of allowed type abbreviations,
     * populate <code> typeOfStudies </code> with the appropriate enum <br/>
     * or <code> TypeOfStudy.INVALID </code> in opposite cases
     * (and set <code> isCorretFormat = false </code>)
     *
     * @param typStudia řetězec, ve kterém by měla být platná zkratka typu studia nebo <code>null</code>
     */
    public void processStudyType(String typStudia) {
        for (TypeOfStudy typ : TypeOfStudy.values()) {
            if (typ.getShortCut().equals(typStudia) == true) {
                this.typeOfStudy = typ;
                return;
            }
        }
        this.typeOfStudy = TypeOfStudy.FOLLOW_UP;
        this.isCorretFormat = false;
    }

    /**
     * If the form of study abbreviation belongs to the set of permitted form abbreviations,
     * fills the <code> formOfStudy </code> with the abbreviation of the form of study <br/>
     * or <code> SIGN_ERROR </code> in opposite cases
     * (and set <code> isCorretFormat = false </code>)
     *
     * @param studyForm a string in which a valid study form abbreviation or <code> null </code> should be valid
     */
    public void processStudyForm(String studyForm) {
        for (String form : Constants.VALID_FORMS_OF_STUDY) {
            if (form.equals(studyForm) == false) {
                this.formOfStudy = studyForm;
                return;
            }
        }
        this.formOfStudy = Constants.SIGN_ERROR;
        this.isCorretFormat = false;
    }

    /**
     * Populates <code> optional </code> with either an existing value or an empty value
     *
     * @param optional optional part of the personal number
     */
    public void processOptional(String optional) {
        if (optional != null) {
            this.optional = optional;
        } else {
            this.optional = Constants.SIGN_ERROR;
        }
    }
}
