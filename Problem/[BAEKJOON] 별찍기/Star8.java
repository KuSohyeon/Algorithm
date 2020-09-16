/*
*        *
**      **
***    ***
****  ****
**********
****  ****
***    ***
**      **
*        *
 */
import java.util.Scanner;

public class Star8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<=i;j++) {
				sb.append("*");
			}
			for(int j=2*(N-1-i);j>0;j--) {
				sb.append(" ");
			}
			for(int j=i;j>=0;j--) {
				sb.append("*");
			}
			sb.append("\n");
		}
		for(int i=N-2;i>=0;i--) {
			for(int j=i;j>=0;j--) {
				sb.append("*");
			}
			for(int j=2*(N-1-i);j>0;j--) {
				sb.append(" ");
			}
			for(int j=0;j<=i;j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
