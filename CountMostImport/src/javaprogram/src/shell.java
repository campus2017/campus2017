package sort;

import java.util.Scanner;
import java.beans.*;
import java.net.*;
import java.security.*;
import java.text.*;
public class shell {
	public static void shells(int[] A){
		
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int A[]=new int[n];
		for(int i=0;i<n;i++){
			A[i]=in.nextInt();
		}
		
		for(int i=0;i<n;i++){
			System.out.print(A[i]+" ");
		}
	}
}
