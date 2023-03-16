//Vasiliki Katogianni A.M. 4696 Zoi Kouvaka A.M. 4706 Athanasios Katsilieris A.M. 3247
import java.util.Random;
class generateData
{
	
	public static void main(String[] args) {
		int i=0;
		Random rand=new Random();
		double x1;
		double x2;
		while(i<8000)
		{
			int x1temp=rand.nextInt(200)+1-100; //generate random doubles in range (-100,100)
			int x2temp=rand.nextInt(200)+1-100;
			x1=(double)x1temp/100; //and now in (-1,1)
			x2=(double)x2temp/100;
			if(((x1-0.5)*(x1-0.5)+(x2-0.5)*(x2-0.5)<0.2 && x2>0.5)||((x1+0.5)*(x1+0.5)+(x2+0.5)*(x2+0.5)<0.2 && x2>-0.5)||((x1-0.5)*(x1-0.5)+(x2+0.5)*(x2+0.5)<0.2 && x2>-0.5)||((x1+0.5)*(x1+0.5)+(x2-0.5)*(x2-0.5)<0.2 && x2>0.5))
			{
				System.out.println(""+x1+","+x2+","+"1"); //is of C1 category
			}
			else if(((x1-0.5)*(x1-0.5)+(x2-0.5)*(x2-0.5)<0.2 && x2<0.5)||((x1+0.5)*(x1+0.5)+(x2+0.5)*(x2+0.5)<0.2 && x2<-0.5)||((x1-0.5)*(x1-0.5)+(x2+0.5)*(x2+0.5)<0.2 && x2<-0.5)||((x1+0.5)*(x1+0.5)+(x2-0.5)*(x2-0.5)<0.2 && x2<0.5))
			{
				System.out.println(""+x1+","+x2+","+"2"); // is of C2 category
			}
			else
			{
				System.out.println(""+x1+","+x2+","+"3"); //is of C3 category
			}
			i++;
		}
	}
}