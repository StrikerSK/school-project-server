package com.javapid.entity.nivo.bubble;

import java.util.ArrayList;
import java.util.List;

public class NivoBubbleData extends NivoBubbleAbstract {
	private List<FirstNestedChildren> children;

	public NivoBubbleData(String name) {
		super(name);
		this.children = new ArrayList<>();
	}

	public List<FirstNestedChildren> getChildren() {
		return children;
	}

	public void addChildren(FirstNestedChildren children) {
		this.children.add(children);
	}

	public static class FirstNestedChildren {
		private String name;
		private List<SecondNestedChildren> children;

		public FirstNestedChildren(String name) {
			this.name = name;
			this.children = new ArrayList<>();
		}

		public String getName() {
			return name;
		}

		public List<SecondNestedChildren> getChildren() {
			return children;
		}

		public void setChildren(List<SecondNestedChildren> children) {
			this.children = children;
		}

		public void addChildren(SecondNestedChildren children) {
			this.children.add(children);
		}

		public static class SecondNestedChildren {
			private String name;
			private List<ThirdNestedChildren> children;

			public SecondNestedChildren(String name) {
				this.name = name;
				children = new ArrayList<>();
			}

			public void addToList(String name, Long value) {
				this.children.add(new ThirdNestedChildren(name, value));
			}

			public String getName() {
				return name;
			}

			public List<ThirdNestedChildren> getChildren() {
				return children;
			}

			public static class ThirdNestedChildren {
				private String name;
				private Long value;

				ThirdNestedChildren(String name, Long value) {
					this.name = name;
					this.value = value;
				}

				public String getName() {
					return name;
				}

				public Long getValue() {
					return value;
				}
			}
		}
	}
}
