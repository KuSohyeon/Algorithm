//[백준] 여왕벌
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QueenBee {
	static int N,M;
	static int [][] map;
	static int [] input;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][M];
		
		input = new int[2*M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			input[zero]++; // 1씩 자라는 곳에서 ++
			input[zero+one]++; //2씩 자라는 곳에서 ++
		}
		// N이 1,000,000이기 때문에 시간마다 하면 터짐
		grow();
		// 다른것들은 결국 0행과 똑같은 값을 가짐
		others();
		
		// 처음에 맵을 1로 초기화하지 않았기 때문에 바로 1 더해서 출력
		for(int i=0;i<M;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]+1+" ");
			}
			System.out.println();
		}
	}
	private static void grow() {
			int index = 0, sum = 0;
			for(int i=M-1;i>=0;i--) {
				sum += input[index++];
				map[i][0] += sum;
			}
			for(int i=1;i<M;i++) {
				sum += input[index++];
				map[0][i] += sum;
			}
	}
	private static void others() {
		for(int i=1;i<M;i++) {
			for(int j=1;j<M;j++) {
				map[i][j]=map[0][j];
			}
		}
	}
}
