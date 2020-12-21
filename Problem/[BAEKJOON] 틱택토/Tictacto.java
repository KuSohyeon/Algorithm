//[백준] 틱택토 
// 영어 철자 오타...

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tictacto {
	static char [][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while(true) {
			String s = br.readLine();
			if(("end").equals(s)) {
				break;
			}
			
			map = new char[3][3];
			int idx = 0;
			int xxx=0, ooo=0;
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++,idx++) {
					map[i][j] = s.charAt(idx);
					if(map[i][j]=='X') {
						xxx++;
					}else if(map[i][j]=='O') {
						ooo++;
					}
				}
			}
			// x와 o의 차이가 1보다 큰 경우, o가 더 많은 경우, 둘다 3보다 작아서 빙고 안만들어지는 경우 pass
			if(xxx-ooo>1 || xxx<ooo || (xxx<3 && ooo<3)) { 
				sb.append("invalid").append("\n");
				continue;
			}
			
			check(xxx,ooo);
			
		}
		System.out.println(sb.toString());
	}
	private static void check(int x, int o) {
		int xxx=0,ooo=0;
		// 가로체크
		for(int i=0;i<3;i++) {
			if(map[i][0]=='.') continue;
			if(map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
				if(map[i][0]=='X') xxx++;
				else ooo++;
			}
		}
		// 세로 체크
		for(int j=0;j<3;j++) {
			if(map[0][j]=='.') continue;
			if(map[0][j] == map[1][j] && map[1][j] == map[2][j]) {
				if(map[0][j]=='X') xxx++;
				else ooo++;
			}
		}
		// 대각선 체크
		if(map[0][0]!='.') {
			if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
				if(map[0][0]=='X') {
					xxx++;
				}else ooo++;
			}
		}
		if(map[0][2] != '.') {
			if(map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
				if(map[0][2]=='X')xxx++;
				else ooo++;
			}
		}
		
		if(xxx > 0 && ooo > 0) { // 둘 다 빙고 잇을 경우
			sb.append("invalid").append("\n");
		}
		else if(ooo > 0 && xxx == 0) { // o 빙고 있고, x 빙고 없을 경우,
			if(x==o) sb.append("valid").append("\n"); // x개수 = o 개수 인 경우
			else sb.append("invalid").append("\n"); // x개수가 더 많은 경우 
		}
		else if(xxx == 0 && ooo == 0) { // 둘 다 빙고 없을 경우
			if(x==5 && o == 4)sb.append("valid").append("\n"); // 빙고판 가득 찬 경우
			else sb.append("invalid").append("\n"); // 아닌 경우
		}
		else if(xxx > 0 && ooo == 0) { // x빙고 있고 o 빙고 없을 경우
			if(x>o) sb.append("valid").append("\n");
			else sb.append("invalid").append("\n"); // 아닌 경우
		}
	}
}
