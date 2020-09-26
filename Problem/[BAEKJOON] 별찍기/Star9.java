//[백준] 별찍기9
/*
*********
 *******
  *****
   ***
    *
   ***
  *****
 *******
*********
 */
import java.util.Scanner;

public class Star9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<2*N-1;j++) {
				if(j<i)	System.out.print(" ");
				else if(j<=(N-1-i)*2+i) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		for(int i=1;i<N;i++) {
			for(int j=0;j<2*N-1;j++) {
				if(N-i-1>j) System.out.print(" ");
				else if(j-(N-i)<=i*2-1) System.out.print("*");
				
			}
			System.out.println();
		}
	}
}
