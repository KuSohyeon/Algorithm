//[백준] 돌림판(Small)
/*
 * P는 돌림판의 어떤 임의의 부분
 * 1. P의 왼쪽, 오른쪽, P가 모두 같거나 다르다면 P에 칠해진 색을 파란색으로 바꾼다.
 * 2. 빨 2, 초 1/ 초 2, 파 1/ 파 2, 빨 1 인경우 빨간색으로, 그렇지 않을 경우 초록색으로 바꾼다.
 * 
 * 이와 같은 방법으로 K-1번 더 바꾸었다.
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TurningPlate {
	static int N, K, result;
	static int [] color;
	static int [] turning;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		color = new int[3];
		turning = new int[N]; // R=0 G=1 B=2로 치환해서 int형 배열로 받기
		String input = br.readLine();
		
		for(int i=0;i<N;i++) {
			char c = input.charAt(i);
			int num = -1;
			if(c=='R') num=0;
			else if(c=='G') num=1;
			else num =2;
			turning[i]=num;
		}
		
		// K번 반복
		for(int i=0;i<K;i++) {
			// 매 번마다 바뀐 색을 받아줄 배열 생성
			int [] newturn = new int[N];
			// 임의의 P -> idx 마다 다 해주기
			for(int p=0;p<N;p++) {
				Arrays.fill(color, 0); // color 배열 초기화
				int left = p-1<0?p-1+N:p-1;
				int right = p+1==N?0:p+1;
				
				color[turning[left]]++;
				color[turning[right]]++;
				color[turning[p]]++;
				
				// 색깔 바꿔주기
				newturn[p] = change(color);
			}
			// 한 번 돌렸으니 업데이트
			turning = Arrays.copyOfRange(newturn, 0, N);
		}
		
		// 출력 method
		count(turning);
		
	}
	// 출력 method
	private static void count(int[] turning) {
		int [] cnt = new int[3];
		for(int i=0;i<N;i++) {
			if(turning[i]==0) {
				cnt[0]++;
			}else if(turning[i]==1) {
				cnt[1]++;
			}else cnt[2]++;
		}
		
		System.out.println(cnt[0] +" "+ cnt[1]+" "+cnt[2]);
	}
	// 색깔 바꿔주는 method
	private static int change(int[] color) {
		if(color[0]==1 && color[1]==1 && color[2]==1) return 2;
		if(color[0]==3 || color[1]==3 || color[2]==3) return 2;
		if(color[0]==2 && color[1]==1) return 0;
		if(color[1]==2 && color[2]==1) return 0;
		if(color[2]==2 && color[0]==1) return 0;
		
		return 1;
	}
}
