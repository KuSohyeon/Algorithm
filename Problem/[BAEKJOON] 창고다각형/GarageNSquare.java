//[백준] 창고 다각형

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GarageNSquare {
	static List<Pair> list;
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.x, o.x);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		list = new ArrayList<>();
		
		int maxY = 0,maxX=0,area=0;
		for(int i=0;i<N;i++) {
			
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			if(maxY<y) { // 최대값 저장해주기
				maxY = y;
				maxX = x;
			}
			list.add(new Pair(x,y));
		}
		
		// x 좌표 순으로 소팅
		Collections.sort(list);
		
		int max=0;
		//앞에서 부터 최대값 까지 뒤에서 부터 최대값까지 반반 나누어서 계산해줄 것
		for(int i=0;i<N;i++) {
			if(i==0) max = i;
			if(list.get(max).y <= list.get(i).y) { // 만약 뒤에께 앞에꺼보다 크다면
				area += (list.get(i).x - list.get(max).x)*list.get(max).y; // 이전의 넓이구해서 최종 넓이에 더해주기
				max=i; // 그다음 max값 바꿔주기
			}
			if(list.get(i).x==maxX) { // 만약 찐 최대값이랑 똑같아진다면 종료
				break;
			}
		}
		// 뒤에서 부터~
		for(int i=N-1;i>=0;i--) {
			if(i==N-1) max=i;
			if(list.get(max).y<list.get(i).y) {
				area += (list.get(max).x - list.get(i).x)*list.get(max).y;
				max = i;
			}
			if(list.get(i).x==maxX) {
				break;
			}
		}
		
		area += maxY; // 마지막으로 우뚝 솟은 부분 더해주기
		
		System.out.println(area);
	}
	
	
}
