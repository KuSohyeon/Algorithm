import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//백준(1931번) - 회의실 배정
/*
11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14
* 출력 : 4
 */
public class Conference {
	static int N;
	static Data [] conference;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		conference = new Data[N];
		int start,end;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			conference[i] = new Data(start,end);
		}
		
		Arrays.sort(conference);
		
		
//		System.out.println(Arrays.toString(conference)); //정렬 확인용
		
		//첫 회의는 무조건 사용할거야.
		int cnt=1;//그래서 1부터 시작
		start = conference[0].start;
		end = conference[0].end;
//		System.out.println(conference[0]); //확인용
		for(int i=1;i<conference.length;i++) {
			if(conference[i].start<end) {//가지치기
				continue;
			}
			if(conference[i].start>=end) {
//				System.out.println(conference[i]); //확인용
				end = conference[i].end;
				cnt++;
			}
		}
		
		
		System.out.println(cnt);
		br.close();
		
	}
	
	static class Data implements Comparable<Data> {
		int start;
		int end;
		
		public Data(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
		}
		@Override
		public int compareTo(Data o) {
			if(this.end ==o.end) return Integer.compare(this.start,o.start);//시작시간도 오름차순으로 정렬
			return Integer.compare(this.end, o.end);//오름차순으로 정렬
		}
		
		
	}
}
