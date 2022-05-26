package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Address;
import com.example.model.User;

@Repository
@Transactional
public interface AddressDAO  extends JpaRepository<Address, Integer>{
	
	
	@Modifying
	@Query("UPDATE  Address SET address= :address, City= :city, State=:state, Pin=:pin   WHERE  user.userId = :userId and addressType='Home' ")
	void updateAddress(@Param("address") String address,@Param("city") String city,@Param("state") String state,@Param("pin") String pin,
			@Param("userId") int userId);
	
	
	@Modifying
	@Query("UPDATE  Address SET user.userId = :relativeId  WHERE  user.userId = :userId and addressType='Home' ")
	int replaceUserId(@Param("userId") int userId,@Param("relativeId")int relativeId);
	
}
