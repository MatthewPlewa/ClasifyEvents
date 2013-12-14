import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import java.io.File;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.awt.Color;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;


public class clasify
{
//Start Main
	public static void main(String args[])throws IOException{
		
		Scanner in = new Scanner(System.in);
		String location="";
		System.out.println("Please give location of the images");
		location = in.nextLine();
		
		/*
		 * THIS WILL ALLOW FOR MULTIPULE PICTURES TO BE ADDED TO THE PROGRAM.
		 */
		////////////////////////////////////////////////////////////////////////////////
		
		File f = new File(location);//ADD A DIRECTORY HERE
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		int size = files.size();
		if (size >10){
			size = 10;////////////// CHANGE HERE TO SET MAX PICS AT ONE TIME
		}
		for(int lcv = 0; lcv<size; lcv++){
		
		/////////////////////////////////////////////////////////////////////////////////
		//String locationarray = files.get(lcv);
		
		BufferedImage image = ImageIO.read(files.get(lcv));
		int w = image.getWidth();
		int h = image.getHeight();
		System.out.println("Image Dimension: Height-" + h + ", Width-"+ w);
		int total_pixels=(h * w);
	    Color[] colors = new Color[total_pixels];
	    SimpleRegression regression = new org.apache.commons.math3.stat.regression.SimpleRegression();
	    clasifyEvent(image, w, h, colors,regression);
		}
	}
//end main
	
	
	
	
	
	
	
	
	
	
////////////////////////////////////////////////START METHODS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	
	
	
	
	
	
	
	private static SimpleRegression clasifyEvent(BufferedImage image, int w, int h, Color[] colors, SimpleRegression regression) {
		/*
		*/
		int i = 0;
		int percent=1;
		boolean got=false;
		int firstX=0, firstY=0;
		int valid = 0;
	//	SimpleRegression[] regress;
	   // SimpleRegression regression = new org.apache.commons.math3.stat.regression.SimpleRegression(); Had it pass a regression instead of having it create it.
	    for (int x = 0; x < w; x++)
	    {
	 
	    	if(w/100<=x&&percent==1){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*2<=x&&percent==2){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*3<=x&&percent==3){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*4<=x&&percent==4){
	    		System.out.print("==");
	    		percent++;
	    	}
	    	if((w/100)*5<=x&&percent==5){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*6<=x&&percent==6){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*7<=x&&percent==7){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*8<=x&&percent==8){
	    		System.out.print("===");
	    		percent++;
	    	}
	    	if((w/100)*9<=x&&percent==9){
	    		System.out.println("===");
	    		percent++;
	    	}
	    	//colors[i] = new Color(image.getRGB(x, y));
	      for (int y = 0; y < h; y++)
	      {
	        colors[i] = new Color(image.getRGB(x, y));
	        //regress[i] = new SimpleRegression(); 
	       // SimpleRegression p = regress[i];
	        Color c = colors[i];
		      int r = c.getRed();
		      int g = c.getGreen();
		      int b = c.getBlue();
		      int rgb=c.getRGB();
		      int init = 0;
		      int initY=0;
		      int initX=0;
		      //int valid=0;
		      if (init == 0){
		    	  initY= y;
		    	  initX=x;
		      } 
		      
		      if(initY!=y|| initX!=x){
		    	  valid = 1;
		      }
		      
		      if((r)>130|| g >130 || b>130){
		    	  if(got==false){
		    		  got=true;
		    		  firstX=x;
		    		  firstY=y;
		    	  }
		    	  
		    	  
		    	  
		    	  
		    	  //This shows all of the information on the pics
		    	  
		    	 // System.out.println("Red:" + r + " Green:" + g + " Blue:" + b + " rgb:"+ rgb+ " Coordinates are "+x+","+y);   
		    	  // 
		    	  
		    	  
		    	  
		    	  
		    	  
		    	  
		    	  if((x - firstX) <=50 && (y-firstY)<= 50){
		    	  regression.addData(x,y);
		    	  }
		    	  else{
		    		  System.out.println("Multiple Events Detected");
		    	  }
		      }
	        i++;
	      }
	      
	    }
	    System.out.println("Getting Regression");
	    double r = regression.getR();
	    if (r >= .8){
	    	System.out.println("Worm/ Track "+ "R = "+ r);
	    	return regression;
	    }
	    if (r > .5&& Math.abs(r) < .7 ){
	    	System.out.println("Blob " + "R = "+ r);
	    	return regression;
	    }
	    
	   
	    if(regression.getN()<= 2){ /// HAD AN ISSUE WITH THE BLANKS NOT SCREAMING BLANK
	    	System.out.println("No Event/hand check due to less than 3 points");
	    	return regression;
	    }
	    if( valid == 0 ){
	    	System.out.println("EVENT MUST BE SELF EVALULATED. \n SLOPE IS = "+  regression.getSlope());
	    	return regression;
	    }
	    else System.out.println("Cascade or Unknown "+ "R = "+ r);
	    
	    return regression;
	}	
	
	
	
	
	public void makeImgData(String str, SimpleRegression reg, BufferedImage image, String path ){
		/*
		 * THIS WILL OUTPUT A NEW IMAGE WITH A NEW NAME, DATA ON THE IMAGE CONTAINING EQUATION AND REG DATA, AND A NEW FILE FOLDER TO
		 * CONTAIN THE OUTPUTED MATERIAL. 
		 */
		
		
		
		System.out.println(reg.getR());
		//System.out.println();
	}







}
//C:\Users\Owner\Desktop\
