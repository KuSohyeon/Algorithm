//[백준] 나무재태크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeFinancial {
	static int N,M,K;
	static int [][] map;
	static int [][] A;
	static int [] dy = {-1,1,0,0,-1,-1,1,1}; //상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	static PriorityQueue<Data> pq;
	static Queue<Data> die;
	static class Data implements Comparable<Data>{
		int i;
		int j;
		int age;
		public Data(int i, int j, int age) {
			super();
			this.i = i;
			this.j = j;
			this.age = age;
		}
		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + ", age=" + age + "]";
		}
		@Override
		public int compareTo(Data o) {
			return this.age-o.age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken()); // 나무 판 크기
		M = Integer.parseInt(st.nextToken()); // 나무 개수
		K = Integer.parseInt(st.nextToken()); // K년 후
		
		map = new int[N+1][N+1]; // r,c는 1부터 시작
		A = new int[N+1][N+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=1;j<=N;j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		pq = new PriorityQueue<Data>(); 
		die = new LinkedList<Data>();
		
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine().trim());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			pq.offer(new Data(i,j,age));
		}
		
		// 가장 처음에 양분은 모든 칸에 5만큼 들어있다
		for(int i=1;i<=N;i++) {
			Arrays.fill(map[i], 5);
		}
		
		for(int k=0;k<K;k++) {
			
			spring();
			summer();
			autumn();
			winter();

		}
		
		System.out.println(pq.size());
	}
	// 양분 뿌리기
	private static void winter() {
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				map[i][j] += A[i][j];
			}
		}
		
	}
	// 나무가 번식 8방위 5의 배수여야함
	private static void autumn() {
		List<Data> list = new ArrayList<>();
		
		while(!pq.isEmpty()) { // 있는 나무 반복
			Data cur = pq.poll();
			
			int r = cur.i;
			int c = cur.j;
			int age = cur.age;
			
			list.add(cur); // 번식 하려고 꺼냇던거 다시 넣어주기
			
			if(age%5!=0) continue; // 5의 배수인 나무만 번식
			
			for(int d=0;d<8;d++) {
				int nr = r + dy[d];
				int nc = c + dx[d];
				
				if(nr<1 || nr>N || nc<1 || nc>N ) continue;
				list.add(new Data(nr,nc,1)); // 나이가 1인 나무 번식
			}
		}
		 
		// pq에 결과 업데이트
		for(Data d : list) {
			pq.offer(d);
		}
		
		
	}
	// 여름에는 죽은 나무가 양분으로 변하게 된다
	private static void summer() {

		while(!die.isEmpty()) {
			Data cur = die.poll();
			
			// 죽은 나무마다 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
			map[cur.i][cur.j] += cur.age/2;
		}
		
	}
	// 봄에는 자신의 나이만큼 양문을 먹고 나이가 1 증가한다.
	private static void spring() {
		List<Data> list = new ArrayList<>();
		
		while(!pq.isEmpty()) { // 있는 나무 반복
			Data cur = pq.poll();
			
			int r = cur.i;
			int c = cur.j;
			int age = cur.age;
			
			if(map[r][c]<age) { // 양분이 부족한 나무는 양분을 먹지 못하고 즉시 죽는다
				die.add(new Data(r,c,age));
				continue;
			}
			
			map[r][c] -= age;
			
			list.add(new Data(r,c,age+1)); // 자라는 나무는 임시 리스트에 저장
		}
		
		// pq에 결과 업데이트
		for(Data d : list) {
			pq.offer(d);
		}
		
	}
	
}
