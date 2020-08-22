//[백준] 롤케이크 
/*
10
3
2 4
7 8
6 9
출력
3
1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RollCake_BufferedReader {
	static Data [] cnt;
	static int [] rollcake;
	static int L,N,P,K,expected,expectedMax,cakeman;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		L = Integer.parseInt(br.readLine());//롤케이크 길이
		rollcake = new int[L+1]; // 1부터 시작
		N = Integer.parseInt(br.readLine()); //사람 수
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			P = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			expected = K-P;
			if(expectedMax<expected) {
				expectedMax = expected;
				cakeman = i;
			}
			for(int j=P;j<=K;j++) {
				if(rollcake[j]!= 0) {
					continue;
				}
				rollcake[j]=i;
			}
		}
		
		cnt = new Data[N+1];
		cnt[0]=new Data();
		for(int j=1;j<=N;j++) {
			cnt[j]=new Data();
			int count=0;
			for(int i=1;i<L;i++) {
				if(rollcake[i]==j) {
					count++;
				}
			}
			cnt[j].index = j;
			cnt[j].cnt = count;
		}
		Arrays.sort(cnt);
		
		System.out.println(cakeman);
		System.out.println(cnt[0].index==0?cnt[1].index:cnt[0].index);
		
		br.close();

		
	}
	static class Data implements Comparable<Data>{
		int index;
		int cnt;
		
		public Data() {}
		public Data(int index, int cnt) {
			super();
			this.index = index;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Data o) {
			return -(this.cnt-o.cnt);
		}
	}
}
