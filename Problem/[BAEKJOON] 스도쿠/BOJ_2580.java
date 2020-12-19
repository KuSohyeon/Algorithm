import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2580 {
	static int [][] map;
	static List<int []> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[9][9];
		list = new ArrayList<int []>();
		for(int i=0;i<9;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<9;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0) {
					list.add(new int []{i,j});
				}
			}
		}
		dfs(0);
	}
	private static void dfs(int num) {
		if(num == list.size()) {
			print();
			System.exit(0); // 스도쿠 판을 채우는 방법이 여럿인 경우 그 중 하나만 출력 -> 시스템 종료
		}
		int y = list.get(num)[0];
		int x = list.get(num)[1];
		for(int n=1;n<=9;n++) {
			map[y][x]=n; // 하나씩 넣어보기
			if(checkGaro(y) && checkSero(x) && checkSquare(y,x)) { // 조건 체크
				dfs(num+1);
			}
			map[y][x]=0; // 백트래킹
		}
	}
	private static boolean checkSquare(int y, int x) {
		boolean [] num = new boolean[10];
		
		if(y<3) y=0;
		else if(y<6) y=3;
		else y=6;
		
		if(x<3) x=0;
		else if(x<6) x=3;
		else x=6;
		
		for(int i=y;i<y+3;i++) {
			for(int j=x;j<x+3;j++) {
				if(map[i][j]==0) continue; // 0인 경우 아직 안채운거니까 그냥 넘기기
				if(num[map[i][j]]) return false; // 하나라도 중복되는 수가 있다면 false 리턴
				num[map[i][j]]=true;
			}
		}
		return true; // 모든 조건이 맞을 경우 true 리턴
	}
	private static boolean checkSero(int x) {
		boolean [] num = new boolean[10];
		for(int i=1;i<=9;i++) {
			if(map[i-1][x]==0) continue;
			if(num[map[i-1][x]]) return false;
			num[map[i-1][x]]=true;
		}
		return true;
	}
	private static boolean checkGaro(int y) {
		boolean [] num = new boolean[10];
		for(int i=1;i<=9;i++) {
			if(map[y][i-1]==0) continue;
			if(num[map[y][i-1]]) return false;
			num[map[y][i-1]]=true;
		}
		return true;
	}
	private static void print() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
