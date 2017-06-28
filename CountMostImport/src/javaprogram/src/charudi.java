package sort;

import java.util.Scanner;
import java.lang.*;
import java.math.*;
import java.rmi.*;
import java.beans.*;

public class charudi {
	public static void charu(int[] A,int size,int n){
		if(size==n-1){
			
		}
		else{
			for(int i=size;i<n;i++){
				int temp=A[i];
				int j=i;
				while(temp<A[j-1]){
					A[j]=A[j-1];
					j--;
					if(j==0) break;
				}
				A[j]=temp;
			}
			charu(A,size+1,n);
		}
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int A[]=new int[n];
		for(int i=0;i<n;i++){
			A[i]=in.nextInt();
		}
		charu(A,1,n);
		for(int i=0;i<n;i++){
			System.out.print(A[i]+" ");
		}
	}
}
