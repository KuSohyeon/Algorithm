//[백준] 컨베이어벨트 위의 로봇

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotOnTheMovingWalk {
	static int N, K, result;
	static int []map;
	static boolean [] v;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[2*N];
		v = new boolean[2*N];
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int i=0;i<2*N;i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true;
		
		while(flag){
			result ++;
			// 컨베이어 벨트 이동
			move();
			// 로봇 내리기
			drop();
			// 로봇 이동하기
			moveRobot();
			// 로봇 내리기
			drop();
			// 로봇 태우기
			In();
			// 검사하기 내구도 0 이 k가 되면 종료
			if(check()) {
				break;
			}
			
		}
		
		System.out.println(result);
	}
	// 검사하기 내구도 0 이 k가 되면 종료
	private static boolean check() {
		int cnt=0;
		for(int i=0;i<2*N;i++) {
			if(map[i]==0) {
				if(++cnt==K) return true;	
			}
		}
		return false;
		
	}
	// 로봇 태우기
	private static void In() {
		if(map[0]==0) return;
		v[0]=true;
		map[0]--;
		
	}
	// 로봇 이동하기
	private static void moveRobot() {
		for(int i=N-2;i>=1;i--) {
			boolean vnow = v[i];
			if(v[i+1] || map[i+1]==0) continue; // 앞에 로봇이 있거나 내구도가 0이라면 이동 X
			v[i+1] = vnow ; // 한칸 밀어주기
			v[i]=false; // 원래칸은 이동했으니 false 처리
			if(v[i+1]) { // 이동했으니 내구도 -1
				map[i+1]--;
			}
		}
		
	}
	// 로봇 내리기
	private static void drop() {
		if(v[N-1]) {
			v[N-1]=false;
		}
	}
	// 컨베이어 벨트 이동
	private static void move() {
		int tmp = map[0], now = tmp;
		boolean vtmp = v[0], vnow = vtmp;
		for(int i=0;i<2*N;i++) {
			now = tmp;
			tmp = map[(i+1)%(2*N)];
			map[(i+1)%(2*N)] = now ;
			vnow = vtmp;
			vtmp = v[(i+1)%(2*N)];
			v[(i+1)%(2*N)] = vnow ;
		}
		
	}
	
}
