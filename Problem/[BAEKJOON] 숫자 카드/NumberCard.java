//[백준] 숫자카드 
// 이분탐색

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberCard {
	static int N,M;
	static int [] sanguen;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		sanguen = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			sanguen[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sanguen);
		
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			find(num);
		}
	}
	private static void find(int num) {
		int start = 0;
		int end = N-1;
		
		while(start<=end) {
			int mid = (start+end)/2;
			if(sanguen[mid]==num) {
				System.out.print("1 ");
				return;
			}
			else if(sanguen[mid]>num) { // 중간이 찾는 숫자보다 더 크다면
				end = mid-1; // 앞에꺼를 다시 탐색하기 위해 끝 옮겨주기
				continue;
			}else { // 중간이 찾는 숫자보다 작다면
				start = mid+1; // 뒤만 보면 됨
				continue;
			}
		}
		
		System.out.print("0 ");
	}
}
