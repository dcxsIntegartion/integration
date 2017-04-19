package team.union.business.mach.model;

import java.util.Date;

public class BisMach {
    private Long id;

	private String machName;

	private String machImg;

	private String machIndro;

	private String machDress;

	private String machArea;

	private String machAreaCode;

	private String machLng;

	private String machLat;

	private Long machUserId;

	private Date machCreatTime;

	private Integer machState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachName() {
		return machName;
	}

	public void setMachName(String machName) {
		this.machName = machName == null ? null : machName.trim();
	}

	public String getMachImg() {
		return machImg;
	}

	public void setMachImg(String machImg) {
		this.machImg = machImg == null ? null : machImg.trim();
	}

	public String getMachIndro() {
		return machIndro;
	}

	public void setMachIndro(String machIndro) {
		this.machIndro = machIndro == null ? null : machIndro.trim();
	}

	public String getMachDress() {
		return machDress;
	}

	public void setMachDress(String machDress) {
		this.machDress = machDress == null ? null : machDress.trim();
	}

	public String getMachArea() {
		return machArea;
	}

	public void setMachArea(String machArea) {
		this.machArea = machArea == null ? null : machArea.trim();
	}

	public String getMachAreaCode() {
		return machAreaCode;
	}

	public void setMachAreaCode(String machAreaCode) {
		this.machAreaCode = machAreaCode == null ? null : machAreaCode.trim();
	}

	public String getMachLng() {
		return machLng;
	}

	public void setMachLng(String machLng) {
		this.machLng = machLng == null ? null : machLng.trim();
	}

	public String getMachLat() {
		return machLat;
	}

	public void setMachLat(String machLat) {
		this.machLat = machLat == null ? null : machLat.trim();
	}

	public Long getMachUserId() {
		return machUserId;
	}

	public void setMachUserId(Long machUserId) {
		this.machUserId = machUserId;
	}

	public Date getMachCreatTime() {
		return machCreatTime;
	}

	public void setMachCreatTime(Date machCreatTime) {
		this.machCreatTime = machCreatTime;
	}

	public Integer getMachState() {
		return machState;
	}

	public void setMachState(Integer machState) {
		this.machState = machState;
	}
	
}