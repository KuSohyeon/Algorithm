//[SW Expert] [모의SW역량테스트] 요리사
/*
10
4
0 5 3 8
4 0 4 1
2 5 0 3
7 2 3 0
출력 :
2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Chef {
	static int [][] S; //시너지 배열
	static int [] A; //음식 저장할 배열
	static int [] B;
	static int [] tmp;
	static int N;
	static int result, rA, rB,sum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc=1;tc<=T;tc++) {
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());

			S = new int[N][N];
			A =new int[N/2];//음식A
			B =new int[N/2];//음식B
			tmp = new int[2];//두개씩 저장해줄거

			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					S[i][j]= Integer.parseInt(st.nextToken());
				}
			}

			comb(0,1);
			
			System.out.println("#"+tc+" "+result);
		}
	}
	//음식 재료 반으로 나눠주는 경우의 수 (조합) 메소드
	private static void comb(int cnt, int start) {

		if(cnt==N/2){
			//B도 구해주기 = A가 선택 안한거
			boolean [] v = new boolean[N+1];
			for(int i=0;i<N/2;i++) {
				v[A[i]]=true;
			}
			int idx = 0;
			for(int i=1;i<N+1;i++) {
				if(!v[i]) {
					B[idx++]=i;
				}
			}
			cal(A,B); //A,B 만들어서 계산하러 보내주기

			return;
		}
		for(int i=start;i<=N;i++) {

			A[cnt]=i;
			comb(cnt+1,i+1);

		}

	}
	//음식 중 2가지 뽑아서 각 음식 점수에 더해주는 메소드
	private static void comb2(int cnt,int start,int [] input) {//tmp에 A배열의 순열 넣어주기
		if(cnt==2) {//2개만 뽑을거니까 종료
			if(input == A) {
				rA += S[tmp[0]-1][tmp[1]-1]+S[tmp[1]-1][tmp[0]-1];
				return;
			}
			if(input == B) {
				rB += S[tmp[0]-1][tmp[1]-1]+S[tmp[1]-1][tmp[0]-1];
				return;
			}
		}
		for(int i=start;i<N/2;i++) {
			tmp[cnt]= input[i];
			comb2(cnt+1,i+1,input);
		}
	}
	// A, B 재료 나눴으면 계산해주기
	private static void cal(int[] A,int [] B) {
		// 매 호출마다 rA,rB 초기화
		rA = 0;
		rB =0;
		// 음식 점수구하는 메소드 호출
		comb2(0,0,A);
		comb2(0,0,B);
		// 절대값 차이 구해주기
		sum = Math.abs(rA - rB);
		// min 저장해주기
		result = Math.min(result, sum);
		return;

	}


}
