import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_양치기꿍 {
    static int R, C, wolf, sheep;
    static char[][] map;
    static boolean[][] v;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Data {
        int i;
        int j;

        public Data(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '#' && !v[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    private static void bfs(int i, int j) {
        Queue<Data> q = new LinkedList<Data>();

        int wo = 0, sh = 0;

        if (map[i][j] == 'v') wo++;
        else if (map[i][j] == 'k') sh++;
        v[i][j] = true;
        q.offer(new Data(i, j));

        while (!q.isEmpty()) {
            Data cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + dy[d];
                int nj = cur.j + dx[d];

                if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == '#' || v[ni][nj]) continue;
                if (map[ni][nj] == 'v') wo++;
                else if (map[ni][nj] == 'k') sh++;
                v[ni][nj] = true;
                q.offer(new Data(ni, nj));
            }
        }

        if (sh > wo) sheep += sh;
        else wolf += wo;
    }
}
