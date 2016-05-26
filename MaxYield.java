import java.io.*;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Files;



public class MaxYield {
	
	//public static int N = 4, M = 3;
	public static int N = 5;
	public static int M = 2;

	public static Integer[][] DilithiumStrips; 
	public static String[] strOptimumDilithiumStrips; 
	public static int TotalYield = 0;
	
	public static void main (String [] args){
	    
		init();
		
		printMineInputs();
		processDataForMaxYields();
		printOptimalSquare();
		
	  } //main	

	public static void init() {
		
		//DilithiumStrips = new Integer[N][2];
		
		/*// testing example 1
		DilithiumStrips[0][0] = 206; DilithiumStrips[0][1] = 1; 
		DilithiumStrips[1][0] = 140; DilithiumStrips[1][1] = 2;
		DilithiumStrips[2][0] = 300; DilithiumStrips[2][1] = 3;
		DilithiumStrips[3][0] = 52; DilithiumStrips[3][1] = 4;
		DilithiumStrips[4][0] = 107; DilithiumStrips[4][1] = 5;*/

		// testing example 2
		/*DilithiumStrips[0][0] = 147; DilithiumStrips[0][1] = 1; 
		DilithiumStrips[1][0] = 206; DilithiumStrips[1][1] = 2;
		DilithiumStrips[2][0] = 52; DilithiumStrips[2][1] = 3;
		DilithiumStrips[3][0] = 240; DilithiumStrips[3][1] = 4;
		DilithiumStrips[4][0] = 300; DilithiumStrips[4][1] = 5;*/

		
		
		//String inpStr = "[ 206, 140, 300, 52, 107 ]";
		//String inpStr = "[ 147, 206, 52, 240, 300 ]";
		
		String sInpFile = "DilStrips.in";
		String inpStr = "";

		try {
			inpStr = new String(Files.readAllBytes(Paths.get(sInpFile)));
			System.out.println("inpStr = " + inpStr );
		} catch (FileNotFoundException fnoe) {
        	System.out.println(" file not found or not readable" );
			fnoe.printStackTrace();
			System.exit(1);
		} catch (IOException ioe){
        	System.out.println(" IOException" );
			ioe.printStackTrace();
			System.exit(1);
			
		}
		String findStr = ",";
		N = inpStr.split(findStr, -1).length;
		System.out.println(" N = " + N );
		
		DilithiumStrips = new Integer[N][2];
		
		String[] inpDatStr = inpStr.split(",");
		inpDatStr[0] = inpDatStr[0].substring(1).trim();
		inpDatStr[N-1] = inpDatStr[N-1].substring(0, inpDatStr[N-1].length()-1).trim();
		for (int i = 0; i < inpDatStr.length; i++) {
			DilithiumStrips[i][0] = Integer.parseInt(inpDatStr[i].trim());
			DilithiumStrips[i][1] = i + 1;
			System.out.println( " DilithiumStrips[i][0] = " + DilithiumStrips[i][0] + " DilithiumStrips[i][1] = " + DilithiumStrips[i][1]);
		}
		
		
	}//init

	public static void processDataForMaxYields() {
		
		for (int i = 1; i <= N; i++) {
			if ( DilithiumStrips[i-1][1] < 0 ) continue;
			TotalYield += DilithiumStrips[i-1][0];
			// now invalidate the adjacent squares
		
			for (int j = 1; j <= N; j++) {
				System.out.println(" j = " + j + " DilithiumStrips[j-1][1] = " + DilithiumStrips[j-1][1]);
				if ( DilithiumStrips[j-1][1] == DilithiumStrips[i-1][1]-1 && DilithiumStrips[j-1][1] > 0) {
					//set this to -ve value 
					DilithiumStrips[j-1][1] = -1 * DilithiumStrips[j-1][1];
				} else if ( DilithiumStrips[j-1][1] == DilithiumStrips[i-1][1]+1 && DilithiumStrips[j-1][1] > 0 ) {
					//set this to -ve value 
					DilithiumStrips[j-1][1] = -1 * DilithiumStrips[j-1][1];
				}
			}
			System.out.println("  i = " + i + " DilithiumStrips[i-1][0] = " + DilithiumStrips[i-1][0] + " DilithiumStrips[i-1][1] = " + DilithiumStrips[i-1][1] +
					" TotalYield =  " + TotalYield);

			
		}

		System.out.println(" Mine data after processing\n" );
		for (int i = 0; i < N; i++) { 
			System.out.println(" dilithium qty = " + DilithiumStrips[i][0] + " square # = " + DilithiumStrips[i][1] );
		}

		System.out.println(" \n\n*****  TotalYield = " + TotalYield  + " *********");

		
 	} ///processDataForMaxYields
 

	public static void printMineInputs() {
		
		System.out.println(" Mine data before sort\n" );
		for (int i = 0; i < N; i++) { 
			System.out.println(" dilithium qty = " + DilithiumStrips[i][0] + " square # = " + DilithiumStrips[i][1] );
		}

		Arrays.sort(DilithiumStrips, new ColumnComparator(0, SortingOrder.DESCENDING));		
		System.out.println(" Mine data after sort\n" );
		for (int i = 0; i < N; i++) { 
			System.out.println(" dilithium qty = " + DilithiumStrips[i][0] + " square # = " + DilithiumStrips[i][1] );
		}

	}//print..

	
	public static void printOptimalSquare() {
		
		strOptimumDilithiumStrips = new String[N];
		for (int i = 0; i < N; i++) { 
			if ( DilithiumStrips[i][1] < 0 ) {
				strOptimumDilithiumStrips[-1*DilithiumStrips[i][1]-1] = "x";
			} else {
				strOptimumDilithiumStrips[DilithiumStrips[i][1]-1] = DilithiumStrips[i][0].toString();

			}
		}

		String sOut = "[" + strOptimumDilithiumStrips[0];
	
		System.out.println(" Output with optimal squares \n" );
		for (int i = 1; i < N; i++) { 
			sOut += "," +  strOptimumDilithiumStrips[i];
		}
		sOut += "]";
		System.out.println(sOut);
		
		// write to output file
		PrintWriter pw = null;
		 try {
			 pw = new PrintWriter(new BufferedWriter(new FileWriter("DilStrips.out")));
			 pw.write("Maximum Yield = " + TotalYield + "\n");
			 pw.write(sOut);
		 } catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
		}
		pw.close();


	}//printOptimalSquare
		
	

}


