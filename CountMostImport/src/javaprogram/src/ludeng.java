package snape;

import java.util.*;
import java.applet.*;
import java.awt.*;
import java.lang.*;
import java.math.*;
public class ludeng {
	  public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int a[]=new int[n];
        int i;
        for(i=0;i<n;i++){
          a[i]=in.nextInt();
        }
        Arrays.sort(a);
        double max=a[1]-a[0];
        for(i=2;i<n;i++){
          if(max<a[i]-a[i-1]){
            max=a[i]-a[i-1];
          }
        }
        double max1=a[0];
        if(a[0]<m-a[n-1]){
          max1=m-a[n-1];
        }
        if(max/2<max1){
          System.out.printf("%.2f",max1);
        }
        else{
          System.out.printf("%.2f",max/2);
        }
      }
}
