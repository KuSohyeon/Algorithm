//정올(1828번) - 냉장고(Greedy)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Refrigerator {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		Data [] temp = new Data[N];
		int tem1, tem2;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			tem1 = Integer.parseInt(st.nextToken());
			tem2 = Integer.parseInt(st.nextToken());
			
			temp[i] = new Data(tem1,tem2);
		}
		Arrays.sort(temp); //정렬
		
//		System.out.println(Arrays.toString(temp)); //정렬 확인용
		
		int cnt = 1; //일단 처음꺼는 무조건 넣어주기
		
		int end = temp[0].end; //end에 현재 냉장고 상한값 넣어주기
		for(int i=1;i<N;i++) {
			if(temp[i].start>end) { //만약 현재 냉장고의 하한 온도가 이전 냉장고의 상한 온도보다 크다면 냉장고 새로 사야함
				cnt++; //개수를 늘려주기
				end = temp[i].end; // 냉장고 온도를 바뀔 냉장고 상한 온도로 맞춰주기
				
			}
			
		}
		
		System.out.println(cnt);
		
		br.close();
	}
	static class Data implements Comparable<Data>{ //하한온도 상한온도 구조체 만들어서 사용하기
		int start;
		int end;
		
		@Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
		}
		
		public Data(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Data o) {
			if(this.end==o.end) return Integer.compare(this.start, o.start); //상한 온도가 같다면 하한 온도를 오름차순으로 정렬
			return Integer.compare(this.end, o.end); //냉장고의 상한 온도를 오름차순으로 정렬
		}
		
		
	}
}
