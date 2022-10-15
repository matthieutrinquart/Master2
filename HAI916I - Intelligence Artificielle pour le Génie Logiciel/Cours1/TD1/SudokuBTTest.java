import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

// taille n du tableau, notion de temps, nombre de solution trouver par algo

//premier qui arrive a 100 solutions
//celui qui trouve le plus de solution
//changer la taille du tableau
class SudokuBTTest {


    @Test
    void tempsPPC(){
        SudokuBT BT = new SudokuBT(4);
        long startTime = System.currentTimeMillis();

        BT.findSolution(0,0);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

        SudokuPPC PPC = new SudokuPPC();
        PPC.setN(4);
         startTime = System.currentTimeMillis();

        PPC.solve();
         endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);


    }
    @Test
    void findSolution() {
    }

    @Test
    void findSolutionAll() {
    }

    @Test
    void testToString() {
    }

    @Test
    void main() {
        tempsPPC();
    }
}