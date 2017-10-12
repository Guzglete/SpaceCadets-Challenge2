import java.io.*;

public class Challenge2
{
	static String []variables = new String[20];
	static int []values = new int[20];
	static int numberOfVariables=0;
	static String []code = new String [300];
	static int nOfLines=0;
	
	public static void main (String [] args)
	{
		try
		{
			FileReader fileReader = new FileReader("barebones.txt");
			BufferedReader bReader = new BufferedReader (fileReader);
			
			try 
			{
				String line = bReader.readLine();
				while (line != null)
				{	
					code[++nOfLines]=line;
					line=bReader.readLine();
				}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			resolve (1,nOfLines);
			for (int i=1;i<=numberOfVariables;i++)
				System.out.println (variables[i]+"="+values[i]);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Unable to open file 'barebones.txt'");
		}
	}
	
	static void resolve (int index1, int index2)
	{
		for (int i=index1;i<=index2;i++)
		{
			if (code[i].contains("clear"))
			{
				String name = code[i].substring(code[i].indexOf("clear") + 6,code[i].indexOf(";"));
				clear (name);
			}
			else if (code[i].contains("incr"))
			{
				String name =  code[i].substring(code[i].indexOf("incr") + 5,code[i].indexOf(";"));
				incr (name);
			}
			else if (code[i].contains("decr"))
			{
				String name = code[i].substring(code[i].indexOf("decr") + 5,code[i].indexOf(";"));
				decr (name);
			}
			else if (code[i].contains ("while"))
			{
				int pos=i;
				while (code[pos].contains ("end") == false || code[i].indexOf("w") != code[pos].indexOf("end"))
					pos++;
				String name=code[i].substring(code[i].indexOf("while")+6, code[i].indexOf("not 0")-1);
				int p=1;
				while (!variables[p].equals(name))
					p++;
				while (values[p] != 0)
					resolve (i+1, pos-1);
				i=pos;
			}
		}
	}
	
	static void clear (String v)
	{
		int found=0;
		for (int i=1;i<=numberOfVariables;i++)
		{
			if (variables[i].equals(v))
			{
				found=1;
				values[i]=0;
				i=numberOfVariables+1;
			}
		}
		if (found==0)
		{
			numberOfVariables++;
			variables[numberOfVariables]=v;
			values[numberOfVariables]=0;
		}
	}
	
	static void incr (String v)
	{
		for (int i=1;i<=numberOfVariables;i++)
		{
			if (variables[i].equals(v))
			{
				values[i]++;
				break;
			}
		}
	}
	
	static void decr (String v)
	{
		for (int i=1;i<=numberOfVariables;i++)
			if (variables[i].equals(v))
			{
				values[i]--;;
				break;
			}
	}
}
