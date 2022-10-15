package org.example;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.chocosolver.solver.search.strategy.Search.minDomLBSearch;
import static org.chocosolver.util.tools.ArrayUtils.append;

public class SudokuPPC {

	static int n;
	static int s;
	private static int instance;
	private static long timeout = 3600000; // one hour

	IntVar[][] rows, cols, shapes;

	Model model;
	ArrayList<ArrayList<Character>> horizontal;
	ArrayList<ArrayList<Character>> vertical;
	public SudokuPPC() throws ParseException {
		final Options options = configParameters();
		final CommandLineParser parser = new DefaultParser();
		final CommandLine line = parser.parse(options, new String[]{""});

		boolean helpMode = line.hasOption("help"); // args.length == 0
		if (helpMode) {
			final HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("sudoku", options, true);
			System.exit(0);
		}
		for (Option opt : line.getOptions()) {
			checkOption(line, opt.getLongOpt());
		}

	}

	public SudokuPPC(int taille) throws ParseException {
		final Options options = configParameters();
		final CommandLineParser parser = new DefaultParser();
		final CommandLine line = parser.parse(options, new String[]{""});

		boolean helpMode = line.hasOption("help"); // args.length == 0
		if (helpMode) {
			final HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("sudoku", options, true);
			System.exit(0);
		}
		instance = taille;
		for (Option opt : line.getOptions()) {
			checkOption(line, opt.getLongOpt());
		}

		n = instance;
		s = (int) Math.sqrt(n);

	}

	public ArrayList<ArrayList<Integer>> ReadCSVContrainte(String path){
		int A = 10;
		int B = 11;
		int C = 12;
		int D = 13;
		int E = 14;
		int F = 15;
		int G = 16;


		ArrayList<ArrayList<Integer>> tableau = new ArrayList<>();
		try
		{
			File file = new File(path);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				ArrayList<Integer> lignes = new ArrayList<>();
				String[] i = line.split(";");
				for (String m :i){
					switch (m.charAt(0)){
						case 'A':
							lignes.add(10);
							break;
						case 'B':
							lignes.add(11);
							break;
						case 'C':
							lignes.add(12);
							break;
						case 'D':
							lignes.add(13);
							break;
						case 'E':
							lignes.add(14);
							break;
						case 'F':
							lignes.add(15);
							break;
						case 'G':
							lignes.add(16);
							break;
						default:
							lignes.add(Integer.parseInt(m));
							break;
					}
				}
				tableau.add(lignes);
			}
			fr.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return tableau;
	}

	public ArrayList<ArrayList<Character>> ReadCSVContrainteGT(String path){
	ArrayList<ArrayList<Character>> tableau = new ArrayList<>();
	try
	{
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine()) != null)
		{
			ArrayList<Character> lignes = new ArrayList<>();
			String[] i = line.split(";");
			for(String c : i){
				lignes.add(c.charAt(0));
			}
			tableau.add(lignes);
		}
		fr.close();
	}
		catch(IOException e)
	{
		e.printStackTrace();
	}

		return tableau;
}

	public void SudokuGT(String path) {
		ArrayList<ArrayList<Character>> tableau = ReadCSVContrainteGT(path);
		this.horizontal = new ArrayList<>();
		this.vertical = new ArrayList<>();
		for(ArrayList<Character> h : tableau){
			if(h.get(0).equals('<') ||h.get(0).equals('>') ||h.get(0).equals('|')  ){
				horizontal.add(h);
			} else if (h.get(0).equals('^') ||h.get(0).equals('v') ||h.get(0).equals('-')) {
				vertical.add(h);
			}
		}
		instance = tableau.get(0).size();
		n = instance;
		s = (int) Math.sqrt(n);
		buildModel();

		for (int i = 0; i < horizontal.size()-1; ++i) {
			for (int j = 0; j < horizontal.get(i).size(); ++j) {
				switch (horizontal.get(i).get(j)) {
					case '<':
						model.arithm(this.rows[i][j], "<", this.rows[i][j+1]).post();
						break;
					case '>':
						model.arithm(this.rows[i][j], ">", this.rows[i][j+1]).post();
						break;
				}
			}
		}
		for (int i = 0; i < vertical.size(); ++i) {
			for (int j = 0; j < vertical.get(i).size(); ++j) {
				switch (vertical.get(i).get(j)) {
					case '^':
						model.arithm(this.rows[i][j], "<", this.rows[i+1][j]).post();
						break;
					case 'v':
						model.arithm(this.rows[i][j], ">", this.rows[i+1][j]).post();
						break;
				}
			}
		}



	}



	public void AddContrainte(String path){
		ArrayList<ArrayList<Integer>> tableau = ReadCSVContrainte(path);
		instance = tableau.size();
		n = instance;
		s = (int) Math.sqrt(n);
		buildModel();
		for(int i = 0; i < tableau.size();++i){
			for(int j = 0; j < tableau.get(i).size();++j){
				if(tableau.get(i).get(j) != 0){
					model.arithm(this.rows[i][j], "=",tableau.get(i).get(j)).post();
				}

			}

		}

	}

	public void solve() {

		//buildModel();

		model.getSolver().solve();

		printGrid();

		model.getSolver().printStatistics();

	}

	public void Allsolve(){
		while(model.getSolver().solve()){
			printGrid();
			model.getSolver().printStatistics();
		}
	}

	public void printGrid() {

		String a;
		a = "┌───";
		String b;
		b = "├───";
		String b1;
		b1 = "├─^─";
		String b2;
		b2 = "├─v─";
		String c;
		c = "─┬────┐";
		String d;
		d = "─┼────┤";
		String d1;
		d1 = "─┼─^──┤";
		String d2;
		d2 = "─┼─v──┤";
		String e;
		e = "─┬───";
		String f;
		f = "─┼───";
		String f1;
		f1 = "─┼─^─";
		String f2;
		f2 = "─┼─v─";
		String g;
		g = "└────┴─";
		String h;
		h = "───┘";
		String k;
		k = "───┴─";
		String esp = " ";
		

		for (int i = 0; i < n; i++) {
			if(vertical !=null){
				for (int line = 0; line < n; line++) {
					if (i == 0) {
						if (line == 0) {
							System.out.print(i == 0 ? a : b1);
						} else if (line == n - 1) {
							System.out.print(i == 0 ? c : d1);
						} else {
							System.out.print(i == 0 ? e : f1);
						}
					} else {
						if (vertical.get(i-1).get(line).equals('^')) {
							if (line == 0) {
								System.out.print(i == 0 ? a : b1);
							} else if (line == n - 1) {
								System.out.print(i == 0 ? c : d1);
							} else {
								System.out.print(i == 0 ? e : f1);
							}
						} else if (vertical.get(i-1).get(line).equals('v')) {
							if (line == 0) {
								System.out.print(i == 0 ? a : b2);
							} else if (line == n - 1) {
								System.out.print(i == 0 ? c : d2);
							} else {
								System.out.print(i == 0 ? e : f2);
							}
						} else if (vertical.get(i-1).get(line).equals('-')) {
							if (line == 0) {
								System.out.print(i == 0 ? a : b);
							} else if (line == n - 1) {
								System.out.print(i == 0 ? c : d);
							} else {
								System.out.print(i == 0 ? e : f);
							}
						}
					}
				}
			}else{
				for (int line = 0; line < n; line++) {
					if (line == 0) {
						System.out.print(i == 0 ? a : b);
					} else if (line == n - 1) {
						System.out.print(i == 0 ? c : d);
					} else {
						System.out.print(i == 0 ? e : f);
					}
				}
			}

			System.out.println("");
			System.out.print("│ ");
			for (int j = 0; j < n; j++) {
				if(horizontal != null &&vertical != null ) {
					if(horizontal.get(i).get(j).equals('<')){
						if (rows[i][j].getValue() > 9) {
							System.out.print(rows[i][j].getValue() + " < ");
						} else {
							System.out.print(esp + rows[i][j].getValue() + " < ");
						}
					}else if(horizontal.get(i).get(j).equals('>')){
						if (rows[i][j].getValue() > 9) {
							System.out.print(rows[i][j].getValue() + " > ");
						} else {
							System.out.print(esp + rows[i][j].getValue() + " > ");
						}
					}else{
						if (rows[i][j].getValue() > 9) {
							System.out.print(rows[i][j].getValue() + " │ ");
						} else {
							System.out.print(esp + rows[i][j].getValue() + " │ ");
						}
					}
				}else{
					if (rows[i][j].getValue() > 9) {
						System.out.print(rows[i][j].getValue() + " │ ");
					} else {
						System.out.print(esp + rows[i][j].getValue() + " │ ");
					}
				}

			}

			if (i == n - 1) {
				System.out.println("");
				for (int line = 0; line < n; line++) {
					System.out.print(line == 0 ? g : (line == n - 1 ? h : k));
				}
			}
			System.out.println("");

		}
	}

	public void buildModel() {
		model = new Model();

		rows = new IntVar[n][n];
		cols = new IntVar[n][n];
		shapes = new IntVar[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				rows[i][j] = model.intVar("c_" + i + "_" + j, 1, n, false);
				cols[j][i] = rows[i][j];
			}
		}

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				for (int k = 0; k < s; k++) {
					for (int l = 0; l < s; l++) {
						shapes[j + k * s][i + (l * s)] = rows[l + k * s][i + j * s];
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			model.allDifferent(rows[i]).post();
			model.allDifferent(cols[i]).post();
			model.allDifferent(shapes[i]).post();

		}

	}

	// Check all parameters values
	public static void checkOption(CommandLine line, String option) {

		switch (option) {
		case "inst":
			instance = Integer.parseInt(line.getOptionValue(option));
			break;
		case "timeout":
			timeout = Long.parseLong(line.getOptionValue(option));
			break;
		default: {
			System.err.println("Bad parameter: " + option);
			System.exit(2);
		}

		}

	}

	// Add options here
	private static Options configParameters() {

		final Option helpFileOption = Option.builder("h").longOpt("help").desc("Display help message").build();

		final Option instOption = Option.builder("i").longOpt("instance").hasArg(true).argName("sudoku instance")
				.desc("sudoku instance size").required(false).build();

		final Option limitOption = Option.builder("t").longOpt("timeout").hasArg(true).argName("timeout in ms")
				.desc("Set the timeout limit to the specified time").required(false).build();

		// Create the options list
		final Options options = new Options();
		options.addOption(instOption);
		options.addOption(limitOption);
		options.addOption(helpFileOption);

		return options;
	}

	public void configureSearch() {
		model.getSolver().setSearch(minDomLBSearch(append(rows)));

	}

}
