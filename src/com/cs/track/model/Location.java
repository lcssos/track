package com.cs.track.model;

import com.baidu.location.BDLocation;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

@Table(name = "Location")
public class Location {

	@Column(name = "addrStr")
	private String addrStr;

	@Column(name = "altitude")
	private double altitude;

	@Column(name = "city")
	private String city;

	@Column(name = "cityCode")
	private String cityCode;

	@Column(name = "coorType")
	private String coorType;

	@Column(name = "direction")
	private float direction;

	@Column(name = "district")
	private String district;

	@Column(name = "floor")
	private String floor;

	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "locType")
	private int locType;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "networkLocationType")
	private String networkLocationType;
	
	@Column(name = "operators")
	private int operators;

	@Column(name = "province")
	private String province;

	@Column(name = "radius")
	private float radius;
	
	@Column(name = "satelliteNumber")
	private int satelliteNumber;

	@Column(name = "speed")
	private float speed;

	@Column(name = "street")
	private String street;

	@Column(name = "streetNumber")
	private String streetNumber;

	@Column(name = "time")
	private String time;

	public Location(BDLocation location) {
		addrStr = location.getAddrStr();
		altitude = location.getAltitude();
		city = location.getCity();
		cityCode = location.getCityCode();
		coorType = location.getCoorType();
		direction = location.getDirection();
		district = location.getDistrict();
		floor = location.getFloor();
		latitude = location.getLatitude();
		locType = location.getLocType();
		longitude = location.getLongitude();
		networkLocationType = location.getNetworkLocationType();
		operators = location.getOperators();
		province = location.getProvince();
		radius = location.getRadius();
		satelliteNumber = location.getSatelliteNumber();
		speed = location.getSpeed();
		street = location.getStreet();
		streetNumber = location.getStreetNumber();
		time = location.getTime();

	}

	public String getAddrStr() {
		return addrStr;
	}

	public void setAddrStr(String addrStr) {
		this.addrStr = addrStr;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCoorType() {
		return coorType;
	}

	public void setCoorType(String coorType) {
		this.coorType = coorType;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getLocType() {
		return locType;
	}

	public void setLocType(int locType) {
		this.locType = locType;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNetworkLocationType() {
		return networkLocationType;
	}

	public void setNetworkLocationType(String networkLocationType) {
		this.networkLocationType = networkLocationType;
	}

	public int getOperators() {
		return operators;
	}

	public void setOperators(int operators) {
		this.operators = operators;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public int getSatelliteNumber() {
		return satelliteNumber;
	}

	public void setSatelliteNumber(int satelliteNumber) {
		this.satelliteNumber = satelliteNumber;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
