package com.javapid.entity.nivo;

public class DataSumDTO extends DataAbstract {

    public DataSumDTO(Long adults, Long seniors, Long juniors, Long students, Long portable) {
        setAdults(adults);
        setJuniors(juniors);
        setPortable(portable);
        setSeniors(seniors);
        setStudents(students);
    }
}
