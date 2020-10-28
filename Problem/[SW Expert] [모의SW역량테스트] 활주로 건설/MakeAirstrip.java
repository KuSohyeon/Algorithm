//[SWEA] 활주로 건설
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeAirstrip {
	static int N,X,result;
	static int [][] map;
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
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++){
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0; //tc마다 초기화
			// 세로 먼저 검사
			for(int i=0;i<N;i++) {
				if(Icheck(i)) {
					result++;
				}
			}
			
			// 가로 먼저 검사
			for(int j=0;j<N;j++) {
				if(Jcheck(j)) {
					result++;
				}
			}
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
	private static boolean Jcheck(int j) {
		v = new boolean[N];
		
		for(int i=0;i<N-1;i++) {
			if(map[i][j]==map[i+1][j]) continue;
			if(Math.abs(map[i][j]-map[i+1][j])>1) return false;
			//경사로의 높이가 1인 경우
			if(map[i][j]>map[i+1][j]) {//현재가 다음보다 더 높은경우
				for(int x=1;x<=X;x++) {
					if(i+x>=N) return false; // 경사의 길이가 배열의 범위 밖으로 넘어가면 건설X
					if(map[i+x][j]>map[i][j]+1 || v[i+x]) return false; // 이미 활주로 설치했거나 경사로 설치 못할 경우 건설 X
					v[i+x]=true; // 경사로 건설
				}
			}else {// 현재가 다음보다 더 작을 경우
				for(int x=0;x<X;x++) {
					if(i-x<0) return false;// 경사의 길이가 배열의 범위 밖으로 넘어가면 건설X
					if(map[i+1][j]-map[i-x][j]!=1 || v[i-x]) return false;// 이미 활주로 설치했거나 경사로 설치 못할 경우 건설 X
					v[i-x]=true;// 경사로 건설
				}
			}
			
		}
		
		return true;
	}
	private static boolean Icheck(int i) {
		v = new boolean[N];
		
		for(int j=0;j<N-1;j++) {
			if(map[i][j]==map[i][j+1]) continue;
			if(Math.abs(map[i][j]-map[i][j+1])>1) return false;
			//경사로의 높이가 1인 경우
			if(map[i][j]>map[i][j+1]) {//현재가 다음보다 더 높은경우
				for(int x=1;x<=X;x++) {
					if(j+x>=N) return false;
					if(map[i][j+x]>map[i][j]+1 || v[j+x]) return false;
					v[j+x]=true;
				}
			}else {// 현재가 다음보다 더 작을 경우
				for(int x=0;x<X;x++) {
					if(j-x<0) return false;
					if(map[i][j+1]-map[i][j-x]!=1 || v[j-x]) return false;
					v[j-x]=true;
				}
			}
			
		}
		
		return true;
	}
}
