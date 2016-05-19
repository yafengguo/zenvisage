/**
 * 
 */
package edu.uiuc.zenvisage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import edu.uiuc.zenvisage.data.ScatterResult;
import edu.uiuc.zenvisage.data.ScatterResult.*;
import edu.uiuc.zenvisage.model.ScatterPlotQuery;
import edu.uiuc.zenvisage.model.ScatterPlotQuery.*;
import edu.uiuc.zenvisage.service.utility.Chart;
import edu.uiuc.zenvisage.service.utility.Result;

/**
 * @author xiaofo
 *
 */
public class ScatterRank {
	
	public static void generateAnalysis(Map<String, ScatterResult> output, ScatterPlotQuery q, Result finalOutput) {
		List<ScatterResult> datas = new ArrayList<ScatterResult>(output.values());
		for (ScatterResult data : datas) {
			for (Tuple point : data.points) {
				if (inArea(point,q)) data.count ++;
			}
		}
		Collections.sort(datas, new Comparator<ScatterResult>() {
			public int compare(ScatterResult a, ScatterResult b) {
				return Double.compare(((double) b.count) / ((double) b.points.size()), ((double) a.count) / ((double) a.points.size()));
			}
		});
		generateCharts(datas, q.numOfResults, q.yAxis, finalOutput);
	}
	
	public static boolean inArea(Tuple tuple, ScatterPlotQuery q) {
		for (Rectangle r : q.rectangles) {
			if (r.inArea(tuple)) return true;
		}
		return false;
	}
	
	public static void generateCharts(List<ScatterResult> datas, int numOfResult, String yAxis, Result finalOutput) {
		int len = Math.min(datas.size(), numOfResult);
		for (int i = 0; i < len; i++) {
			Chart chartOutput = new Chart();
			ScatterResult data = datas.get(i);
			System.out.println(data.name + Double.toString(((double) data.count) / ((double) data.points.size())));
			chartOutput.setxType((i+1)+" : "+data.name);
			chartOutput.setyType(yAxis);
			for (Tuple point : data.points) {
				chartOutput.xData.add(Double.toString(point.x));
				chartOutput.yData.add(Double.toString(point.y));
			}
			finalOutput.outputCharts.add(chartOutput);
		}
	}
}
