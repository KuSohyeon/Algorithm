//[백준] 경비원
/*
 * 1 : 북  | 2: 남 | 3 : 서 | 4 : 동
 * 마지막째 줄에는 동근이의 위치가 상점의 위치와 같은 방식으로 주어진다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2564_경비원 {
	static int W,H,store,dongD,dongF,result;
	static int [][] loc;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		store = Integer.parseInt(br.readLine());

		loc = new int[store][2];

		for(int i=0;i<store;i++) {
			st = new StringTokenizer(br.readLine().trim());
			loc[i][0]=Integer.parseInt(st.nextToken());
			loc[i][1]=Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine().trim());
		dongD = Integer.parseInt(st.nextToken());
		dongF = Integer.parseInt(st.nextToken());

		getDistance();

		System.out.println(result);

	}
	// 1 : 북  | 2: 남 | 3 : 서 | 4 : 동
	// (북, 0) 기준으로 해서 시계방향의 거리를 구한 다음 
	// 상점 거리 - 동근이의 위치 -> 시계 방향
	// 전체 거리 - 시계방향 거리 -> 반시계 방향
	// 둘 중 최소값으로 더해준다.
	private static void getDistance() {
		// 동근이의 거리
		int dongDis = 0 ,total = 2*H + 2*W;
		if(dongD==1) {
			dongDis = dongF;
		}else if(dongD==2) {
			dongDis = W + H + W - dongF;
		}else if(dongD == 3) {
			dongDis = W*2 + H*2 - dongF;
		}else {
			dongDis = W + dongF;
		}
		// 상점의 거리
		for(int i=0;i<store;i++) {
			int l = loc[i][0];
			int distance=0;
			switch (l) {
			case 1:
				distance = loc[i][1];
				break;
			case 2:
				distance = W + H + W-loc[i][1];
				break;
			case 3:
				distance =  W*2 + H*2 -loc[i][1];
				break;
			case 4:
				distance = W + loc[i][1];
				break;
			}
			// 상점 거리 - 동근 거리
			distance -= dongDis;
			distance = Math.abs(distance); // 음수나올까봐 절대값 사용
			// 최소값으로 업데이트
			result += Math.min(distance, total-distance);
		}


	}


}
