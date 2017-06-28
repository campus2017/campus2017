package sort;
import java.io.*;
import java.util.*;
import java.applet.*;
public class maopaodi {
	public static void mao(int[] A,int size){
		if(size==1){}
		else{
		    for(int i=0;i<size-1;i++){
			    if(A[i]>A[i+1]){
				    int temp=A[i];
				    A[i]=A[i+1];
				    A[i+1]=temp;
			    }
		     }		
		     mao(A,size-1);
		}
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);		
		int size=in.nextInt();
		int[] A=new int[size];
		for(int i=0;i<size;i++){
			A[i]=in.nextInt();
		}	
		mao(A,size);
		for(int i=0;i<size;i++){
			System.out.print(A[i]+" ");
		}
	}

}
