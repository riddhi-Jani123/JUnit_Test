package com.example.model;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Address {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private  int addressId;
				
		@Transient
		private String removeId;
		
		private String address;
		private  String city;
		private  String state;
		private  String pin;
		private String addressType;
		
		@Transient
		private String addressesId;
		
		
		@ManyToOne
		@JoinColumn(name="userId")
		private User user ;

		public int getAddressId() {
			return addressId;
		}

		public void setAddressId(int addressId) {
			this.addressId = addressId;
		}

	
		
		public String getRemoveId() {
			return removeId;
		}

		public void setRemoveId(String removeId) {
			this.removeId = removeId;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getPin() {
			return pin;
		}

		public void setPin(String pin) {
			this.pin = pin;
		}
		
		
		public String getAddressType() {
			return addressType;
		}

		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		

		public String getAddressesId() {
			return addressesId;
		}

		public void setAddressesId(String addressesId) {
			this.addressesId = addressesId;
		}

		@Override
		public String toString() {
			return "Address [addressId=" + addressId + ", removeId=" + removeId + ", address=" + address + ", city="
					+ city + ", state=" + state + ", pin=" + pin + ", addressType=" + addressType + ", addressesId="
					+ addressesId + "]";
		}

	
		

		
	

		
	
	
}
