package snape;
import java.util.Scanner;
import java.net.*;
import java.security.*;
public class gu{
	  public static void main(String[] arg){
	    Scanner in=new Scanner(System.in);
	    int n;
	    
	    while(in.hasNextInt()){
	    	int m=1;
	          n=in.nextInt();
	         int i=1,j=1;
	         for(int k=2;k<=n;k++){
	           if(i<=j){
	             m++;
	             i++;
	           }
	           else{
	             m--;
	             i=1;
	             j++;
	           }
	      }
	    System.out.println(m);
	    }
	 }
}
