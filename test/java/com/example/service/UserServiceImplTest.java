package com.example.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dao.AddressDAO;
import com.example.dao.UserDAO;
import com.example.model.Address;
import com.example.model.User;




@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserDAO userDao;
	
	@Mock
	private AddressDAO addressDao;
	@Test
	void register() throws Exception{
		
	User user = new User();
	user.setUserId(1);
	user.setUserFName("drashti");
	user.setUserLName("khatra");
	user.setUserMobile("9265940877");
	user.setUserEmail("drashti@gmail.com");
	user.setAdmin(false);
	user.setUserGender("female");
	user.setUserPass("123");
	user.setUserHobby("singing");

	
	
	String imgLocation = "src/main/resources/static/images/1.png";
	
	BufferedImage bufferedImg = ImageIO.read(new File(imgLocation));
	
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	ImageIO.write(bufferedImg, "png", out);
	
	byte[] arr = out.toByteArray();
	
	user.setImages(arr);
	
	
	Address address = new Address();
	
	address.setAddress("shivansh green");
	address.setCity("ahmedabad");
	address.setState("gujarat");
	address.setPin("324156");
	
	List<Address> addresses = new ArrayList<>();
	addresses.add(address);

	user.setAddress(addresses);
	
	when(userDao.save(user)).thenReturn(user);	

	userService.register(user);
	
	user.setRelativeId(2);
	when(userDao.save(user)).thenReturn(user);	

	userService.register(user);
	when(userDao.save(user)).thenThrow(new RuntimeException());
	userService.register(user);


	
	verify(userDao, atLeast(1)).save(any());
		
	}
	
	@Test
	void login() throws Exception {
		User user = new User();
		user.setUserEmail("vi@gmail.com");
		user.setUserPass("123");
	
		
		when(userDao.findByUserEmailAndUserPass(user.getUserEmail(), user.getUserPass())).thenReturn(user);	
				
		User actual = userService.login(user);
	
		when(userDao.findByUserEmailAndUserPass(user.getUserEmail(), user.getUserPass())).thenThrow(new RuntimeException());
		userService.login(user);
		verify(userDao, atLeast(1)).findByUserEmailAndUserPass(any(),any());
	
	}
	
	@Test
	void viewUser() throws Exception {

		
	List<User> users = new ArrayList<>();

		
		when(userDao.findByisAdmin(false)).thenReturn(users);	
				
		users = userService.viewUser();
	
		when(userDao.findByisAdmin(false)).thenThrow(new RuntimeException());
		userService.viewUser();
		

		
		verify(userDao, atLeast(1)).findByisAdmin(false);
	
	}
	
	
	@Test
	void findById() {
		
		User user = new User();
		
		int id = 1;
		
		when(userDao.findByuserId(id)).thenReturn(user);
		
		user = userService.findById(id);
		

		when(userDao.findByuserId(id)).thenThrow(new RuntimeException());
		userService.findById(id);
		
		verify(userDao,atLeast(1)).findByuserId(id);
		
		
	}
	
	@Test
	void update() throws Exception {
		
		
		User user = new User();
		user.setUserFName("drashti");
		user.setUserLName("khatra");
		user.setUserMobile("9265940877");
		user.setUserEmail("drashti@gmail.com");
		user.setAdmin(false);
		user.setUserGender("female");
		user.setUserPass("123");
		user.setUserHobby("singing");

		String imgLocation = "src/main/resources/static/images/1.png";
		
		BufferedImage bufferedImg = ImageIO.read(new File(imgLocation));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bufferedImg, "png", out);
		
		byte[] arr = out.toByteArray();
		
		user.setImages(arr);
		
		
		Address address = new Address();
		
		address.setAddress("shivansh green");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPin("324156");
		
		List<Address> addresses = new ArrayList<>();
		addresses.add(address);

		user.setAddress(addresses);
		
		when(userDao.save(user)).thenReturn(user);	
		userService.update(user);
		
		user.setUserId(2);
		
		when(userDao.save(user)).thenReturn(user);	
		userService.update(user);
	
		when(userDao.save(user)).thenThrow(new RuntimeException());
		userService.update(user);
		
		verify(userDao, atLeast(1)).save(any());
		
	
		
	}
	
	@Test
	void viewProfile() {
		
		User user = new User();
		
		user.setUserEmail("vi@gmail.com");
		
		when(userDao.findByUserEmail(user.getUserEmail())).thenReturn(user);	
		
		userService.viewProfile(user.getUserEmail());
		

		when(userDao.findByUserEmail(user.getUserEmail())).thenThrow(new RuntimeException());
		userService.viewProfile(user.getUserEmail());
		
		
		verify(userDao, atLeast(1)).findByUserEmail(any());
		
		
	}
	
	
	@Test
	void deleteUser() {
		
		
		User user = new User();
		
		user.setUserId(1);

		
		doNothing().when(userDao).deleteById(user.getUserId());
		
		userService.deleteUser(user.getUserId());

		
		verify(userDao, atLeast(1)).deleteById(user.getUserId());
	
		
		
	}
	
	
	@Test
	void checkEmail() {
			
//		User users = new User();
//
//		when(userDao.findByUserEmail(null)).thenReturn(users);	
//		
//		System.out.println("users "+users);
//		userService.checkEmail(null);
//		
		User user =new User();
		
		user.setUserEmail("vi@gmail.com");
		
		when(userDao.findByUserEmail(user.getUserEmail())).thenReturn(user);	
		System.out.println("user "+user);
		
		userService.checkEmail(user.getUserEmail());
		when(userDao.findByUserEmail(user.getUserEmail())).thenThrow(new RuntimeException());
		userService.checkEmail(user.getUserEmail());
		
		verify(userDao, atLeast(1)).findByUserEmail(any());
		
		
	}

	@Test
	void updatePass() throws Exception{
		
		int result =0 ;
		User users = new User();
		
		users.setUserEmail("vi@gmail.com");
		users.setUserPass("123");
		
	
		when(userDao.updateByuserEmail(users.getUserEmail(),users.getUserPass())).thenReturn(result);	
		
		userService.updatePass(users.getUserEmail(),users.getUserPass());
		when(userDao.updateByuserEmail(users.getUserEmail(),users.getUserPass())).thenThrow(new RuntimeException());
		userService.updatePass(users.getUserEmail(),users.getUserPass());
		
		
		verify(userDao, atLeast(1)).updateByuserEmail(any(),any());
		
		
	}
	
	@Test
	void existId() {
		
	
		User users = new User();
		
		users.setRelativeId(3);
		
	
		when(userDao.findByrelativeId(users.getRelativeId())).thenReturn(users);	
		
		userService.existId(users.getRelativeId());
		
		when(userDao.findByrelativeId(users.getRelativeId())).thenThrow(new RuntimeException());
		userService.existId(users.getRelativeId());
		
		
		verify(userDao, atLeast(1)).findByrelativeId(users.getRelativeId());
		
	}
	
	
	@Test
	void getHomeAddress() {
		
		User users = new User();
		
		users.setUserId(2);
		
		List<Address> addresses = new ArrayList<>();
		

		Address address = new Address();
		
		address.setAddress("shivansh green");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPin("324156");
		address.setAddressType("Home");
		
		addresses.add(address);
		
		users.setAddress(addresses);
		when(userDao.findByuserId(users.getUserId())).thenReturn(users);	
		
		userService.getHomeAddress(users.getUserId());
		
		address.setAddressType("Office");
		
		when(userDao.findByuserId(users.getUserId())).thenReturn(users);	
		
		userService.getHomeAddress(users.getUserId());
		

		
		when(userDao.findByuserId(users.getUserId())).thenThrow(new RuntimeException());
		userService.getHomeAddress(users.getUserId());
		
		verify(userDao, atLeast(1)).findByuserId(users.getUserId());
		
		
	}
	
	@Test
	void updateAddress() {
		
		User user = new User();
		
		user.setRelativeId(1); 
		
		Address address = new Address();
		
		address.setAddress("shivansh green");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPin("324156");
		address.setAddressType("Home");
		
		doNothing().when(addressDao).updateAddress(address.getAddress(), address.getCity(), address.getState(), address.getPin(), user.getRelativeId());	
		
		userService.updateAddress(address,user);
		
		
		verify(addressDao, atLeast(1)).updateAddress(address.getAddress(), address.getCity(), address.getState(), address.getPin(), user.getRelativeId());
		
		
	}
	
	@Test
	void replaceUserId() {
		int result =0;
		int userId =2;
		int relativeId = 3;
		
		
		User user = new User();
		when(addressDao.replaceUserId(userId,relativeId)).thenReturn(result);	
		
		doNothing().when(userDao).updateId(0,relativeId);	
		
		when(userDao.findByuserId(userId)).thenReturn(user);	
		
		doNothing().when(userDao).deleteByuId(userId);	
		
		userService.replaceUserId(userId,relativeId);
	
	
		verify(addressDao, atLeast(1)).replaceUserId(userId,relativeId);
		
		verify(userDao, atLeast(1)).updateId(0,relativeId);
		
		verify(userDao, atLeast(1)).findByuserId(userId);
		
		verify(userDao, atLeast(1)).deleteByuId(userId);
		
	}

}
