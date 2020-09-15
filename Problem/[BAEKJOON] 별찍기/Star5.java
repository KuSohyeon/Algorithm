/*
    *
   ***
  *****
 *******
*********
 */
import java.util.Scanner;

public class Star5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int i=1;i<=N;i++) {
			for(int z=N;z>i;z--) {
				System.out.print(" ");
			}			
			for(int j = 0;j<2*i-1;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
