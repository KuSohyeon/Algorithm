/*
    *
   ***
  *****
 *******
*********
 *******
  *****
   ***
    *
 */
import java.util.Scanner;

public class Star7 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			for(int j=n-i-1;j>0;j--) {
				sb.append(" ");
			}
			for(int j=0;j<2*i+1;j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		for(int i=n-2;i>=0;i--) {
			for(int j=0;j<n-1-i;j++) {
				sb.append(" ");
			}
			for(int j=2*i+1;j>0;j--) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
