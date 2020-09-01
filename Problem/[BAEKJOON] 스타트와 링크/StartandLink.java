//[백준] 스타트와 링크
/*
4
0 1 2 3
4 0 5 6
7 1 0 2
3 4 5 0
# 0
6
0 1 2 3 4 5
1 0 2 3 4 5
1 2 0 3 4 5
1 2 3 0 4 5
1 2 3 4 0 5
1 2 3 4 5 0
# 2
8
0 5 4 5 4 5 4 5
4 0 5 1 2 3 4 5
9 8 0 1 2 3 1 2
9 9 9 0 9 9 9 9
1 1 1 1 0 1 1 1
8 7 6 5 4 0 3 2
9 1 9 1 9 1 0 9
6 5 4 3 2 1 9 0
# 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StartandLink {
	static int N; // 사람 수
	static int [][] power; // 능력치 배열
	static int min = Integer.MAX_VALUE;
	static int [] S,L,temp;	 //S팀 L팀 2명씩 비교할 때 사용할 배열
	static int Sscore, Lscore; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		N = Integer.parseInt(br.readLine());
		
		power = new int[N+1][N+1]; //입력 받을 배열
		S = new int[N/2]; //스타트팀 
		L = new int[N/2]; //링크팀
		temp = new int[2];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=1;j<=N;j++) {
				power[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,1);
		
		System.out.println(min);
	}
	private static void comb(int cnt,int start) {
		if(cnt==N/2) {
			boolean [] v = new boolean[N+1];
			for(int i=0;i<N/2;i++) {
				v[S[i]]=true;
			}
			//L팀도 구해주기
			int idx = 0;
			for(int i=1;i<N+1;i++) {
				if(!v[i]) {
					L[idx++]=i;
				}
			}

			min = Math.min(min, calPower()); 
			
			if(min==0) return;
			return;
		}
		for(int i=start;i<=N;i++) {
			S[cnt] = i;
			comb(cnt+1,i+1);
		}
		
	}
	private static int calPower() {
		//일단 매 S와 L이 바뀔때마다 점수 초기화
		Sscore=0;
		Lscore=0;
		//점수 구하는 메소드
		comb2(0,0,S);
		comb2(0,0,L);
		
		int cha = Math.abs(Sscore - Lscore); //두 팀 점수의 절대값
		
		return cha;
	}

	static void comb2(int cnt, int start, int [] input) { //조합 이용해서 2명씩 더해주기
		if(cnt==2) {
			if(input==S) {
				Sscore += power[temp[0]][temp[1]] + power[temp[1]][temp[0]];
			}else {
				Lscore += power[temp[0]][temp[1]] + power[temp[1]][temp[0]];
			}
			return;
		}
		for(int i=start;i<N/2;i++) {
			temp[cnt]=input[i];
			comb2(cnt+1,i+1,input);
		}
	}
	
}
