package sort;

import java.util.Scanner;
import java.text.*;
public class xuanzedi {
	public static void xuanze(int[] A,int size,int n){
		if(size!= n-1){
			int min=size;
			for(int i=size;i<n;i++){
				if(A[min]>A[i]){
					min=i;
				}
			}
			if(A[min]!=A[size]){
				int temp=A[min];
				A[min]=A[size];
				A[size]=temp;
			}
			xuanze(A,size+1,n);
		}
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int A[]=new int[n];
		for(int i=0;i<n;i++){
			A[i]=in.nextInt();
		}
		xuanze(A,0,n);
		for(int i=0;i<n;i++){
			System.out.print(A[i]+" ");
		}
	}
}
