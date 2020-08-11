//[SW Expert] [S/W 문제해결 기본] 1일차 - 최빈수 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class TheMostFrequent {
	static int [] score;//점수 배열 만들어주기
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			String num = br.readLine();//tc 받아줄 용(필요없음)
			
			score = new int[101];//0-100점 몇명인지 체크할 배열 만들기
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			
			for(int i=0;i<1000;i++) {
				score[Integer.parseInt(st.nextToken())]++;//각 배열의 인덱스 값이 들어오면 ++해주기
			}

			int max =0,cnt=0;//tc마다 초기화 해주기
			
			for(int i=0;i<=100;i++) {
				if(cnt<score[i]) {
					max=i; //맥스에는 인덱스 넣기(최빈수)
					cnt = score[i];//몇개인지 넣기
				}
				if(cnt==score[i]) {//최빈수가 여러개일 경우에
					if(max<i) {//가장 큰 점수를 출력하라
						max=i;
					}
				}
			}
			System.out.println("#"+tc+ " "+ max);
		}
	}
}
