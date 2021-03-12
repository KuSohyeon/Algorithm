import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1926_그림 {
    static int N, M, cnt, max;
    static int[][] map;
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !v[i][j]) {
                    max = Math.max(max, bfs(i, j));
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max);

    }

    private static int bfs(int i, int j) {
        Queue<Data> q = new LinkedList<>();
        int size = 0;

        v[i][j] = true;
        q.offer(new Data(i, j));

        while (!q.isEmpty()) {
            Data cur = q.poll();
            size++;

            for (int d = 0; d < 4; d++) {
                int ni = cur.i + dy[d];
                int nj = cur.j + dx[d];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 0 || v[ni][nj]) continue;
                v[ni][nj] = true;
                q.offer(new Data(ni, nj));
            }
        }

        return size;
    }
}
