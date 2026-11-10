package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    
    // ===== B: бүх салаа (branch) 100% хамрах тестүүд =====

    // letterGrade
    @Test
    void letterGrade_75_isC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGrade_65_isD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGrade_50_isF() {
        assertEquals("F", calc.letterGrade(50));
    }

    @Test
    void letterGrade_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGrade_above100_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    // gpa
    @Test
    void gpa_loop0_nullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpa_loop0_emptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[] {}));
    }

    @Test
    void gpa_allGradeLetters_is2_0() {
        // A=4, B=3, C=2, D=1, F=0 -> (4+3+2+1+0)/5 = 2.0
        assertEquals(2.0, calc.gpa(new double[] {95, 85, 75, 65, 50}), 0.0001);
    }

    // scholarship
    @Test
    void scholarship_gpaBelowZero_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
    }

    @Test
    void scholarship_gpaAboveFour_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
    }

    @Test
    void scholarship_negativeCredits_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.5, -1, false));
    }

    @Test
    void scholarship_violation_returns0() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    @Test
    void scholarship_highGpaLowCredits_returns70() {
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarship_midGpaLowCredits_returns50() {
        assertEquals(50, calc.scholarship(3.5, 11, false));
    }

    @Test
    void scholarship_basic_returns50() {
        assertEquals(50, calc.scholarship(3.2, 5, false));
    }

    @Test
    void scholarship_low_returns0() {
        assertEquals(0, calc.scholarship(2.9, 20, false));
    }
    
    // ===== C: давталтын заагийн тестүүд (0 / 1 / 2+) =====
    // 0 удаа: дээрх gpa_loop0_nullThrows ба gpa_loop0_emptyThrows

    @Test
    void gpa_loop1_singleScore_is4_0() {
        assertEquals(4.0, calc.gpa(new double[] {95}), 0.0001);
    }

    @Test
    void gpa_loop2plus_manyScores_is2_5() {
        assertEquals(2.5, calc.gpa(new double[] {95, 85, 75, 65}), 0.0001);
    }
    
    // ===== D: зааг утгын тестүүд =====

    @Test
    void letterGrade_boundary60_isD() {
        assertEquals("D", calc.letterGrade(60));
    }

    @Test
    void letterGrade_boundary59_99_isF() {
        assertEquals("F", calc.letterGrade(59.99));
    }

    @Test
    void letterGrade_boundary69_99_isD() {
        assertEquals("D", calc.letterGrade(69.99));
    }

    @Test
    void letterGrade_boundary0_isF() {
        assertEquals("F", calc.letterGrade(0));
    }

    @Test
    void letterGrade_boundary100_isA() {
        assertEquals("A", calc.letterGrade(100));
    }

    @Test
    void letterGrade_boundary89_99_isB() {
        assertEquals("B", calc.letterGrade(89.99));
    }

    @Test
    void letterGrade_boundary90_isA() {
        assertEquals("A", calc.letterGrade(90));
    }
}