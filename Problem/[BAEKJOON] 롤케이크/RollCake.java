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

		cnt = new Data[N+1]; 
		cnt[0]=new Data();//1부터 넣어줄꺼니까 얘는 앞으로 빼주기
		for(int i=1;i<=N;i++) {
			P = sc.nextInt();
			K = sc.nextInt();

			expected = K-P+1;//받기로 기대 될 케이크 수 
			
			if(expectedMax<expected) {//만약 max값보다 현재 값이더 크면 바꿔주기
				expectedMax = expected;
				cakeman = i;//그리고 케이크 받는 방청객의 인덱스값 넣어주기
			}
			
			for(int j=P;j<=K;j++) {
				if(rollcake[j]!= 0) {//케이크가 0이 아니면 이미 다른 방청객한테 할당된 경우니까 continue
					continue;
				}
				rollcake[j]=i;
			}
			
			int count=0;
			for(int j=1;j<L;j++) {
				if(rollcake[j]==i) {
					count++;
				}
			}
			cnt[i]=new Data(i,count);
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
