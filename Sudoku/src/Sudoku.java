



public class Sudoku {
	public static void main(String[]args)
	{
		char[][] a = {	{'F','H','E',	'D','B','C',	'G','A','I'},
						{'G','I','C',	'A','F','E',	'D','H','B'},
						{'D','A','B',	'H','I','G',	'F','C','E'},
						
						{'I','E','G',	'B','A',0,	     'C','F','H'},
						{'C','D','A',	'F','E','H',	'B','I','G'},
						{'H','B','F',	'G','C','I',	'A','E','D'},
						
						{'B','F','D',	'I','H',0,	     'E','G','C'},
						{'E','G',0,  	'C','D','F',	'H','B','A'},
						{'A','C','H',	'E','G',0,		0,'D',0}};
		solve(a);
		for(int m = 0; m<9;m++)
		{
			for(int z = 0; z < 9; z++)
			{
				System.out.print(a[m][z]+" ");
			}
			System.out.println();
		}
		
	}
	public static boolean isPermutation(char[] a) 
	{
		boolean[] test = new boolean[a.length];
		for(int i = 0; i < a.length; i++)
			test[i] = false;
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] != 0)
			{
				if(test[(int)(a[i]-'A')]==true)
				{
					return false;
				}
				test[(int)(a[i]-'A')]=true;
			}
		}
		return true;
	}
	//Überprüft unsere Permutationsbedingung für das Array a, Die Buchstaben A bis I werden durch die chars 'A' bis 'I' dargestellt, die 0 durch den char 0. 
	public static boolean isPermutationRow(char[][] a, int row) 
	{
		return isPermutation(a[row]);
	}
	//Überprüft, ob Reihe row im zweidimensionalen Array a eine Permutationsmenge ist.
	public static boolean isPermutationCol(char[][] a, int col)
	{
		char[] b = new char[a.length];
		for(int i = 0; i < a.length; i++)
		{
			b[i]=a[i][col];
		}
		return isPermutation(b);
	}
	// Überprüft, ob Spalte col im zweidimensionalen Array a eine Permutationsmenge ist.
	public static boolean isPermutationMatrix(char[][] a)
	{
		for(int i = 0; i < 9; i++)
		{
			if(!(isPermutationRow(a,i)&&isPermutationCol(a,i)))
			{
				return false;
			}
		}
		
		return true;
	}
	// Überprüft, ob die Matrix, dargestellt durch das zweidimensionale Array a, eine Permutationsmatrix ist.
	public static boolean isPermutationBlock(char[][] a, int minRow, int maxRow, int minCol, int maxCol)
	{
		char[] b = new char[9];
		int z = minRow;
		for(int i = 0; i < 9; i++)
		{
			if(z<maxRow-1)
			{
				b[i]=a[z][minCol];
				z++;
			}
			else
			{
				b[i]=a[z][minCol];
				z = minRow;
				minCol++;
			}
		}
		return isPermutation(b);
	}
	// Überprüft, ob die Menge aller Zahlen im zweidimensionalen Block zwischen den Reihen minRow (inklusive) und maxRow (exklusive) und den Spalten minCol (inklusive) und maxCol (exklusive) eine Permutationsmenge (nicht nur eine Permutationsmatrix) darstellt.
	public static boolean isValid(char[][] a)
	{
		if(isPermutationMatrix(a))
		{
			for(int i = 0; i < 3; i++)
			{
				for(int z = 0; z < 3; z++)
				{
					if(!isPermutationBlock(a, z*3, (z+1)*3,i*3, (i+1)*3))
					{
						return false;
					}
				}
			}
		}
		else
			return false;
		return true;
	}
	// Überprüft, ob das Sudokufeld alle Eigenschaften (s.o.) erfüllt.
	public static char[][] solve(char[][] a)
	{
		int[] b = {0,0};
		int x = erstesFreies(a,b)[0];
		int y = erstesFreies(a,b)[1];
		if(solve1(a,x,y))
			return a;
		else
			return null;
		
	}
	public static boolean solve1(char[][] a, int x, int y)
	{
		for(char i = 'A'; i <= 'I'; i++)
		{
			a[x][y]=i;
			if(isValid(a))
			{	
				if(x==8 && y == 8 && a[8][8]!=0)
				{
					return true;
				}
				int[] b = {x,y+1};
				int xtmp=erstesFreies(a,b)[0], ytmp=erstesFreies(a,b)[1];
				if(solve1(a,xtmp,ytmp))
					return true;
				a[xtmp][ytmp]=0;
				for(int m = 0; m<9;m++)
				{
					for(int z = 0; z < 9; z++)
					{
						System.out.print(a[m][z]+" ");
					}
					System.out.println();
				}System.out.println(x+""+y);
			}
			
		}
		return false;
	}
	public static int[] erstesFreies(char[][] a,int[] b)
	{
		if(b[1]==9)
		{
			b[0]=b[0]+1;
			b[1]=0;
		}
		int z = b[1];
		for(int i = b[0]; i < 9; i++)
		{
			while(z < 9)
			{

				if(a[i][z]==0)
				{
					int[] c = {i,z};
					return c;
				}
				else
					z++;
			}
			z = 0;
		}
		int[] c = {8,8};
		return c;
	}
	//Berechnet eine Lösung für das Sudokufeld a. Liefert  diese zurück, falls sie existiert. Liefert sonst null zurück.

}
