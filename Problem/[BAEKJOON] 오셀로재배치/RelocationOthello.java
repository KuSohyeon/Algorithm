//[백준] 오셀로 재배치
//배치된 말 중 임의의 2개의 말을 골라 서로의 위치를 바꾼다.
//말 1개를 들어 뒤집어 놓아 색상을 변경한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RelocationOthello {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			String one = br.readLine();
			String two = br.readLine();
			
			int black = 0, white = 0;
			
			for(int i=0;i<N;i++) {
				if(one.charAt(i)!=two.charAt(i)) {
					if(two.charAt(i)=='B') {
						black++;
					}else {
						white++;
					}
				}
			}
			
			// 서로 바꾸는 방법이 있으니 둘 중에 큰 걸로 리턴하면 됨
			System.out.println(Math.max(black, white));
		}
	}
}
