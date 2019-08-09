package oks;

import com.sun.org.apache.xpath.internal.operations.Number;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalNumberTest {

    @BeforeEach
    void setUp(){
        oc = new PersonalNumber("Novák, Josef, fav, 2014, b, 0123, p, i");
    }

    PersonalNumber oc;

    @Test
    void testGetPersonNumber() {
        assertEquals("A14B0123Pi", oc.getPersonNumber());
    }

    @Test
    void testIsValidPersonNumberValid() {
        oc.isCorretFormat = true;
        assertTrue(oc.isValidPersonNumber());

    }

    @Test
    void testIsValidPersonNumberInvalid() {
        oc.isCorretFormat = false;
        assertFalse(oc.isValidPersonNumber());
    }

    @Test
    void testIsCorrectFormatValid() {
        oc.isCorretFormat = false;
        assertFalse(oc.isCorretFormat());
    }

    @Test
    void testIsCorrectFormatInvalid() {
        oc.isCorretFormat = true;
        assertTrue(oc.isCorretFormat());
    }

    @Test
    void testGetTypeOfStudy() {
        assertEquals(TypeOfStudy.BACHELOR, oc.getTypeOfStudy());
    }

    @Test
    void testGetFaculty() {
        assertEquals("A", oc.getFaculty());
    }

    @Test
    void testProcessSurname() {
        oc.processSurname("novák");
        assertEquals("Novák", oc.surname);
    }

    @Test
    void testProcessSurnameNull() {
        oc.processSurname(null);
        assertEquals(Constants.SIGN_ERROR, oc.surname);
        assertFalse(oc.isCorretFormat);
    }

    @Test
    void testProcessName() {
        oc.processName("Josef");
        assertEquals("Josef",  oc.name);
    }

    @Test
    void testProcessNameNull() {
        oc.processName(null);
        assertEquals(Constants.SIGN_ERROR, oc.name);
        assertFalse(oc.isCorretFormat);
    }

    @Test
    void testProcessArrivalYear() {
        oc.processArrivalYear("2014");
        assertEquals("14", oc.arrivalYear);
    }

    @Test
    void testProcessArrivalYearNull() {
        oc.isCorretFormat = true;
        oc.processArrivalYear(null);
        assertEquals(Constants.SIGN_ERROR, oc.arrivalYear);
        assertFalse(oc.isCorretFormat);
    }

    @Test
    void testProcessArrivalYearException() {
        assertThrows(NumberFormatException.class, () -> {
            oc.processArrivalYear("twenty");
        });
    }

    @Test
    void testWrongArrivalYearConstant() {
        oc.wrongArrivalYear();
        assertEquals(Constants.SIGN_ERROR, oc.arrivalYear);
    }

    @Test
    void testWrongArrivalYearCorrectFormatBoolean() {
        oc.wrongArrivalYear();
        assertFalse(oc.isCorretFormat);
    }

    @Test
    void testProcessFaculty() {
        oc.processFaculty("fav");
        assertEquals("A", oc.faculty);
    }

    @Test
    void testProcessStudyType() {
        oc.processStudyType("b");
        assertEquals("B", oc.typeOfStudy);
    }

    @Test
    void testProcessStudyForm() {
        oc.processStudyForm("p");
        assertEquals("P", oc.formOfStudy);

    }

    @Test
    void testProcessOptional() {
        oc.processOptional("i");
        assertEquals("i", oc.optional);
    }
}