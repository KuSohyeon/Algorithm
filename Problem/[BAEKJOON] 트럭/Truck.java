import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Truck {
	static int n, w, L; // n : 다리를 건너는 트럭 수, w : 다리의 길이, L : 다리의 최대 하중
	static int [] truck;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		truck = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			truck[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(Go() + w); // 마지막 트럭 이동 시간(다리의 길이) 만큼 더해주기
		
	}
	private static int Go() {
		int time = 0, weight = 0;
		Queue<Integer> bridge = new LinkedList<Integer>();
		
		for(int i=0;i<n;i++) {
			while(true) {
				if(bridge.size() == w) { // 다리에 트럭으로 꽉 차면 하나씩 빼주기
					weight -= bridge.poll();
				}
				if(truck[i] + weight <= L) break; // 빼자마자 들어갈 수 있다면 break후 다음 트럭 들어가기
				bridge.add(0); // 들어갈 수 없다면 0으로 채워주기
				time++; // 0으로 채우더라도 시간은 간다
			}
			bridge.add(truck[i]); // 다음 트럭 들어가기
			weight += truck[i];
			time++;
		}
		
		return time;
	}
}
