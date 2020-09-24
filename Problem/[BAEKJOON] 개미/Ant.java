import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ant {
	static int w, h, p, q, second;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		second = Integer.parseInt(br.readLine());
		int dir[] = {1, -1}; // 처음 방향은 무조건 우상 부딪힐때마다 토글
		int d = 0;
		// 가로와 세로를 나눠서 생각
		int x = second % (2*w); // 가로길이 2배만큼 시간이 지날때마다 x는 제자리 
		int y = second % (2*h); 
		// 가로 움직이기
		for (int i = 0; i < x; i++) {
			p += dir[d%2];
			if(p == 0 || p == w)
				d++;
		}
		// 세로 움직이기
		d = 0;
		for (int i = 0; i < y; i++) {
			q += dir[d%2];
			if(q == 0 || q == h)
				d++;
		}
		
		System.out.println(p + " " + q);
	}

}