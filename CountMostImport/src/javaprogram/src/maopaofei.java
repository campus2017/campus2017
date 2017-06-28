package sort;

import java.util.*;
import java.applet.*;
import java.awt.*;
import java.lang.*;
public class maopaofei {
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		LinkedList<Integer> A=new LinkedList<Integer>();
		int size=in.nextInt();
		for(int i=0;i<size;i++){
			A.add(in.nextInt());
		}	
		int temp;
		for(int i=0;i<size-1;i++){
			for(int j=0;j<size-i-1;j++){
				if(A.get(j+1)<A.get(j)){
					temp=A.get(j+1);
					A.set(j+1, A.get(j));
					A.set(j, temp);
				}
			}
		}
		for(int i=0;i<size;i++){
			System.out.print(A.get(i)+" ");
		}
	}

}
