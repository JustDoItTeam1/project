package com.sju.program.utils;

import java.util.Arrays;

/**
 * @author qhw
 * @date 2021/3/26
 **/
public class PNPoly {

	public static boolean polygon(double[] xs, double[] ys, double x, double y) {
		// 点是否包含在多边形内
		boolean contained = false;
		double xMin = Arrays.stream(xs).min().getAsDouble();
		double xMax = Arrays.stream(xs).max().getAsDouble();
		double yMin = Arrays.stream(ys).min().getAsDouble();
		double yMax = Arrays.stream(ys).max().getAsDouble();
		if (x > xMax || x < xMin || y > yMax || y < yMin) {
			contained = false;
		}
		// 核心算法部分
		int N = xs.length;
		double dist = Double.MAX_VALUE;
		for (int i = 0, j = N - 1; i < N; j = i++) {
			if (((ys[j] >= y) != (ys[i] >= y)) && (x < (xs[j] - xs[i]) * (y - ys[i]) / (ys[j] - ys[i]) + xs[i])) {
				contained = !contained;
			}
			//dist = Math.min(dist, pointToSegmentDist(x, y, xs[i], ys[i], xs[j], ys[j]));
		}
		System.out.println(contained);
		return contained;
	}
}
