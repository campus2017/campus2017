package sort;

import java.util.Scanner;
import java.applet.*;
import java.awt.*;
public class churufei {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int A[]=new int[n];
		for(int i=0;i<n;i++){
			A[i]=in.nextInt();
		}
		for(int i=1;i<n;i++){
			int j=i;
			int temp=A[i];
			while(temp<A[j-1]){
				A[j]=A[j-1];
				j--;
				if(j==0){
					break;
				}
			}
			A[j]=temp;
		}
		for(int i=0;i<n;i++){
			System.out.print(A[i]+" ");
		}
	}
}
