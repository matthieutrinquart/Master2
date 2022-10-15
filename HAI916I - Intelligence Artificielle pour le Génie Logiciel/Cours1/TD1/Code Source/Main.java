package org.example;

import org.apache.commons.cli.ParseException;
import org.example.CLI.InterfaceCLI;

public class Main {
    public static void main(String[] args) throws ParseException {

        InterfaceCLI cli = new InterfaceCLI();
        cli.ExecutionCLI();

    }
}