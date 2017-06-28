package snape;

import java.util.*;
import java.math.*;
import java.rmi.*;
import java.beans.*;
import java.net.*;
import java.security.*;
public class Main1 {
	public static void main(String[] arg){
	    Scanner in=new Scanner(System.in);
	    int n=in.nextInt();
	    int m=in.nextInt();
	    int[] A=new int[n];
	    int i=0;
	    int sum=0;
	    for(i=0;i<n;i++){
	    	A[i]=in.nextInt();
	    }
	    for(i=0;i<n-1;i++){
	    	for(int j=i+1;j<n;j++){
	    		if((A[i]^A[j])>m){
	    			sum++;
	    		}
	    	}
	    }
	    System.out.println(sum);
	}
}
