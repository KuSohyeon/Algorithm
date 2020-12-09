//[백준] A와 B
/*
 * 1. 문자열의 뒤에 A를 추가한다.
 * 2. 문자열을 뒤집고 뒤에 B를 추가한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AandB {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String T = br.readLine();
		
		StringBuilder sb = new StringBuilder(T);
		
		int lenS = S.length();
		int lenT = T.length();
		
		while(lenT > lenS) {
			lenT--;
			
			if(sb.charAt(lenT)=='A') { // A는 끝에만 붙인거
				sb.delete(lenT, lenT+1);
			}else { // B는 뒤집어서 끝에 붙인거
				sb.delete(lenT, lenT+1);
				sb.reverse();
			}
			
			if(sb.toString().equals(S)) {
				System.out.println(1);
				return;
			}
		}
		
		System.out.println(0);
	}
}
