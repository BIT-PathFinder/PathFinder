package com.douzon.finderpath;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class FindPathAlgorihm {
	static int N = 16; // N*N ���
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
		int �ݺ�Ƚ�� = 100;
		String txt = "";

		String fileName = "C:\\test.txt";
		try {

			BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

		for (int aaa = 0; aaa < �ݺ�Ƚ��; ++aaa) {
			money = new int[N][N];
			visited = new int[N];
			int row, col;
			for (row = 0; row <= money.length - 1; row++) {
				for (col = 0; col <= money.length - 1; col++) {
					if (row == col) {
						money[row][col] = 0;
					} else {
						if (row > col) {
							money[row][col] = money[col][row];
						} else {
							int r = (int) (Math.random() * 1000);
							money[row][col] = r;
						}
					}
				}
			}

			for (row = 0; row <= money.length - 1; row++) {
				for (col = 0; col <= money.length - 1; col++) {
					System.out.printf("%3d\t", money[row][col]);
				}
				System.out.println();
			}

			System.out.println();

			price = Integer.MAX_VALUE; // �ּҺ���� ���ؾ� �ϱ� ������ �ִ밪�� �����ص�

			long startTime = System.currentTimeMillis();
			DFS(1, 0, 0); // �湮���� ����, ��ߵ���, ���� ���
			long endTime = System.currentTimeMillis();

			System.out.println(price);

			System.out.println(��ť);
			// Total time
			long lTime = endTime - startTime;
			System.out.println("TIME : " + lTime + "(ms)");
			fw.write(lTime + "+");
		}
			fw.flush();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}