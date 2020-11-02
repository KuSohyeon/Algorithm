//[SWEA] [모의SW역량테스트] 무선충전

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WirelessCharge {
	static int M,A,result;
	static int [] Adir, Bdir;
	static int [][] AP;
	static int [] dy = {0,-1,0,1,0};// X 상 우 하 좌
	static int [] dx = {0,0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			M = Integer.parseInt(st.nextToken());
			A= Integer.parseInt(st.nextToken());
			
			Adir = new int[M+1];
			Bdir = new int[M+1];
			AP = new int [A][4];
			
			// 사용자의 이동 정보
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1;i<=M;i++) {
				Adir[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1;i<=M;i++) {
				Bdir[i] = Integer.parseInt(st.nextToken());
			}
			
			// AP정보
			for(int i=0;i<A;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<4;j++) {
					AP[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0; //tc마다 초기화
			go();
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void go() {
		int Ax=1, Ay=1,Bx=10,By=10;
		List<Integer> AList = new ArrayList<Integer>();
		List<Integer> BList = new ArrayList<Integer>();
		
		for(int i=0;i<=M;i++) {
			// 플레이어 위치 이동
			Ax += dx[Adir[i]];
			Ay += dy[Adir[i]];
			Bx += dx[Bdir[i]];
			By += dy[Bdir[i]];
			
			
			// BC 리스트 구하기
			AList = check(Ax,Ay);
			BList = check(Bx,By);
			
			int max = 0;
			if(AList.size()==0 && BList.size()==0) continue; // 둘 다 충전 X 경우
			else if(AList.size()==0) { // A만 충전 못할경우
				for(Integer b : BList) {
					max = Math.max(max, AP[b][3]); // BList에서 최대값 찾아주기
				}
			}else if(BList.size()==0) { // B만 충전 못하는 경우
				for(Integer a : AList) {
					max = Math.max(max, AP[a][3]); // AList에서 최대값 찾아주기
				}
			}else {
				for(Integer a : AList) {
					for(Integer b : BList) {
						if(a==b) max = Math.max(max, AP[b][3]);  // 동시에 이용할 경우 한사람만 더해주기
						else max = Math.max(max, AP[a][3]+AP[b][3]); // 아니면 각자 다른거 쓰는거 더해주기
					}
				}
			}
			result += max; // 누적
			
		}
	}
	// 이용가는한 충전국 검색하는 method
	private static List<Integer> check(int x, int y) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<A;i++) {
			int dist = Math.abs(x-AP[i][0])+Math.abs(y-AP[i][1]);
			if(dist<=AP[i][2]) {
				list.add(i);
			}
		}
		return list;
		
	}
}
