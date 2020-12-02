//[백준] 피자굽기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakePizza {
	static int D,N,start,end;
	static int [] oven, input;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		D = Integer.parseInt(st.nextToken()); // 오븐의 깊이
		N = Integer.parseInt(st.nextToken()); // 피자 반죽의 개수
		
		oven = new int[D+1];
		input = new int[N];
		
		// 오븐 입력
		st = new StringTokenizer(br.readLine().trim());
		oven[1]=Integer.parseInt(st.nextToken());
		int min = oven[1];
		
		for(int i=2;i<=D;i++) {
			oven[i]=Integer.parseInt(st.nextToken());
			min = Math.min(min, oven[i]);
			oven[i]=min; // 이렇게 하면 내림차순으로 정렬됨
		}
		
		// 피자 입력
		st = new StringTokenizer(br.readLine().trim());
		for(int i=0;i<N;i++) {
			input[i]=Integer.parseInt(st.nextToken());
		}
		
		start = 1;
		end = D+1;
		
		for(int i=0;i<N;i++) {
			find(input[i]);
			end--; // 다음 피자는 현재 피자의 위치 -1 에서 부터 탐색
			if(end<1) break; // 최상단이면 break
			start = 1; // start는 항상 최상단부터
		}
		
		System.out.println(end);
	}
	// 이분 탐색 method 
	private static void find(int i) {
		while(start<end) {
			int mid = (start+end)/2;
			if(oven[mid]<i) {
				end=mid;
			}else {
				start=mid+1;
			}
		}
		
	}
}
