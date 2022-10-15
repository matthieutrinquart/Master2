package org.example.CLI;

import org.apache.commons.cli.ParseException;
import org.example.SudokuBT;
import org.example.SudokuPPC;
import org.example.TestSudoku;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceCLI {

    private ArrayList<String> choice ;
    public InterfaceCLI(){
        choice = new ArrayList<>();
        choice.add("0) Exit");
        choice.add("1) Question 6 résultat backtrack pour un sudoku 4*4");
        choice.add("2) Question 7 Test unitaire de PPC et du backtrack pour une solution (peut-être assez long)");
        choice.add("3) Question 8 Afficher toutes les solutions du sudoku 4*4 en PPC");
        choice.add("4) Question 8 Afficher toutes les solutions du sudoku 4*4 en BT");
        choice.add("5) Question 9 Test unitaire de PPC et du backtrack pour toutes les solutions (peut-être assez long)");
        choice.add("6) Question 10 résultat de PPC sur la figure 2");
        choice.add("7) Question 11 résultat de PPC sur la figure 3");
        choice.add("8) Question 12 résultat de PPC sur la figure 4");
        choice.add("9)  résultat de PPC sur une figure passé en paramètre");
        choice.add("10) résultat de PPC sur une figure Greater Than Sudoku passé en paramètre");
        choice.add("11) Afficher tous les exercices d'un coup (peut-être assez long)");
    }

    public void AfficherCLI(){
        for (String m : choice){
            System.out.println(m);
        }
        System.out.println();
        System.out.println("Selectionner l'exercice à exécuter : ");
    }

    public void ExecutionCLI() throws ParseException {
        Scanner scan = new Scanner(System.in);
        String charReaded = "";
        while(!charReaded.equals("0")){
            AfficherCLI();
            charReaded = scan.next();
            switch (charReaded){
                case "1" :
                    SudokuBT BT = new SudokuBT(4);
                    BT.findSolution(0,0);
                    break;
                case "2" :
                    TestSudoku t = new TestSudoku();
                    t.TimeForSize4();
                    //t.TimeForSize9();
                    break;
                case "3" :
                    SudokuPPC PPC = new SudokuPPC(4);
                    PPC.buildModel();
                    PPC.Allsolve();
                    break;
                case "4" :
                    BT = new SudokuBT(4);
                    BT.findSolutionAll(0,0);
                    break;
                case "5" :
                    t = new TestSudoku();
                    t.AllSolutionTimeForSize4();
                    //t.AllSolutionTimeForSize9();
                    break;
                case "6" :
                    PPC = new SudokuPPC();
                    PPC.AddContrainte("src\\main\\Figure\\Figure2.csv");
                    PPC.solve();
                    break;
                case "7" :
                    PPC = new SudokuPPC();
                    PPC.AddContrainte("src\\main\\Figure\\Figure3.csv");
                    PPC.solve();
                    break;
                case "8" :
                    PPC = new SudokuPPC();
                    PPC.buildModel();
                    PPC.SudokuGT("src\\main\\Figure\\Figure4.csv");
                    PPC.solve();
                    break;
                case "9" :
                    System.out.println("Donner le lien du fichier csv représentant la figure");
                    charReaded = scan.next();
                    PPC = new SudokuPPC();
                    PPC.buildModel();
                    PPC.AddContrainte(charReaded);
                    PPC.solve();
                    break;
                case "10" :
                    System.out.println("Donner le lien du fichier csv représentant la figure Greater Than Sudoku");
                    charReaded = scan.next();
                    PPC = new SudokuPPC();
                    PPC.buildModel();
                    PPC.SudokuGT(charReaded);
                    PPC.solve();
                    break;
                case "11" :
                    t = new TestSudoku();
                    BT = new SudokuBT(4);
                    System.out.println("----------Exo6----------");
                    BT.findSolution(0,0);
                    System.out.println("----------Exo7----------");
                    t.TimeForSize4();
                    System.out.println("----------Exo8----------");
                    System.out.println("PPC = ");
                    PPC = new SudokuPPC(4);
                    PPC.buildModel();
                    PPC.Allsolve();
                    System.out.println("BT = ");
                    BT.findSolutionAll(0,0);
                    System.out.println("----------Exo9----------");
                    t.AllSolutionTimeForSize4();

                    System.out.println("----------Exo10----------");
                    PPC = new SudokuPPC();
                    PPC.buildModel();
                    PPC.AddContrainte("src\\main\\Figure\\Figure2.csv");
                    PPC.solve();

                    System.out.println("----------Exo11----------");
                    PPC.AddContrainte("src\\main\\Figure\\Figure3.csv");
                    PPC.solve();


                    System.out.println("----------Exo12----------");
                    SudokuPPC PPC2 = new SudokuPPC();
                    PPC2.SudokuGT("src\\main\\Figure\\Figure4.csv");
                    PPC2.solve();
                    break;
                case "0":
                    System.out.println("Au revoir");
                    break;
                default :
                    System.out.println("erreur");
                    break;
            }
        }

    }

}
