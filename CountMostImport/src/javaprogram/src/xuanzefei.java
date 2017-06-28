package sort;
import java.util.*;
import java.awt.*;
import java.lang.*;
public class xuanzefei {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int A[]=new int[n];
		for(int i=0;i<n;i++){
			A[i]=in.nextInt();
		}
		for(int i=0;i<n;i++){
			int min=i;
			for(int j=i;j<n;j++){
				if(A[j]<A[min]){
					min=j;
				}
			}
			if(A[i]!=A[min]){
				int temp=A[i];
				A[i]=A[min];
				A[min]=temp;
			}
		}
		for(int i=0;i<n;i++){
			System.out.print(A[i]+" ");
		}
	}

}
