//[백준] Z
// 분할 정복 + 규칙

import java.util.Scanner;

public class Z2 {
	static int N,R,C,cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		C = sc.nextInt();
		
		int n = N;
		int r = 0, c = 0;
		cnt = 0;
		
		// 재귀를 이용하니까 시간 초과 -> 하나하나 다 탐색한다니보다는 몇 사분면에 있는지를 확인해서 범위 좁혀주기
		while(n>1) {
			int next = (int)Math.pow(2,--n); // 배열의 크기 반으로 줄여주기
			if(R<r+next && C<c+next) { // 1사분면
				continue; // 그대로 넘겨주기(인덱스 변화 X)
			}else if(R<r+next && C>=c+next) { // 2사분면
				c += next; 
				cnt += (int)Math.pow(2, 2*n); // 1사분면 만큼 넘겨줌
				continue;
			}else if(R>=r+next && C<c+next) { // 3사분면
				r += next;
				cnt += (int)Math.pow(2, 2*n)*2; // 2개의 사분면 만큼 넘겨줌
				continue;
			}else { // 4사분면
				r += next;
				c += next;
				cnt += (int)Math.pow(2, 2*n)*3; // 3개의 사분면만큼 넘겨줌
			}
		}
		
		// 좌표가 있는 2*2 배열만 확인해주면 끝
		count(r,c);
		
	}
	private static void count(int r, int c) {
		if(r == R && c == C) {
			System.out.println(cnt);
			System.exit(0);
		}else if(r == R && c+1 == C) {
			System.out.println(cnt+1);
			System.exit(0);
		}else if(r+1==R && c == C) {
			System.out.println(cnt+2);
			System.exit(0);
		}else if(r+1==R && c+1 == C) {
			System.out.println(cnt+3);
			System.exit(0);
		}
	}
}
