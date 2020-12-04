//[SWEA] 수정이의 타일자르기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class CutTileWithSujeong {
	static int N,M,result;
	static Integer [] paper;
	static List<Paper> list;
	static class Paper{
		int x;
		int y;
		public Paper(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Paper [lo=" + x + ", sh=" + y + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 1;
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			paper = new Integer[N];
			list = new ArrayList<Paper>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				paper[i]=(int) Math.pow(2, Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(paper,Collections.reverseOrder()); // 큰 종이 부터 자르기
			
			buy(); // 종이 사러 가기
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void buy() {
		list.add(new Paper(M,M)); // 일단 제일 처음에 한장 무조건 필요함
		
		for(int i=0;i<N;i++) {
			boolean flag = false; // 종이 샀는지 안샀는지 확인할 flag
			for(int j=0;j<list.size();j++) { //리스트 다 돌면서 있는걸로 쓸 수 있는지 확인
				Paper now = list.get(j);
				
				if(now.x>=paper[i] && now.y>=paper[i]) { // 만약 있는 걸로 쓸 수 있으면
					list.remove(j); // 일단 쓴거 처리
					int x = now.x - paper[i];
					int y = now.y - paper[i];
					flag = true;
					if(x==0 && y==0) break; // 다 쓰면 리스트에 넣어줄 필요가 없음
					if(y==0) { // 세로가 딱 맞는 경우
						list.add(new Paper(x,now.y));
					}else if(x==0) { // 가로가 딱 맞는 경우
						list.add(new Paper(now.x, y));
					}else { // 가로 세로가 남는경우는 직사각형으로 잘라서 넣어주기
						list.add(new Paper(x, now.y));
						list.add(new Paper(paper[i],y));
					}
					break;
				}
			}
			if(!flag) { // 만약 가지고 있는 종이 중 맞는게 없을 경우 하나 새로 사야함
				result ++; // 종이 하나 추가해주고
				// 위에서 했던식으로 처리
				int x = M - paper[i];
				int y = M - paper[i];
				flag = true;
				if(x==0 && y==0) continue;
				if(y==0) {
					list.add(new Paper(x,M));
				}else if(x==0) {
					list.add(new Paper(M, y));
				}else {
					list.add(new Paper(x, M));
					list.add(new Paper(paper[i],y));
				}
			}
		}
	}
}
