package com.javapid.entity.nivo.bubble;

import java.util.ArrayList;
import java.util.List;

public class InnerChildren{
	private String name;
	private List<FinalChildren> children;

	public InnerChildren(String name) {
		this.name = name;
		children = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<FinalChildren> getChildren() {
		return children;
	}

	public void addChildren(String name, Long value){
		children.add(new FinalChildren(name, value));
	}

	static class FinalChildren{
		private String name;
		private Long value;

		public String getName() {
			return name;
		}

		public Long getValue() {
			return value;
		}

		FinalChildren(String name, Long value) {
			this.name = name;
			this.value = value;
		}
	}
}