package com.smluv82.file2compare.domain;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name="USER_INFO")
@Data
public class UserInfo {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID", nullable=false)
	@Nonnull
	private String userId;

	@Column(name="USER_PASS", nullable=false)
	@NonNull
	private String userPass;

	@Column(name="DEPT_CODE")
	private String deptCode;

	@Column(name="DEPT_NAME")
	private String deptName;

	@Column(name="USER_NO")
	private String userNo;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_TEL")
	private String userTel;

	@Column(name="USER_TYPE")
	@NonNull
	private String userType;

	@Column(name="USER_AUTHORITY")
	private String userAuthority;

	@Column(name="TERMINAL_IP")
	private String terminalIp;

	@Column(name="USE_YN")
	private String useYn;

	@Column(name="USER_DESC")
	private String userDesc;

	@Column(name="REG_DATE")
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date regDate;

	@Column(name="MOD_DATE")
	private String modDate;

	@Column(name="FAIL_CT")
	private String failCt;

	@Transient
	private Date startDate;

	@Transient
	private Date endDate;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public UserInfo() {

	}
}