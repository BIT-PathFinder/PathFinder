package com.douzon.finderpath;

import java.util.ArrayList;
import java.util.Scanner;

public class FindPathAlgorihm {
	static int N; // N*N ���
	static int[][] money; // ���
	static String[][] test;
	static int[] visited; // �湮ǥ��
	static String[] test2;
	static int price; // �ּҺ��
	static ArrayList<String> q = new ArrayList<String>();
	static ArrayList<String> ��ť = new ArrayList<String>();

	static void DFS(int n, int s, int v) {

		if (n >= N) { // ��� �湮�� �Ѱ��
			if (money[s][0] != 0) { // ó������ ����.
				if (price > v) {
					price = v; // ���� ��ȯ ����
					��ť.clear();
					��ť.add("0");
					for (String ss : q) {
						��ť.add(ss);
					}
				}
			}
			return;
		}

		for (int i = 1; i < N; i++) {
			if (visited[i] == 1)
				continue; // �̹̹湮 ����
			if (money[s][i] == 0)
				continue; // 0�� ���̴�.

			// ���ݱ��� ���� ����� �������� ������.
			if (v + money[s][i] < price) { // ������ ���� �ּҺ�뺸�� ������ ��쿡�� �湮
				// �湮 ǥ�� �ϰ�
				visited[i] = 1;
				q.add(i + "");
				DFS(n + 1, i, v + money[s][i]);

				visited[i] = 0; // �湮ǥ�� ����
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
		price = Integer.MAX_VALUE; // �ּҺ���� ���ؾ� �ϱ� ������ �ִ밪�� �����ص�

		DFS(1, 0, 0); // �湮���� ����, ��ߵ���, ���� ���
		System.out.println(price);
//      System.out.println(Arrays.toString(test2));
//      System.out.println(Arrays.deepToString(test));
		sc.close();

		System.out.println(��ť);
	}
}


//��ǲ�� 5�� N*N�̶� ��. �ؿ��� ���
//5
//0 14 4 10 20 
//14 0 7 8 7 
//4 5 0 7 16 
//11 7 9 0 2 
//18 7 17 4 0 
//

//���� ���
//1 -> 4
//4  -> 5
//5 -> 2
//2 -> 3
//3 -> 1
// = 30
