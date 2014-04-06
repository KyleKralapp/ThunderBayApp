package edu.mtu.citizenscience.thunderbay;

import java.util.Date;

public class ShipWreck {
	private String name;
	private String type;
	private String hull;
	private int built;
	private int lost;
	private String builder;
	private String buildPlace;
	private double length;
	private double beam;
	private String lossType;
	private String cargo;
	private int livesLost;
	private String county;
	private float latitude;
	private float longitude;
	private double depth;
	private String notes;
	private long id;
	
	public long getId(){
		return id;
	}
	
	public void setId(long newId){
		this.id = newId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHull() {
		return hull;
	}

	public void setHull(String hull) {
		this.hull = hull;
	}

	public int getBuilt() {
		return built;
	}

	public void setBuilt(int built) {
		this.built = built;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getBuildPlace() {
		return buildPlace;
	}

	public void setBuildPlace(String buildPlace) {
		this.buildPlace = buildPlace;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getBeam() {
		return beam;
	}

	public void setBeam(double beam) {
		this.beam = beam;
	}

	public String getLossType() {
		return lossType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getLivesLost() {
		return livesLost;
	}

	public void setLivesLost(int livesLost) {
		this.livesLost = livesLost;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
