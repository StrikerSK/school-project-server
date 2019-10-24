package com.javapid.entity.nivo;

public class NivoBarDataSumDTO extends NivoBarDataAbstract {

	public NivoBarDataSumDTO(Long adults, Long seniors, Long juniors, Long students, Long portable) {
		setAdults(adults);
		setJuniors(juniors);
		setPortable(portable);
		setSeniors(seniors);
		setStudents(students);
	}
}
