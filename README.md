# MLP-Neural-Network
A collab with Vasiliki Katogianni and Athanasios Katsilieris

A neural network classifying examples of type (x1,x2) as following:
1) if (x1 – 0.5)2 + (x2 – 0.5)2 <0.2, and x2>0.5 classify example in category C1,
2) if (x1 – 0.5)2 + (x2 – 0.5)2 <0.2, and x2<0.5 classify example in category C2,
3) if (x1 + 0.5)2 + (x2 + 0.5)2 <0.2, and x2>-0.5 classify example in category C1,
4) if (x1 + 0.5)2 + (x2 + 0.5)2 <0.2, and x2<-0.5 classify example in category C2,
5) if (x1 – 0.5)2 + (x2 + 0.5)2 <0.2, and x2>-0.5 classify example in category C1,
6) if (x1 – 0.5)2 + (x2 + 0.5)2 <0.2, and x2<-0.5 classify example in category C2,
7) if (x1 + 0.5)2 + (x2 - 0.5)2 <0.2, and x2>0.5 classify example in category C1,
8) if (x1 + 0.5)2 + (x2 - 0.5)2 <0.2, and x2<0.5 classify example in category C2,
9) if nothing above mark as category C3.

Changing the number of neurons in each level is possible and leads to different success ratio. (20-22-18 gives a 96.35% of success ratio)

java generateData>datatest.txt will produce an example-category file

java sortprogram>XYZ.txt will produce an example- category file containing the examples of datatest.txt and the classification done by MLP. If correct, number of example category is shown,while number 4 is a sign of wrong classification. 

run graphmaker.py to see wrong classified examples marked with red colour. Change line number two to open XYZ.txt file from previous step
