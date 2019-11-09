package com.javapid.entity.nivo.bubble;

import java.util.List;

public class BubbleChartData extends NivoBubbleAbstract {

	private List<InnerChildren> children;

	public BubbleChartData(String name, List<InnerChildren> children) {
		super(name);
		this.children = children;
	}

	public List<InnerChildren> getChildren() {
		return children;
	}

}
