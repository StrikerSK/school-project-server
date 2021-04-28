package com.charts.general.entity.nivo.bubble;

import java.util.ArrayList;
import java.util.List;

public class NivoBubbleData extends NivoBubbleAbstract {
	private List<FirstComplexChildren> children;

	public NivoBubbleData(String name) {
		super(name);
		this.children = new ArrayList<>();
	}

	public List<FirstComplexChildren> getChildren() {
		return children;
	}

	public void addChildren(FirstComplexChildren children) {
		this.children.add(children);
	}

	public static class FirstComplexChildren {
		private String name;
		private List<SecondComplexChildren> children;

		public FirstComplexChildren(String name) {
			this.name = name;
			this.children = new ArrayList<>();
		}

		public String getName() {
			return name;
		}

		public List<SecondComplexChildren> getChildren() {
			return children;
		}

		public void setChildren(List<SecondComplexChildren> children) {
			this.children = children;
		}

		public void addChildren(SecondComplexChildren children) {
			this.children.add(children);
		}

		public static class SecondComplexChildren {
			private String name;
			private List<BubbleFinalChildren> children;

			public SecondComplexChildren(String name) {
				this.name = name;
				children = new ArrayList<>();
			}

			public void addToList(String name, Long value) {
				this.children.add(new BubbleFinalChildren(name, value));
			}

			public String getName() {
				return name;
			}

			public List<BubbleFinalChildren> getChildren() {
				return children;
			}
		}
	}
}
