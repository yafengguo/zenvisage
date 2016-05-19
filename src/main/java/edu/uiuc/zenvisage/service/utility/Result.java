package edu.uiuc.zenvisage.service.utility;

import java.util.ArrayList;

public class Result {
	public ArrayList<Chart> outputCharts = new ArrayList<Chart>();
	public String method;
	public String xUnit;
	public String yUnit;
	public int totalPage;
	
	// default constructor
	public Result() {
		
	}
	
	public Result(Result other, int page) {
		this.method = other.method;
		this.totalPage = (int) other.outputCharts.size() / 50;
		for (int i = 50*(page-1); i < 50*page && i < other.outputCharts.size(); i++) {
			this.outputCharts.add(other.outputCharts.get(i));
		}
	}

	public ArrayList<Chart> getOutputCharts() {
		return outputCharts;
	}

	public void setOutputCharts(ArrayList<Chart> outputCharts) {
		this.outputCharts = outputCharts;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getxUnit() {
		return xUnit;
	}

	public void setxUnit(String xUnit) {
		this.xUnit = xUnit;
	}

	public String getyUnit() {
		return yUnit;
	}

	public void setyUnit(String yUnit) {
		this.yUnit = yUnit;
	}
}
