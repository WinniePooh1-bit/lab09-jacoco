package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ===== A: хэвийн урсгалын (happy path) тестүүд =====

    @Test
    void letterGrade_95_isA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void letterGrade_85_isB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void gpa_twoScores_is3_5() {
        assertEquals(3.5, calc.gpa(new double[] {95, 85}), 0.0001);
    }

    @Test
    void scholarship_top_returns100() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship_mid_returns70() {
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }
}