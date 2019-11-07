package com.javapid.entity.nivo.bubble;

import java.util.List;

public class BubbleChartData {

	private String name;
	private List<InnerChildren> children;

	public BubbleChartData(String name, List<InnerChildren> children) {
		this.name = name;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public List<InnerChildren> getChildren() {
		return children;
	}

}
