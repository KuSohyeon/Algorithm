import java.io.BufferedReader;
//[백준] 빗물

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rain {
	static int[] blocks;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int h = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

		blocks = new int[width];

		st = new StringTokenizer(br.readLine().trim());
		for (int j = 0; j < width; j++) {
			blocks[j] = Integer.parseInt(st.nextToken());
		}

		solution(width, blocks);
	}

	private static void solution(int width, int[] blocks) {
		// 빗물의 양
		int rain = 0;

		// 앞에서 부터 빗물 채우기
		int index = 0;
		for(int i=1;i<width;i++) {
			if(blocks[index]>blocks[i]) continue; // 기준보다 작을 경우는 넘겨주기
			// 기준보다 클 경우
			for(int j=index+1;j<i;j++) {
				rain += blocks[index]-blocks[j]; // 채워야 할 빗물의 양 더해주고
				blocks[j]=blocks[index]; // 빗물로 채워주기(표시)
			}
			index=i; // 기준 바꿔주기
		}
		
		// 뒤에서부터 덜 채운 빗물 다시 채우기
		index = width-1;
		for(int i=width-2;i>=0;i--) {
			if(blocks[index]>blocks[i]) continue;
			for(int j=index-1;j>i;j--) {
				rain += blocks[index]-blocks[j];
				blocks[j]=blocks[index];
			}
			index=i;
		}

		System.out.println(rain);
	}
}
