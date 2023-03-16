//Vasiliki Katogianni A.M. 4696 Zoi Kouvaka A.M. 4706 Athanasios Katsilieris A.M. 3247
import java.lang.Math;
import java.util.Random;
class Neuron
{
	private int d=2; //number of input neurons
	public int K=3;  //number of categories
	public int H1=2; //number of neurons of first hidden layer
	public int H2=2;  //number of neurons of second hidden layer
	public int H3=2; //number of neurons of third hidden layer
	private double[] weights; //array of the weights of neuron from previous layer neurons, position 0 is bias 
	private double output; // output of neuron used only for input neurons
	private double n;// learning rate
	private int layer;//0->input,1->H1,2->H2,3->H3,4->output
	private double[] paragogoi; //pinakas me tous merikous paragwgous tou neurona
	private double[] paragogoi1; //pinakas me ta merika athroismata
	public Neuron(double n,int layer) //constructor of neuron
	{
		Random rand=new Random();
		this.n=n;
		this.layer=layer;
		if(layer==1){ //if neuron is input neuron 
			this.weights=new double[(d+1)]; //initialize arrays
			this.paragogoi=new double[d+1];
			this.paragogoi1=new double[d+1];
			for(int i=0;i<(d+1);i++){
				this.weights[i]=rand.nextDouble()*(2)-1; //weights are random numbers between (-1,1)
			}
		}else if(layer==2){ //if neuron is neuron in first hidden layer
			this.weights=new double[H1+1]; //initialize arrays
			this.paragogoi=new double[H1+1];
			this.paragogoi1=new double[H1+1];
			for(int i=0;i<(H1+1);i++){
				this.weights[i]=rand.nextDouble()*(2)-1; //weights are random numbers between (-1,1)
			}
		}else if(layer==3){  //if neuron is neuron in second hidden layer
			this.weights=new double[H2+1];  //initialize arrays
			this.paragogoi=new double[H2+1];
			this.paragogoi1=new double[H2+1];
			for(int i=0;i<(H2+1);i++){
				this.weights[i]=rand.nextDouble()*(2)-1;  //weights are random numbers between (-1,1)
			}
		}else if(layer==4){  //if neuron is neuron in third hidden layer
			this.weights=new double[H3+1];  //initialize arrays
			this.paragogoi=new double[H3+1];
			this.paragogoi1=new double[H3+1];
			for(int i=0;i<(H3+1);i++){
				this.weights[i]=rand.nextDouble()*(2)-1;  //weights are random numbers between (-1,1)
			}
		}
	}
	public double getOutput()  //accessor method to get the output of neuron
	{
		return output;
	}
	public double[] getweights()  //accessor method to get the weights of neuron
	{
		return weights;
	}
	public double[] getparagogoi()  //accessor method to get array paragogoi of neuron
	{
		return paragogoi;
	}
	public double[] getparagogoi1() //accessor method to get array paragogoi1 of neuron
	{
		return paragogoi1;
	}
	public void setweights(int i,double weight) //mutator method to change a weight of neuron
	{
		weights[i]=weight;		
	}
	public void setweights(double[] weight)  //mutator method to change weights of neuron
	{
		this.weights=weight;		
	}
	public void setparagogoi(int i,double paragogos)  //mutator method to change an element of array paragogoi of neuron
	{
		this.paragogoi[i]=paragogos;	
	}
	public void setparagogoi1(int i,double paragogos) //mutator method to change an element of array paragogoi1 of neuron
	{
		this.paragogoi1[i]=paragogos;	
	}
	public void setoutput(double timi)  //mutator method to change output of neuron
	{
		this.output=timi;	
	}
	public void setparagogoi1(double i) //mutator method to change array paragogoi1 of neuron
	{
		int timi=0;
		if(layer==1){
			timi=d+1;
		}else if(layer==2){
			timi=H1+1;
		}if(layer==3){
			timi=H2+1;
		}if(layer==4){
			timi=H3+1;
		}	
		for(int j=0;j<timi;j++){
			this.paragogoi1[j]=i;
		}	
	}
}