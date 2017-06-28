package sort;

import java.util.Scanner;
import java.net.*;
import java.security.*;
import java.text.*;
public class kuaisufei {
	public static void quik(int left,int right,int[] A){
		if(left<right){
			int low=left;
			int high=right;
			int key=A[left];
			while(low<high){
				while(low<high && key<A[high]){
					high--;
				}
				A[low]=A[high];
				while(low<high &&key>A[low]){
					low++;
				}
				A[high]=A[low];
			}
			A[low]=key;
			quik(left,low-1,A);
			quik(low+1,right,A);
		}
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int A[]=new int[n];
		for(int i=0;i<n;i++){
			A[i]=in.nextInt();
		}
		quik(0,n-1,A);	
		for(int i=0;i<n;i++){
			System.out.print(A[i]+" ");
		}
	}
}
