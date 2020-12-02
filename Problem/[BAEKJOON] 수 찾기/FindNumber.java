//[백준] 1920번 수찾기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FindNumber {
	static int [] A;
	static int N,M;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A); // 이분 탐색의 조건 = sort
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<M;i++) {
			int input=Integer.parseInt(st.nextToken());
			System.out.println(find(input));
		}
		
		
		
		
		
	}
	private static int find(int i) {
		int start = 0, end=N-1, mid=0;
		
		while(start<=end) {
			mid = (start+end)/2;
			if(i == A[mid]) { // 원하는 수를 찾았다면 1 반환
				return 1;
			}else if(Integer.compare(i, A[mid])<0) { // 내가 찾는 수보다 중간값이 작다면
				end = mid-1; // 그 앞을 탐색할꺼니 마지막을 mid-1로 바꿔주기
			}else {// 내가 찾는 수보다 중간 값이 크다면
				start = mid+1; // 뒤를 탐색
			}
		}
		
		return 0; // 못찾으면 0 반환
	}
}
