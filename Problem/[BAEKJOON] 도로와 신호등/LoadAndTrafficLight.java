//[백준] 도로와 신호등

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LoadAndTrafficLight {
	static int N,L;
	static Data [] traffic;
	static class Data{
		int loc;
		int red;
		int green;
		public Data(int loc, int red, int green) {
			super();
			this.loc = loc;
			this.red = red;
			this.green = green;
		}
		@Override
		public String toString() {
			return "Data [loc=" + loc + ", red=" + red + ", green=" + green + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 신호등의 개수 N
		L = Integer.parseInt(st.nextToken()); // 도로의 길이 L
		traffic = new Data[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			traffic[i] = new Data(l, r, g);
		}
		
		int time = 0, loc = 0, idx = 0;
		while(loc < L) {
			time++;
			loc++;
			if(check(loc)) { // 현 위치에 신호등이 있다면
				time += delay(time,idx++);
			}
		}
		
		System.out.println(time);
		
	}
	// 신호 확인 후 대기시간 반환하는 method
	private static int delay(int time, int i) {
		int trafficTime = traffic[i].green + traffic[i].red;
		int deleyTime = time % trafficTime;
		if(deleyTime <= traffic[i].red) {
			return traffic[i].red-deleyTime;
		} else {
			return 0;
		}
	}
	// 현 위치에 신호등이 있는지 판단하는 method
	private static boolean check(int loc) {
		for(int i=0;i<N;i++) {
			if(traffic[i].loc == loc) return true;
		}
		return false;
	}
}
