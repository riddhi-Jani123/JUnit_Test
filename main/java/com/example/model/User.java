package com.example.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;




@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;

	private String userFName;
	
	private String userLName;

	private String userEmail;

	private String userPass;

	private String userMobile;
	
	private String userGender;
	
	private String userHobby;

	private int relativeId;
	
	@Transient
	private String relativeIds;
	
	private boolean isAdmin;
	
	@Lob
	private byte[] images;
	
	@Transient
	private String base64Image;
	
	
	@Transient
	private MultipartFile image;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Address> address;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public String getUserLName() {
		return userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
	
		this.userPass = userPass;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserHobby() {
		return userHobby;
	}

	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}
	

	

	public int getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(int relativeId) {
		this.relativeId = relativeId;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	public byte[] getImages() {
		return images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	public String getBase64Image() {
		
		return Base64.getEncoder().encodeToString(images);
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) throws IOException {
		this.images = image.getBytes();
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
	

	public String getRelativeIds() {
		return relativeIds;
	}

	public void setRelativeIds(String relativeIds) {
		this.relativeIds = relativeIds;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userFName=" + userFName + ", userLName=" + userLName + ", userEmail="
				+ userEmail + ", userPass=" + userPass + ", userMobile=" + userMobile + ", userGender=" + userGender
				+ ", userHobby=" + userHobby + ", relativeId=" + relativeId + ", relativeIds=" + relativeIds
				+ ", isAdmin=" + isAdmin + "]";
	}

	
	



	


	





	


	
	
	

}
