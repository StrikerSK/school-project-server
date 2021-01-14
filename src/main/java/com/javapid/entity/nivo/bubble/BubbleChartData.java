package com.javapid.entity.nivo.bubble;

import java.util.ArrayList;
import java.util.List;

public class BubbleChartData extends NivoBubbleAbstract {

	private final List<FirstChildren> children;

	public BubbleChartData(String name) {
		super(name);
		this.children = new ArrayList<>();
	}

	public List<FirstChildren> getChildren() {
		return children;
	}

	public void addFirstChildren(FirstChildren children){
		this.children.add(children);
	}

	public static class FirstChildren {
		private final String name;
		private final List<BubbleFinalChildren> children;

		public FirstChildren(String name) {
			this.name = name;
			this.children = new ArrayList<>();
		}

		public String getName() {
			return name;
		}

		public List<BubbleFinalChildren> getChildren() {
			return children;
		}

		public void addSecondChildren(String name, Long value){
			this.children.add(new BubbleFinalChildren(name, value));
		}
	}

}
