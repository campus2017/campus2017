package snape;

import java.io.*;
import java.util.*;
import java.applet.*;
import java.lang.*;
public class jisuanji{
  public static void main(String[] args){
    List<Integer> list = new ArrayList<>();
    Scanner in=new Scanner(System.in);
    while(in.hasNextInt()){
      int a=in.nextInt();
      for(int i=2;i<=a;i++){
        while(a%i==0){
          list.add(i);
          a=a/i;
        }
      }
      for(int j=1;j<=5;j++){
        for(int i=0;i<list.size();i++){
          int b=list.get(i);
          Stack<Integer> stack=new Stack<>();
          while(b/10>0){
              stack.push(b%10);
              b=b/10;
          }
          stack.push(b);
          while(!stack.isEmpty()){
            Printzifu(stack.pop(),j);
          }
          if(j==3 && i<list.size()-1){
            System.out.print("*");
          }
          else{
            System.out.print(" ");
          }
        }
        System.out.println();
      }
      list.clear();
    }
  }
  public static void Printzifu(int a,int b){
    switch(a){
      case 0:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("| |");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("| |");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
      case 1:switch(b){
        case 1:System.out.print("   ");break;
        case 2:System.out.print(" | ");break;
        case 3:System.out.print("   ");break;
        case 4:System.out.print(" | ");break;
        case 5:System.out.print("   ");break;
                 }
                 break;
      case 2:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("  |");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("|  ");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
      case 3:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("  |");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("  |");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
      case 4:switch(b){
        case 1:System.out.print("   ");break;
        case 2:System.out.print("| |");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("  |");break;
        case 5:System.out.print("   ");break;
                 }
                 break;
      case 5:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("|  ");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("  |");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
      case 6:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("|  ");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("| |");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
      case 7:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("  |");break;
        case 3:System.out.print("   ");break;
        case 4:System.out.print("  |");break;
        case 5:System.out.print("   ");break;
                 }
                 break;
      case 8:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("| |");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("| |");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
      case 9:switch(b){
        case 1:System.out.print(" - ");break;
        case 2:System.out.print("| |");break;
        case 3:System.out.print(" - ");break;
        case 4:System.out.print("  |");break;
        case 5:System.out.print(" - ");break;
                 }
                 break;
    }
  }
}