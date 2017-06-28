package snape;
import java.util.*;
import java.beans.*;
import java.net.*;
import java.security.*;
import java.text.*;
public class Sortshuzu {
	
	  public static void main(String[] args){
	    Scanner in=new Scanner(System.in);
	    int i;
	    int length=in.nextInt();
	    int a[]=new int[length];
	    int b[]=new int[length];
	    for(i=0;i<length;i++){
	         a[i]=in.nextInt();
	         b[i]=a[i];
	    }
	    int left=0,right=length-1;
	    Arrays.sort(b);
	    while(left<length&&a[left]==b[left]) left++;
	    while(right>=0&&a[right]==b[right])right--;
	    for( i=0;i<right-left;i++){
	      if(a[left+i]!=b[right-i]) break;
	    }
	    if(i==right-left){
	      System.out.println("yes");
	    }
	    else{
	      System.out.println("no");
	    }
	  }
}
