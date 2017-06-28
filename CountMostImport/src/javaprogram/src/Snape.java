package snape;

import java.util.Scanner;
import java.lang.*;
import java.math.*;
public class Snape {
	public static void main(String[] args){
		int i=1,j=0,k=0;
		Scanner in =new Scanner(System.in);
		int N=in.nextInt();
//		int N=4;
		int snape[][]=new int[N][N];
		while(i<N*N){
			if(j==k){
				while( j+k !=N-1 ){
				    snape[j][k]=i;
				    i++;
				    k++;
				}
			}
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
			j++;
			k++;
		}
		if(i==N*N)
		    snape[j][k]=N*N;
//		for(i=0;i<N;i++){
//			for(j=0;j<N;j++){
//				System.out.print(snape[i][j]+" ");
//			}
//			System.out.println();
//		}
		i=1;j=0;k=0;
        while(i<N*N){
            if(j==k){
                while(j+k != N-1){
                    System.out.print(snape[j][k]+",");
                    k++;
                    i++;
                }
            }
            if(j+k == N-1){
                while(j!=k){
                    System.out.print(snape[j][k]+",");
                    j++;
                    i++;
                }
            }
            if(j == k){
                while(j+k != N-1){
                    System.out.print(snape[j][k]+",");
                    k--;
                    i++;
                }
            }
            if(j+k == N-1){
                while(j!=k){
                    System.out.print(snape[j][k]);
                    if(i!=N*N){
                    	System.out.print(",");
                    }
                    j--;
                    i++;
                }
            }
            k++;
            j++;
        }
        if(i==N*N){
            System.out.print(snape[j][k]);
        }   
	}

}
