//Vasiliki Katogianni A.M. 4696 Zoi Kouvaka A.M. 4706 Athanasios Katsilieris A.M. 3247
import java.lang.Math;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

class sortprogram //reads data runs gradient_descent and test the neural network
{
	public static void main(String[] args) {
		Mlp mlp=new Mlp();
		BufferedReader reader =null;
		try
		{

			reader = new BufferedReader(new FileReader("datatest.txt")); //reads datatest.txt file which contains the examples
		}
		catch(FileNotFoundException e) //if file is not found
		{
			System.out.println("File was not found");
			System.out.println("or could not be opened.");
			System.exit(0);
		}
		try
		{
			String line = reader.readLine();
			double[][] arr = new double[8000][2]; //array which contains all the examples x1,x2
			double[] category=new double[8000]; //array which contains all the categories of the examples
			int u=0; //counter 
			int v=0; //temp variable
			int l=0;  //temp variable
			while (line != null && u<8000) { //read every time an example and its category from file
            
				String[] split = line.split(","); 
				
				double x = Double.parseDouble(split[0]);
				double y = Double.parseDouble(split[1]);
				double z = Double.parseDouble(split[2]);
				arr[u][v]=x; //keeps x1
				v++;
				arr[u][v]=y; //keeps x2
				v=0; 
				u++;
				category[l]=z; //keeps category
				l++;
				line = reader.readLine(); //goes to next line
			}
			reader.close();
			mlp.gradient_descent(arr,category);
			System.out.println();
			mlp.testExamples(4000,4000,arr,category);
			System.out.println();
			System.out.println("Percentage of right decisions : "+(mlp.getpososto()*100)/4000+"%");
		}
		catch(IOException e)
		{
			System.out.println("IOException");
			System.exit(0);
		}
	}	
}