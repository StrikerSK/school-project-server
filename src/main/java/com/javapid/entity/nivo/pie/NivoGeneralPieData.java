package com.javapid.entity.nivo.pie;

public class NivoGeneralPieData extends NivoPieAbstractData {

	public NivoGeneralPieData(String name, Long count) {
		setId(name);
		setLabel(name);
		setValue(count);
	}

}
