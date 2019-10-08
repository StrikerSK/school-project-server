package com.javapid.entity.nivo;

public abstract class DataAbstract {

    private Long adults;
    private Long seniors;
    private Long juniors;
    private Long students;
    private Long portable;

    public Long getAdults() {
        return adults;
    }

    public void setAdults(Long adults) {
        this.adults = adults;
    }

    public Long getSeniors() {
        return seniors;
    }

    public void setSeniors(Long seniors) {
        this.seniors = seniors;
    }

    public Long getJuniors() {
        return juniors;
    }

    public void setJuniors(Long juniors) {
        this.juniors = juniors;
    }

    public Long getStudents() {
        return students;
    }

    public void setStudents(Long students) {
        this.students = students;
    }

    public Long getPortable() {
        return portable;
    }

    public void setPortable(Long portable) {
        this.portable = portable;
    }
}
