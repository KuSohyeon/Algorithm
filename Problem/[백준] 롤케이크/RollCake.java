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

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class RollCake {
	static Data [] cnt;
	static int [] rollcake;
	static int L,N,P,K,expected,expectedMax,cakeman;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		L = sc.nextInt();//롤케이크 길이
		rollcake = new int[L+1]; // 1부터 시작
		N = sc.nextInt(); //사람 수
		
		for(int i=1;i<=N;i++) {
			P = sc.nextInt();
			K = sc.nextInt();
			
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
