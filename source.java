import java.util.*;

public class DivineIngenuity {
    private static int m, n;
    private static char[][] domain;
    private static int[][] distance;
    private static HashMap<Character, int[]> directions;

    private static class Node implements Comparable<Node> {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static int divineIngenuity() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node start = null, end = null;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (domain[i][j] == 'i') {
                    start = new Node(i, j, 0);
                    distance[i][j] = 0;
                }
                else if (domain[i][j] == 'j') {
                    end = new Node(i, j, 0);
                }
            }
        }

        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;
            int dist = current.dist;

            if (x == end.x && y == end.y) {
                break;
            }
            if (distance[x][y] == -1) {
                continue;
            }

            for (int[] d : directions.values()) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < m && ny < n && distance[nx][ny] != -1) {
                    int next = distance[nx][ny];
                    if (x == start.x && y == start.y) {
                        distance[nx][ny] = 0;
                        queue.add(new Node(nx, ny, 0));
                    }
                    else if (domain[x][y] == 'x') {
                        if (dist + 1 < next) {
                            distance[nx][ny] = dist + 1;
                            queue.add(new Node(nx, ny, dist + 1));
                        }
                    }
                    else {
                        int[] dir = directions.get(domain[x][y]);
                        if (dir != null && dir[0] == nx - x && dir[1] == ny - y) {
                            if (dist < next) {
                                distance[nx][ny] = dist;
                                queue.add(new Node(nx, ny, dist));
                            }
                        }
                        else {
                            if (dist + 1 < next) {
                                distance[nx][ny] = dist + 1;
                                queue.add(new Node(nx, ny, dist + 1));
                            }
                        }
                    }
                }
            }
            distance[x][y] = -1;
        }
        return distance[end.x][end.y];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        scanner.nextLine();

        domain = new char[m][n];
        distance = new int[m][n];
        directions = new HashMap<>();
        directions.put('w', new int[]{-1, 0});
        directions.put('a', new int[]{0, -1});
        directions.put('s', new int[]{1, 0});
        directions.put('d', new int[]{0, 1});

        for (int i = 0; i < m; i++) {
            String row = scanner.nextLine().trim();
            for (int j = 0; j < n; j++) {
                domain[i][j] = row.charAt(j);
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        int result = divineIngenuity();
        System.out.println(result);
    }
}
