package com.douzon.finderpath;

import java.util.ArrayList;
import java.util.Scanner;

public class FindPathAlgorihm {
	static int N; // N*N 경로
	static int[][] money; // 비용
	static String[][] test;
	static int[] visited; // 방문표시
	static String[] test2;
	static int price; // 최소비용
	static ArrayList<String> q = new ArrayList<String>();
	static ArrayList<String> 찐큐 = new ArrayList<String>();

	static void DFS(int n, int s, int v) {

		if (n >= N) { // 모두 방문을 한경우
			if (money[s][0] != 0) { // 처음으로 간다.
				if (price > v) {
					price = v; // 적다 교환 ㄱㄱ
					찐큐.clear();
					찐큐.add("0");
					for (String ss : q) {
						찐큐.add(ss);
					}
				}
			}
			return;
		}

		for (int i = 1; i < N; i++) {
			if (visited[i] == 1)
				continue; // 이미방문 했음
			if (money[s][i] == 0)
				continue; // 0은 벽이다.

			// 지금까지 누적 비용이 작을때만 돌린다.
			if (v + money[s][i] < price) { // 이전에 구한 최소비용보다 저렴한 경우에만 방문
				// 방문 표시 하고
				visited[i] = 1;
				q.add(i + "");
				DFS(n + 1, i, v + money[s][i]);

				visited[i] = 0; // 방문표시 제거
//            System.out.println(Arrays.toString(visited));
				q.remove(q.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		money = new int[N][N];
		visited = new int[N];
//      test = new String[N][N];
//      test2 = new String[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				money[i][j] = sc.nextInt();
//            test[i][j] =i+","+j;
			}
		}
		price = Integer.MAX_VALUE; // 최소비용을 구해야 하기 때문에 최대값을 저장해둠

		DFS(1, 0, 0); // 방문도시 개수, 출발도시, 누적 비용
		System.out.println(price);
//      System.out.println(Arrays.toString(test2));
//      System.out.println(Arrays.deepToString(test));
		sc.close();

		System.out.println(찐큐);
	}
}


//인풋값 5는 N*N이란 뜻. 밑에는 비용
//5
//0 14 4 10 20 
//14 0 7 8 7 
//4 5 0 7 16 
//11 7 9 0 2 
//18 7 17 4 0 
//

//정답 경로
//1 -> 4
//4  -> 5
//5 -> 2
//2 -> 3
//3 -> 1
// = 30
