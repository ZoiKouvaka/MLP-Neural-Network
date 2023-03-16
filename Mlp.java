//Vasiliki Katogianni A.M. 4696 Zoi Kouvaka A.M. 4706 Athanasios Katsilieris A.M. 3247
import java.lang.Math;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.io.FileNotFoundException;
class Mlp
{
	private int d=2;  //number of input neurons
	public int K=3;  //number of categories
	public int H1=2;  //number of neurons of first hidden layer
	public int H2=2;  //number of neurons of second hidden layer
	public int H3=2;   //number of neurons of third hidden layer
	private Neuron[] inputneurons; //array with input neurons 
	private Neuron[] firstLayer;  //array with neurons of the first hidden layer
	private Neuron[] secondLayer;  //array with neurons of the second hidden layer
	private Neuron[] thirdLayer;  //array with neurons of the third hidden layer
	private Neuron[] outputneurons;    //array with output neurons 
	private double[] ufirstLayer;  //array with u of neurons of the first hidden layer
	private double[] outputfirstLayer;  //array with output of neurons of the first hidden layer
	private double[] usecondLayer;  //array with u of neurons of the second hidden layer
	private double[] outputsecondLayer;  //array with output of neurons of the second hidden layer
	private double[] uthirdLayer;  //array with u of neurons of the third hidden layer
	private double[] outputthirdLayer;  //array with output of neurons of the third hidden layer
	private double[] uoutputLayer;  //array with u of output neurons 
	private double[] o;  //array with output of output neurons 
	private double[] deltaoutput;  //array with delta of output neurons 
	private double[] deltaH3;  //array with delta of neurons of the third hidden layer
	private double[] deltaH2; //array with delta of neurons of the second hidden layer
	private double[] deltaH1;   //array with delta of neurons of the first hidden layer  
	private double katofli=1; //katofli
	private int N=4000; //paradeigmata sinolou ekpaideusis
	private int B=N/100; //mini-batches
	private	double pososto=0; //percentage of right decisions
	private double n=0.01;// learning rate
	private int layer;//0->input,1->H1,2->H2,3->H3,4->output
	public Mlp() //constructor
	{
		inputneurons = new Neuron[d]; 
		for(int i=0;i<d;i++){
			inputneurons[i]=new Neuron(n,0); //create input neurons 
		}	
		firstLayer = new Neuron[H1];
		for(int i=0;i<H1;i++){
			firstLayer[i]=new Neuron(n,1); //create neurons of the first hidden layer
		}
		secondLayer = new Neuron[H2];
        for(int i=0;i<H2;i++){
			secondLayer[i]=new Neuron(n,2); //create neurons of the second hidden layer
		}
		thirdLayer = new Neuron[H3];
        for(int i=0;i<H3;i++){
			thirdLayer[i]=new Neuron(n,3); //create neurons of the third hidden layer
		}	
		outputneurons = new Neuron[K];
        for(int i=0;i<K;i++){
			outputneurons[i]=new Neuron(n,4);  //create output neurons 
		}
        ufirstLayer=new double[H1];  //initialize arrays
        outputfirstLayer=new double[H1];
        usecondLayer=new double[H2];
        outputsecondLayer=new double[H2];		
		uthirdLayer=new double[H3];
        outputthirdLayer=new double[H3];
		uoutputLayer=new double[K];
        o=new double[K];
		deltaoutput=new double[K];
		deltaH3=new double[H3];
		deltaH2=new double[H2];
		deltaH1=new double[H1];
	}
	public double sigmatonos(double y)//where y is sigmoid(u) of neuron
	{
		return y*(1-y); //ipologismos paragogou logistikis sinartisis
	}
	public void computeUH1(int d) //compute u of neurons of the first hidden layer
	{
		double sum=0;
		double utemp;
		for(int i=0;i<H1;i++){	
			sum=0;
		    for(int j=0;j<d;j++){
				sum+= inputneurons[j].getOutput()*firstLayer[i].getweights()[j+1]; 
			}	
			utemp=sum+firstLayer[i].getweights()[0];
			ufirstLayer[i]=utemp;//periexei ta u kathe neurona tou protou epipedou
			outputfirstLayer[i]=1/(1+Math.exp(-ufirstLayer[i]));  //periexei tous exodous kathe neurona tou protou epipedou
		}
	}
	public void computeUH2() //compute u of neurons of the second hidden layer
	{
		double sum=0;
		double utemp;
		for(int i=0;i<H2;i++){	
		    sum=0;
		    for(int j=0;j<H1;j++){
				sum+= outputfirstLayer[j]*secondLayer[i].getweights()[j+1]; 
			}	
			utemp=sum+secondLayer[i].getweights()[0];
			usecondLayer[i]=utemp;//periexei ta u kathe neurona tou deuterou epipedou
			outputsecondLayer[i]=1/(1+Math.exp(-usecondLayer[i])); //periexei tous exodous kathe neurona tou deuterou epipedou
		}
	}
	public void computeUH3() //compute u of neurons of the third hidden layer
	{
		double sum=0;
		double utemp;
		for(int i=0;i<H3;i++){	
		    sum=0;
		    for(int j=0;j<H2;j++){
				sum+= outputsecondLayer[j]*thirdLayer[i].getweights()[j+1]; 
			}	
			utemp=sum+thirdLayer[i].getweights()[0];
			uthirdLayer[i]=utemp;//periexei ta u kathe neurona tou tritou epipedou
			outputthirdLayer[i]=1/(1+Math.exp(-uthirdLayer[i])); //periexei tous exodous kathe neurona tou tritou epipedou
		}
	}
	public void computeUoutput(int k) //compute u of output neurons 
	{
		double sum=0;
		double utemp;
		for(int i=0;i<k;i++){
			sum=0;
		    for(int j=0;j<H3;j++){
				sum+= outputthirdLayer[j]*outputneurons[i].getweights()[j+1]; 
			}	
			utemp=sum+outputneurons[i].getweights()[0];
			uoutputLayer[i]=utemp;//periexei ta u kathe neurona exodou
			o[i]=1/(1+Math.exp(-uoutputLayer[i])); //periexei tous exodous kathe neurona exodou
		}
	}
	public void give_input(double x[],int d) //set output of input neurons
	{
		for(int i=0;i<d;i++){	
			this.inputneurons[i].setoutput(x[i]);
		}	
	}
	public double[] forward_pass(double[] x,int d,double[] y,int k) //forward_pass method
	{
		give_input(x,d);
		computeUH1(d);
		computeUH2();
		computeUH3();
		computeUoutput(k);
		y=getO();
		return y;
	}
	public void initialize()  //theto ta merika athroismata isa me 0
	{
		for(int i=0;i<H1;i++){	
			firstLayer[i].setparagogoi1(0);
		}
		for(int i=0;i<H2;i++){	
			secondLayer[i].setparagogoi1(0); 
		}
		for(int i=0;i<H3;i++){	
			thirdLayer[i].setparagogoi1(0); 
		}
		for(int i=0;i<K;i++){	
			outputneurons[i].setparagogoi1(0); 	
		}
	}
	public void updateparagogoi1() //enimerosi ton merikon athroismaton
	{
		for(int i=0;i<H1;i++){	
			for(int l=0;l<(d+1);l++){
				firstLayer[i].setparagogoi1(l,firstLayer[i].getparagogoi()[l]+firstLayer[i].getparagogoi1()[l]);  //enimerosi ton merikon athroismaton ton neuronon tou prodou epipedou
			}
		}	
		for(int i=0;i<H2;i++){	
			for(int l=0;l<(H1+1);l++){
				secondLayer[i].setparagogoi1(l,secondLayer[i].getparagogoi()[l]+secondLayer[i].getparagogoi1()[l]); //enimerosi ton merikon athroismaton ton neuronon tou deuterou epipedou
			}
		}
		for(int i=0;i<H3;i++){	
			for(int l=0;l<(H2+1);l++){
				thirdLayer[i].setparagogoi1(l,thirdLayer[i].getparagogoi()[l]+thirdLayer[i].getparagogoi1()[l]); //enimerosi ton merikon athroismaton ton neuronon tou tritou epipedou
			}
		}
		for(int i=0;i<K;i++){	
			for(int l=0;l<(H3+1);l++){
				outputneurons[i].setparagogoi1(l,outputneurons[i].getparagogoi()[l]+outputneurons[i].getparagogoi1()[l]); //enimerosi ton merikon athroismaton ton neuronon exodou
			}
		}
	}
	public void updateweights() //enimerosi varon
	{
		for(int i=0;i<H1;i++){	
			for(int l=0;l<(d+1);l++){
				firstLayer[i].setweights(l,firstLayer[i].getweights()[l]-n*firstLayer[i].getparagogoi1()[l]); //enimerosi ton varon ton neuronon tou prodou epipedou
			}
		}	
		for(int i=0;i<H2;i++){	
			for(int l=0;l<(H1+1);l++){
				secondLayer[i].setweights(l,secondLayer[i].getweights()[l]-n*secondLayer[i].getparagogoi1()[l]); //enimerosi ton varon ton neuronon tou deuterou epipedou
			}
			
		}
		for(int i=0;i<H3;i++){	
			for(int l=0;l<(H2+1);l++){
				thirdLayer[i].setweights(l,thirdLayer[i].getweights()[l]-n*thirdLayer[i].getparagogoi1()[l]);  //enimerosi ton varon ton neuronon tou tritou epipedou
			}
			
		}
		for(int i=0;i<K;i++){	
			for(int l=0;l<(H3+1);l++){
				outputneurons[i].setweights(l,outputneurons[i].getweights()[l]-n*outputneurons[i].getparagogoi1()[l]); //enimerosi ton varon ton neuronon exodou
			}			
		}
	}
	public double calculateError(double t[]) //calculate E^n(w)
	{
		double sum=0;
		for(int i=0;i<K;i++){	
		    sum=sum+(t[i]-o[i])*(t[i]-o[i]);
		} 
		return sum;
	}	
	public void backprop(double x[], int d, double t[], int K) //backpropagation method
	{
		double sum=0;
		for(int i=0;i<K;i++){	
		    deltaoutput[i]=sigmatonos(o[i])*(o[i]-t[i]); //calculate delta of output neurons 
		}	
		for(int j=0;j<H3;j++){	
		    sum=0;
		    for(int l=0;l<K;l++){
				sum+=(outputneurons[l].getweights()[j+1]*deltaoutput[l]);
			}	
			deltaH3[j]=sigmatonos(outputthirdLayer[j])*sum; //calculate delta of neurons of the third hidden layer
		}
		sum=0;
		for(int j=0;j<H2;j++){	
		    sum=0;
		    for(int l=0;l<H3;l++){
				sum+=(thirdLayer[l].getweights()[j+1]*deltaH3[l]);
			}	
			deltaH2[j]=sigmatonos(outputsecondLayer[j])*sum; //calculate delta of neurons of the second hidden layer
		}
		sum=0;
		for(int j=0;j<H1;j++){	
		    sum=0;
		    for(int l=0;l<H2;l++){
				sum+=(secondLayer[l].getweights()[j+1]*deltaH2[l]);
			}	
			deltaH1[j]=sigmatonos(outputfirstLayer[j])*sum; //calculate delta of neurons of the first hidden layer
		}
		for(int i=0;i<H1;i++){	
			for(int l=0;l<(d+1);l++){
				if(l==0){
					firstLayer[i].setparagogoi(l,deltaH1[i]); 
				}else{
					firstLayer[i].setparagogoi(l,deltaH1[i]*inputneurons[l-1].getOutput()); //ipologismos merikon athroismaton neuronon protou epepedou
				}	
			}	
		}
		for(int i=0;i<H2;i++){	
			for(int l=0;l<(H1+1);l++){
				if(l==0){
					secondLayer[i].setparagogoi(l,deltaH2[i]); 
				}else{
					secondLayer[i].setparagogoi(l,deltaH2[i]*outputfirstLayer[l-1]); //ipologismos merikon athroismaton neuronon deuterou epepedou
				}	
			}	
		}
		for(int i=0;i<H3;i++){	
			for(int l=0;l<(H2+1);l++){
				if(l==0){
					thirdLayer[i].setparagogoi(l,deltaH3[i]); 
				}else{
					thirdLayer[i].setparagogoi(l,deltaH3[i]*outputsecondLayer[l-1]);  //ipologismos merikon athroismaton neuronon tritou epepedou
				}	
			}	
		}
		for(int i=0;i<K;i++){	
			for(int l=0;l<(H3+1);l++){
				if(l==0){
					outputneurons[i].setparagogoi(l,deltaoutput[i]); 
				}else{
					outputneurons[i].setparagogoi(l,deltaoutput[i]*outputthirdLayer[l-1]);  //ipologismos merikon athroismaton neuronon exodou
				}	
			}	
		}
	}
	public void gradient_descent(double[][] ar,double[] categ) //gradient_descent method
	{
		int epoxi=1; //epoxi
		double sumoferrors=0; //E(w)
		double error=0; //E^n(w)
		double[] t=new double[K]; //pinakas pou xrisimopoieitai gia na kodikopoiithei i katigoria
		double[] empty={}; //not used
		int counter40teams=0; //counter to load every time different 40 examples
		while(epoxi>=1 && epoxi<=100700){  //runs many epochs to be sure neural network is learning
			initialize(); 
			for(int n=0;n<B;n++){
				if(categ[B*counter40teams+n]==1){ //if category is 1
					t[0]=1;
					t[1]=0;
					t[2]=0;
				}else if(categ[B*counter40teams+n]==2){  //if category is 2
					t[0]=0;
					t[1]=1;
					t[2]=0;
				}else if(categ[B*counter40teams+n]==3){ //if category is 3
					t[0]=0;
					t[1]=0;
					t[2]=1;
				}	
				give_input(ar[B*counter40teams+n],d);
				forward_pass(ar[B*counter40teams+n],d,empty,K);
				backprop(ar[B*counter40teams+n],d,t,K);
				updateparagogoi1();
				error=calculateError(t)/2;
				sumoferrors=sumoferrors+error;
			}  
			if(counter40teams<99) //so that it will be trained only in 4000 examples 
			{
				counter40teams++; 
			}
			else if(counter40teams==99)
			{
				counter40teams=0;
			}
			updateweights();
			System.out.println("Epoxi "+epoxi+": E(w)= "+sumoferrors);
			sumoferrors=0;
			epoxi++; //goes to next epoch
		}	
	}

	public void testExamples(int numOfExamples,int positionOfFirstExample,double[][] ar,double[] categ) //method to test the neural network to the examples 
	{  
		double[] t=new double[K]; //temp category holder
		double[] y; //this will be the output of the neural network for an example
		double[] empty={}; //not used
		int tempPos=0; //position of the max value of output
		double temp=0; //max output value
		double[] tempArr=new double[K]; //neural network put the example in this category
		boolean same=true;
		for (int i=positionOfFirstExample;i<numOfExamples+positionOfFirstExample;i++)
		{
			if (categ[i]==1) // encode category
			{
				t[0]=1;
				t[1]=0;
				t[2]=0;
			}else if(categ[i]==2){
				t[0]=0;
				t[1]=1;
				t[2]=0;
			}else if(categ[i]==3){ //
				t[0]=0;
				t[1]=0;
				t[2]=1;
			}
			give_input(ar[i],d);
			y=forward_pass(ar[i],d,empty,K);
			temp=y[0];
			tempPos=0;
			for(int j=0;j<K-1;j++) 
			{
				if(temp<y[j+1]) //find the max number of the 
				{
					temp=y[j+1];
					tempPos=j+1;
				}
			}
			for(int k=0;k<K;k++)
			{
				if(k==tempPos)
				{
					tempArr[k]=1;
				}
				else
				{
					tempArr[k]=0;
				}
			}
			for(int l=0;l<K;l++)
			{
				if(tempArr[l]!=t[l]) 
				{
					same=false; //the category nn gave is not the category of the example
					break;
				}
			}
			if(!same)
			{
				System.out.println(ar[i][0]+","+ar[i][1]+","+"4"); //we encode wrong answers as 4
			}
			else
			{
				System.out.println(ar[i][0]+","+ar[i][1]+","+categ[i]); //for right answers we keep the number of the category of the example
				this.pososto++; //increase percentage every time gives a right decision
			}
			same=true;
		}
	}

	public Neuron[] get1stLayer() //accessor method to get array with neurons of first layer
	{
		return firstLayer;
	}

	public Neuron[] get2ndLayer() //accessor method to get array with neurons of second layer
	{
		return secondLayer;
	}
    public double getpososto() //accessor method to get the percentage
	{
		return pososto;
	}
	public Neuron[] get3rdLayer()  //accessor method to get array with neurons of third layer
	{
		return thirdLayer;
	}

	public Neuron[] getOutNeur() //accessor method to get array with output neurons 
	{
		return outputneurons;
	}

	public double[] getUfirstLayer() //accessor method to get array with u of neurons of first layer
	{
		return ufirstLayer;
	}

	public double[] getOutputfirstLayer() //accessor method to get array with outputs of neurons of first layer
	{ 
		return outputfirstLayer;
	}

	public double[] getUsecondLayer() //accessor method to get array with u of neurons of second layer
	{
		return usecondLayer;
	}

	public double[] getOutputsecondLayer() //accessor method to get array with outputs of neurons of second layer
	{
		return outputsecondLayer;
	}

	public double[] getUthirdlayer() //accessor method to get array with u of neurons of third layer
	{
		return uthirdLayer;
	}
	
	public double[] getOutputthirdLayer()  //accessor method to get array with outputs of neurons of third layer
	{
		return outputthirdLayer;
	}

	public double[] getO()  //accessor method to get array with outputs of output neurons 
	{
		return o;
	}

	public double[] getUOutput() //accessor method to get array with u of output neurons 
	{
		return uoutputLayer;
	}

}
