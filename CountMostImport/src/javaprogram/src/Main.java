package snape;

import java.util.*;
import java.security.*;

public class Main {
	public static void main(String[] arg){
	    Scanner in=new Scanner(System.in);
	    int n=in.nextInt();
	    int[] A=new int[n];
	    int i=0;
	    int temp=0;
	    int sum=0;
	    for(i=0;i<n;i++){
	    	A[i]=in.nextInt();
	    }
	    Arrays.sort(A);
	    for(i=0;i<n-1;i++){
	    	if((i+1+sum)%3!=0){
	    	    if(A[i+1]-A[i]>10){
	    		    temp=A[i];
	    		    while(temp+10<A[i+1] && (i+1+sum)%3!=0 ){
	    			    temp=temp+10;
	    			    sum++;
	    		    }
	    	     }
	    	}
	    }
	    while((sum+n)%3!=0){
	    	sum++;
	    }
	    System.out.println(sum);
	}
}
