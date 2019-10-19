package com.javapid.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "pid_jizdenky")
public class PidJizenky {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonIgnore
	private Long id;

	@Column(name = "kod")
	@JsonProperty("kod")
	private String code;

	@Column(name = "mesiac")
	private String month;

	@Column(name = "rok")
	private Integer year;

	@Column(name = "zlavneny")
	private Boolean discounted;

	@Column(name = "stvrt_minut")
	private Long fifteenMinutes;

	@Column(name = "jeden_den")
	private Long oneDay;

	@Column(name = "jeden_den_vsetky")
	private Long oneDayAll;

	@Column(name = "dve_pasma")
	private Long twoZones;

	@Column(name = "tri_pasma")
	private Long threeZones;

	@Column(name = "styri_pasma")
	private Long fourZones;

	@Column(name = "pat_pasem")
	private Long fiveZones;

	@Column(name = "sest_pasem")
	private Long sixZones;

	@Column(name = "sedem_pasem")
	private Long sevenZones;

	@Column(name = "osem_pasem")
	private Long eightZones;

	@Column(name = "devat_pasem")
	private Long nineZones;

	@Column(name = "desat_pasem")
	private Long tenZones;

	@Column(name = "jedenast_pasem")
	private Long elevenZones;

	public PidJizenky() {
	}

	public PidJizenky(String code, String month, Integer year, Boolean discounted, Long fifteenMinutes, Long oneDay, Long oneDayAll, Long twoZones, Long threeZones, Long fourZones, Long fiveZones, Long sixZones, Long sevenZones, Long eightZones, Long nineZones, Long tenZones, Long elevenZones) {
		this.code = code;
		this.month = month;
		this.year = year;
		this.discounted = discounted;
		this.fifteenMinutes = fifteenMinutes;
		this.oneDay = oneDay;
		this.oneDayAll = oneDayAll;
		this.twoZones = twoZones;
		this.threeZones = threeZones;
		this.fourZones = fourZones;
		this.fiveZones = fiveZones;
		this.sixZones = sixZones;
		this.sevenZones = sevenZones;
		this.eightZones = eightZones;
		this.nineZones = nineZones;
		this.tenZones = tenZones;
		this.elevenZones = elevenZones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getDiscounted() {
		return discounted;
	}

	public void setDiscounted(Boolean discounted) {
		this.discounted = discounted;
	}

	public Long getFifteenMinutes() {
		return fifteenMinutes;
	}

	public void setFifteenMinutes(Long fifteenMinutes) {
		this.fifteenMinutes = fifteenMinutes;
	}

	public Long getOneDay() {
		return oneDay;
	}

	public void setOneDay(Long oneDay) {
		this.oneDay = oneDay;
	}

	public Long getOneDayAll() {
		return oneDayAll;
	}

	public void setOneDayAll(Long oneDayAll) {
		this.oneDayAll = oneDayAll;
	}

	public Long getTwoZones() {
		return twoZones;
	}

	public void setTwoZones(Long twoZones) {
		this.twoZones = twoZones;
	}

	public Long getThreeZones() {
		return threeZones;
	}

	public void setThreeZones(Long threeZones) {
		this.threeZones = threeZones;
	}

	public Long getFourZones() {
		return fourZones;
	}

	public void setFourZones(Long fourZones) {
		this.fourZones = fourZones;
	}

	public Long getFiveZones() {
		return fiveZones;
	}

	public void setFiveZones(Long fiveZones) {
		this.fiveZones = fiveZones;
	}

	public Long getSixZones() {
		return sixZones;
	}

	public void setSixZones(Long sixZones) {
		this.sixZones = sixZones;
	}

	public Long getSevenZones() {
		return sevenZones;
	}

	public void setSevenZones(Long sevenZones) {
		this.sevenZones = sevenZones;
	}

	public Long getEightZones() {
		return eightZones;
	}

	public void setEightZones(Long eightZones) {
		this.eightZones = eightZones;
	}

	public Long getNineZones() {
		return nineZones;
	}

	public void setNineZones(Long nineZones) {
		this.nineZones = nineZones;
	}

	public Long getTenZones() {
		return tenZones;
	}

	public void setTenZones(Long tenZones) {
		this.tenZones = tenZones;
	}

	public Long getElevenZones() {
		return elevenZones;
	}

	public void setElevenZones(Long elevenZones) {
		this.elevenZones = elevenZones;
	}
}
