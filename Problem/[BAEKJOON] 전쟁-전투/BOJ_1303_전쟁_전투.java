import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303_전쟁_전투 {
    static int N, M;
    static char[][] map;
    static boolean[][] v;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int white, black;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세

        map = new char[N][M];
        v = new boolean[N][M];

        String input = null;

        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int white = 0;
        int black = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (v[i][j]) continue;
                if (map[i][j] == 'W') {
                    white += (int) Math.pow(dfs(i, j, 1, map[i][j]), 2);
                }else {
                    black += (int) Math.pow(dfs(i,j,1,map[i][j]),2);
                }
            }
        }

        System.out.println(white + " " + black);
    }

    static int dfs(int i, int j, int cnt, char color) {
        v[i][j]=true;
        for (int d = 0; d < 4; d++) {
            int ni = i + dy[d];
            int nj = j + dx[d];
            if (ni< 0 || ni >= N || nj < 0 || nj >= M || v[ni][nj] || map[ni][nj] != color) continue;
            cnt = dfs(ni, nj, cnt + 1, color);
        }

        return cnt;
    }
}
