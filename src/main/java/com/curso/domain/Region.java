package com.curso.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the REGIONS database table.
 * 
 */
@Entity
@Table(schema="HR", name="REGIONS")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REGION_ID")
	private long idRegion;

	@Column(name="REGION_NAME")
	private String regionName;

	//bi-directional many-to-one association to Country
	@OneToMany(mappedBy="region")
	private List<Country> country;

	public Region() {
	}

	public long getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(long regionId) {
		this.idRegion = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public List<Country> getCountries() {
		return this.country;
	}

	public void setCountries(List<Country> country) {
		this.country = country;
	}

	public Country addCountry(Country country) {
		getCountries().add(country);
		country.setRegion(this);

		return country;
	}

	public Country removeCountry(Country country) {
		getCountries().remove(country);
		country.setRegion(null);

		return country;
	}

}