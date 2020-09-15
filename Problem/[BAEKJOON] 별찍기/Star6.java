/*
*********
 *******
  *****
   ***
    *
 */

import java.util.Scanner;

public class Star6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		
		for(int i=0;i<N;i++) {
			for(int z=0;z<i;z++) {
				sb.append(" ");
			}
			for(int j=2*(N-1-i);j>=0;j--) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
