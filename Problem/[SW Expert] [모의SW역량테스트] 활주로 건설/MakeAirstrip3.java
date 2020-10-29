//[SWEA] 활주로 건설
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeAirstrip3 {
	static int N,X,result;
	static int [][] map,rmap;
	static boolean [] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			rmap = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++){
					rmap[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0; //tc마다 초기화

			
			for(int i=0;i<N;i++) { // 교차하면서 검사
				// 세로 검사
				if(check(map[i])) result++;
				// 가로 검사
				if(check(rmap[i])) result++;
				
			}
			
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
private static boolean check(int [] road) {
		
		int beforeHeight, size;
		beforeHeight = road[0];
		size=1;
		for(int j=1;j<N;j++) {
			// 1. 이전칸과 현재칸의 높이가 같은지
			if(beforeHeight==road[j]) {
				size++;
			}
			// 2. 현재칸이 이전칸보다 높이가 1 높은경우(오르막 경사로 설치가능한지 체크)
			else if(beforeHeight+1 == road[j]) {
				if(size<X) return false;
				beforeHeight++;
				size = 1;
			}
			// 3. 현재칸이 이전칸보다 높이가 1 낮은 경우(내리막 경사로 설치가능한지 체크)
			else if(beforeHeight-1 == road[j]) {
				int count=0;
				for(int k=j;k<N;k++) {
					if(road[k] != beforeHeight-1) break;
					count++; // 이전칸의 높이와 1차이 낮은 연속된 평탄화 지형의 길이를 카운트
				}
				if(count<X) return false;
				j += X-1;
				beforeHeight --;
				size = 0;
			}
			// 4. 높이가 2이상 차이나는 경우
			else {
				return false;
			}
		}
		return true;
	}
	
}
