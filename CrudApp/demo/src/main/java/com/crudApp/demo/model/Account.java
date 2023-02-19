package com.crudApp.demo.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="account")
public class Account {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		

		public Account(String fullName, String currency, String country, Long externlid,LocalDateTime deletedOn) {
			super();
			this.fullName = fullName;
			this.currency = currency;
			this.country = country;
			this.externlid = externlid;
			this.deletedOn=deletedOn;
		}
		
		
		public Account() {
			super();
			// TODO Auto-generated constructor stub
		}


		@Column(name="fullName")
		private String fullName;
		
		@Column(name="currency")
		private String currency;
		
		@Column(name="country")
		private String country;
		
		@Column(name="externlid")
		private Long externlid;
		
		@Column(name="deleteOn")
		private LocalDateTime deletedOn;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public Long getExternlid() {
			return externlid;
		}

		public void setExternlid(Long externlid) {
			this.externlid = externlid;
		}

		public LocalDateTime getDeletedOn() {
			return deletedOn;
		}

		public void setDeletedOn(LocalDateTime deletedOn) {
			this.deletedOn = deletedOn;
		}


		@Override
		public String toString() {
			return "Account [id=" + id + ", fullName=" + fullName + ", currency=" + currency + ", country=" + country
					+ ", externlid=" + externlid + "]";
		}


	
		
		
}
