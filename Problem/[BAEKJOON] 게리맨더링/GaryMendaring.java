//[백준] 17471번 - 게리맨더링
/*
6
5 2 3 4 1 2
2 2 4
4 1 3 6 5
2 4 2
2 1 3
1 2
1 2
# 1
6
2 3 4 5 6 7
2 2 3
2 1 3
2 1 2
2 5 6
2 4 6
2 4 5
# 9
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class GaryMendaring {
	static int N,result=Integer.MAX_VALUE;
	static int [] people;
	static int [][] map,copy;
	static List<Integer> A,B;
	static boolean [] isSelected;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine().trim());
		
		people = new int[N+1]; // 인구 수 받아줄 배열
		map = new int[N+1][N+1]; // 인접 행렬
		isSelected = new boolean[N+1]; // 부분집합에 사용
		copy = new int[N+1][N+1]; //우리구역 정보만 있는 배열 이용할거니까 copy 배열 생성
		A = new ArrayList<Integer>();
		B = new ArrayList<Integer>();
		
		for(int i=1;i<=N;i++) {
			people[i]=Integer.parseInt(st.nextToken());
		}
		// 입력 형태 인접 리스트로 변환해서 넣어주기
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				map[i][Integer.parseInt(st.nextToken())]=1;
			}
		}
		
		subset(1);
		
		if(result!=Integer.MAX_VALUE) {
			System.out.println(result);
		}else System.out.println("-1");
	}
	private static void subset(int cnt) {
		if(cnt==N) {
			A.clear();
			B.clear();
			for(int i=1;i<=N;i++) {
				if(isSelected[i]) {
					A.add(i);
				}
				else B.add(i);
			}
			
			if(A.size() == N || A.size() == 0) return; // 선거구별 구역개수는 1 이상
			
			// 인접한 구역인지 찾는 메소드 
			if(canGo(A) && canGo(B)) {
				result = Math.min(result, cal()); // 지역별 인구 차 비교
			}else return;
			
			//만약 차가 0이면 이보다 작을 수는 없으니 바로 출력 후 종료
			if(result==0) {
				System.out.println("0");
				System.exit(0);
			}
			return;
		}
		isSelected[cnt]=true;
		subset(cnt+1);
		isSelected[cnt]=false;
		subset(cnt+1);
		
	}
	// 모든 정점이 연결되어 있는지 확인하는 메소드
	private static boolean canGo(List<Integer> list) {
		
		List<Integer> compare;
		if(list == A) {//A가 가능한지 확인한거면
			compare = B; //B 요소 다 0으로 만들어줄꺼니까 compare에 B 넣어주기
		}else {
			compare = A;
		}
		
		// 내 구역만 가지고 있는 인접행렬 만들어주기
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(compare.contains(i) || compare.contains(j)) { //다른 요소에 있는거면 다 0으로 만들어주기
					copy[i][j]=0;
				}
				else {
					copy[i][j]=map[i][j]; 
				}
			}
		}
		
		//Prim이용해서 MST 만들기
		int [] distance = new int[N+1];
		boolean [] v = new boolean[N+1];
		int cnt=0, min = 0, current=0;
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[list.get(0)] = 0;
		
		for(int c=1;c<=N;c++) {
			min = Integer.MAX_VALUE;
			
			for(int i=1;i<=N;i++) {
				if(!v[i] && min>distance[i]) {
					min = distance[i];
					current = i;
					cnt++;
				}
			}
			
			v[current] = true;
			
			
			for(int i=1;i<=N;i++) {
				if(!v[i] && copy[current][i]==1 && distance[i]> copy[current][i]) {
					distance[i] = copy[current][i];
				}
			}
			
		}
		
		// 만약 정점의 개수가 list와 같다면 다 연결된거니까 true 리턴
		if(cnt == list.size()) return true;
		// 그게 아니라면 못가는 곳이 생기는 거니까 false 리턴
		return false;
	} 
	// 선거구 인구 차 구하기
	private static int cal() {
		int aPeople = 0,bPeople = 0;
		for(Integer a : A) {
			aPeople += people[a];
		}
		for(Integer b : B) {
			bPeople += people[b];
		}
		
		return Math.abs(aPeople-bPeople);
	}
}
