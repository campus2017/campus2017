package snape;

import java.util.Scanner;
import java.lang.*;
import java.math.*;
import java.rmi.*;

public class mima {
	public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int mi[]=new int[21];
	    String str= in.nextLine();
	    String s[]=str.split(" ");
	    int l=Integer.parseInt(s[0]);
	    int r=Integer.parseInt(s[1]);
	    int m=Integer.parseInt(s[2]);	    
	    int i;
	    int sum=0;
	    for(i=l;i<=r;i++){
	    	int t=i;
	    	int num=m;
	    	while(t !=0){
	    	    if((t&1)==1){
	    		   num--;
	    	    }
	    	    t=t>>1;
	    	}
	    	if(num==0) sum++;
	    }
	    if(sum==0) sum=-1;
	    System.out.println(sum);
	  }

}
