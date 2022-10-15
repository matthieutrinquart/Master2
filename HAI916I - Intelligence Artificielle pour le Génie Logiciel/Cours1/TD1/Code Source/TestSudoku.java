package org.example;

import org.apache.commons.cli.ParseException;
import org.example.SudokuBT;
import org.example.SudokuPPC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestSudoku {


    SudokuPPC PPC;
    SudokuBT BT;

    @Test
    public void TimeForSize4() throws ParseException {
        long start = System.currentTimeMillis();
        PPC = new SudokuPPC(4);
        PPC.buildModel();
        PPC.solve();
        float timePPC = System.currentTimeMillis() - start;


        start = System.currentTimeMillis();
        BT = new SudokuBT(4);
        BT.findSolution(0,0);
        float timeBT = System.currentTimeMillis() - start;


        if(timePPC < timeBT){
            System.out.println("BT prend plus de temps pour un Sudoku de taille 4 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else if(timePPC > timeBT){
            System.out.println("PPC prend plus de temps pour un Sudoku de taille 4 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else{
            System.out.println("PPC et BT ont mit le même temps pour un Sudoku de taille 4 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }
    }

    @Test
    public void TimeForSize9() throws ParseException {
        long start = System.currentTimeMillis();
        PPC = new SudokuPPC(9);
        PPC.buildModel();
        PPC.solve();
        float timePPC = System.currentTimeMillis() - start;


        start = System.currentTimeMillis();
        BT = new SudokuBT(9);
        BT.findSolution(0,0);
        float timeBT = System.currentTimeMillis() - start;


        if(timePPC < timeBT){
            System.out.println("BT prend plus de temps pour un Sudoku de taille 9 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else if(timePPC > timeBT){
            System.out.println("PPC prend plus de temps pour un Sudoku de taille 9 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else{
            System.out.println("PPC et BT ont mit le même temps pour un Sudoku de taille 9 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }
    }


    @Test
    public void TimeForSize16() throws ParseException {
        long start = System.currentTimeMillis();
        PPC = new SudokuPPC(16);
        PPC.buildModel();
        PPC.solve();
        float timePPC = System.currentTimeMillis() - start;


        start = System.currentTimeMillis();
        BT = new SudokuBT(16);
        BT.findSolution(0,0);
        float timeBT = System.currentTimeMillis() - start;


        if(timePPC < timeBT){
            System.out.println("BT prend plus de temps pour un Sudoku de taille 16 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else if(timePPC > timeBT){
            System.out.println("PPC prend plus de temps pour un Sudoku de taille 16 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else{
            System.out.println("PPC et BT ont mit le même pour un Sudoku de taille 16 temps avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }
    }

    @Test
    public void AllSolutionTimeForSize4() throws ParseException {
        long start = System.currentTimeMillis();
        PPC = new SudokuPPC(4);
        PPC.buildModel();
        PPC.Allsolve();
        float timePPC = System.currentTimeMillis() - start;


        start = System.currentTimeMillis();
        BT = new SudokuBT(4);
        BT.findSolutionAll(0,0);
        float timeBT = System.currentTimeMillis() - start;


        if(timePPC < timeBT){
            System.out.println("BT prend plus de temps pour un Sudoku de taille 4 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else if(timePPC > timeBT){
            System.out.println("PPC prend plus de temps pour un Sudoku de taille 4 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else{
            System.out.println("PPC et BT ont mit le même pour un Sudoku de taille 4 temps avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }
    }

    @Test
    public void AllSolutionTimeForSize9() throws ParseException {
        long start = System.currentTimeMillis();
        PPC = new SudokuPPC(9);
        PPC.buildModel();
        PPC.Allsolve();
        float timePPC = System.currentTimeMillis() - start;


        start = System.currentTimeMillis();
        BT = new SudokuBT(9);
        BT.findSolutionAll(0,0);
        float timeBT = System.currentTimeMillis() - start;


        if(timePPC < timeBT){
            System.out.println("BT prend plus de temps pour un Sudoku de taille 9 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else if(timePPC > timeBT){
            System.out.println("PPC prend plus de temps pour un Sudoku de taille 9 avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }else{
            System.out.println("PPC et BT ont mit le même pour un Sudoku de taille 9 temps avec comme temps : ");
            System.out.println("PPC : " + timePPC);
            System.out.println("BT : " + timeBT);
        }
    }


}
