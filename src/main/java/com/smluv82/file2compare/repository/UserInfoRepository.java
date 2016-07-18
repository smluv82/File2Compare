package com.smluv82.file2compare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smluv82.file2compare.domain.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
	UserInfo findByUserIdAndUserPass(String userId, String userPass);
}
