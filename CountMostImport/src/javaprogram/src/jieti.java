package snape;

import java.util.*;
import java.lang.*;
import java.math.*;
public class jieti{
  public static int f(int a){
    if(a==1) return 1;
    if(a==2) return 1;
    return f(a-1)+f(a-2);
  }
  public static void main(String[] args){
    Scanner in=new Scanner(System.in);
    int n= in.nextInt();
    int a[]=new int[n];
    for(int i=0;i<n;i++){
     a[i]=in.nextInt();
    }
    for(int i=0;i<n;i++){
       if(a[i] ==1){
         System.out.println(a[i]);
       }
       else{
         System.out.println(f(a[i]));
       }
    }
}
}
