//백준(2961번) - 도영이가 만든 맛있는 음식 (부분 집합)
/*
4
1 7
2 6
3 8
4 9
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DoyoungFood {
	
	static boolean[] isSelected;
	static int [] S, B;
	static int min = Integer.MAX_VALUE,result=0,N;
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		isSelected = new boolean[N];
		S = new int[N];
		B = new int[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		comb(0,1,0);
		
		System.out.println(min);
		
		br.close();
		
	}

	private static void comb(int cnt, int result1, int result2) {
		//종료 시점
		if(cnt==N) {
			if(result1==1 && result2==0) {//아무것도 안하면 계산 X
				return;
			}
			result = Math.abs(result1-result2);
			
			if(min>result) {
				min=result;
			}
			return;
		}
		//실행
		isSelected[cnt]=true;
		comb(cnt+1,result1*S[cnt],result2+B[cnt]);
		isSelected[cnt]=false;
		comb(cnt+1,result1,result2);
		
	}
}
