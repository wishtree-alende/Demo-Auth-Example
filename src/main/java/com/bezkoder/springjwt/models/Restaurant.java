package com.bezkoder.springjwt.models;



import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String phonenumber;

	private String address;

	private String opentime;

	private String closetime;

	private boolean flag = false;

	@JsonView(View.FileInfo.class)
	private String filename;

	private String mimetype;

	@Lob
	private byte[] pic;

	private String lastUpdatedTime;

	public Restaurant() {
		super();
	}

	public Restaurant(String name, String phonenumber, String address, String opentime, String closetime, boolean flag,
			String filename, String mimetype, byte[] pic, String lastUpdatedTime) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.address = address;
		this.opentime = opentime;
		this.closetime = closetime;
		this.flag = flag;
		this.filename = filename;
		this.mimetype = mimetype;
		this.pic = pic;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getClosetime() {
		return closetime;
	}

	public void setClosetime(String closetime) {
		this.closetime = closetime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + ", address=" + address
				+ ", opentime=" + opentime + ", closetime=" + closetime + ", flag=" + flag + ", filename=" + filename
				+ ", mimetype=" + mimetype + ", pic=" + Arrays.toString(pic) + ", lastUpdatedTime=" + lastUpdatedTime
				+ "]";
	}

}