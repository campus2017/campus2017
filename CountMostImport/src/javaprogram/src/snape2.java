package snape;

import java.util.Scanner;
import java.beans.*;
import java.net.*;

class snape2 {
	public static void main(String[] args){
		int i=1,j=0;
		Scanner in =new Scanner(System.in);
		int N=in.nextInt();
        int k=N-1;
		int snape[][]=new int[N][N];
		while(i<N*N){
			if(j+k==N-1){
				while(j!=k){
					snape[j][k]=i;
					i++;
					j++;
				}
			}
			if(j==k){
				while(j+k!=N-1){
					snape[j][k]=i;
				    i++;
				    k--;
				}
			}
			if(j+k==N-1){
				while(j!=k){
					snape[j][k]=i;
					i++;
					j--;
				}
			}
          if(j==k){
				while( j+k !=N-1 ){
				    snape[j][k]=i;
				    i++;
				    k++;
				}
			}
			j++;
			k--;
		}
		if(i==N*N)
		    snape[j][k]=N*N;
		for(i=0;i<N;i++){
			for(j=0;j<N;j++){
				System.out.print(snape[i][j]);
              if(j!=N-1) System.out.print(" ");
			}
			System.out.println();
		}   
	}

}
