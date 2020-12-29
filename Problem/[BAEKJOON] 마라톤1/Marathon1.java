//[백준] 마라톤1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Marathon1 {
	static int N, min, total;
	static int [][] checkPoint;
	static int [] distance;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		checkPoint = new int[N][2];
		distance = new int[N];
		total = 0;
		min = 987654321;
		
		st = new StringTokenizer(br.readLine());
		checkPoint[0][0] = Integer.parseInt(st.nextToken());
		checkPoint[0][1] = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<N;i++) { // 1부터 N-1번까지 반복
			st = new StringTokenizer(br.readLine());
			checkPoint[i][0] = Integer.parseInt(st.nextToken());
			checkPoint[i][1] = Integer.parseInt(st.nextToken());
			distance[i] = Math.abs(checkPoint[i-1][0]-checkPoint[i][0]) + Math.abs(checkPoint[i-1][1]-checkPoint[i][1]); // 각 포인트마다의 거리를 distance 배열에 저장
			total += distance[i]; //체크포인트간의 거리를 누적
		}
		
		for(int i=1;i<N-1;i++) {
			int tmp = total - distance[i] - distance[i+1]; // 건너뛸 체크포인트에서 직전과 직후 거리 빼주기
			tmp += Math.abs(checkPoint[i-1][0]-checkPoint[i+1][0]) + Math.abs(checkPoint[i-1][1]-checkPoint[i+1][1]); // 전 체크포인트와 후 체크포인트 거리 구해서 더해주기
			min = Math.min(min, tmp); // 최소값으로 업데이트
		}
		
		System.out.println(min);
		
	}
}
