/*
    *
   **
  ***
 ****
*****
 */
import java.util.Scanner;

public class Star2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		
		for(int i=0;i<N;i++) {
			for(int j=N-1;j>=0;j--) {
				if(j>i) {
					sb.append(" ");
				}else sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
