package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

import com.example.model.Address;
import com.example.model.User;
import com.example.service.UserService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;



@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	

	
	@MockBean
	private UserService userService;
	

	@Test
	public void register() throws Exception{

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

		user.setRelativeId(2);
		
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
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		
		session.setAttribute("relativeId", "2");
		
		doNothing().when(userService).register(user);
		
		this.mockMvc
		.perform(post("/registerForm").session(session).param("adduser","addUser").param("Address[]", "vidhi").param("City[]", "patel").param("State[]", "gujarat").param("Pin[]", "352417").param("addressType[]", "Home")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());
		
		this.mockMvc
		.perform(post("/registerForm").param("Address[]", "vidhi").param("City[]", "patel").param("State[]", "gujarat").param("Pin[]", "352417").param("addressType[]", "Office")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());
		
		this.mockMvc
		.perform(post("/registerForm").session(session).param("Address[]", "vidhi").param("City[]", "patel").param("State[]", "gujarat").param("Pin[]", "352417").param("addressType[]", "Office")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());
		
		this.mockMvc
		.perform(post("/registerForm").param("Address[]", "vidhi").param("City[]", "patel").param("State[]", "gujarat").param("Pin[]", "352417").param("addressType[]", "Home")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());

		
		verify(userService, atLeast(1)).register(any());
		
	}
	
	@Test
	public void userRegister() throws Exception{
		
		List<User> userList = new ArrayList<User>();
		
		when(userService.viewUser()).thenReturn(userList);
		
		this.mockMvc
		.perform(get("/register")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		boolean isExist = true;
		
		int id =2;
		
		when(userService.existId(id)).thenReturn(isExist);
	
		
		this.mockMvc
		.perform(get("/register").param("adduser", "adduser").param("id", "2")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		this.mockMvc
		.perform(get("/register").param("adduser", "adduser").param("id", "")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		List<Address> addressList = new ArrayList<Address>();
		Address address = new Address();
		
		address.setAddress("abc");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPin("324156");
		address.setAddressType("Office");
		address.setAddressId(1);
		addressList.add(address);
		
		when(userService.getHomeAddress(id)).thenReturn(addressList);
		
		this.mockMvc
		.perform(get("/register").param("adduser", "adduser").param("id", "1").content(addressList.toString())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	when(userService.getHomeAddress(1)).thenReturn(addressList);
		
		this.mockMvc
		.perform(get("/register").param("adduser", "adduser").param("id", "1").content(addressList.toString())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		
		verify(userService, atLeast(1)).viewUser();
	}
	
	@Test
	public void index() throws Exception{
		
		this.mockMvc
		.perform(post("/index")
				.contentType(MediaType.APPLICATION_JSON).content(""))
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void logout() throws Exception{
		
		this.mockMvc
		.perform(post("/logout")
				.contentType(MediaType.APPLICATION_JSON).content(""))
		.andExpect(status().isOk());
		
	}
	

	
	@Test
	public void login() throws Exception{
	
		User user = new User();
		
		when(userService.login(user)).thenReturn(user);
		this.mockMvc
		.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk()) ;
		
	
		
		user.setUserEmail("vi@gmail.com");
		user.setUserPass("123");
		
		user.setBase64Image("123");
		
		user.setImages("123".getBytes());
		
	
		System.out.println("users "+user);
		when(userService.login(any())).thenReturn(user);
		System.out.println("users "+user);
	
		this.mockMvc
		.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk()) ;
		
		user.setAdmin(true);
		
		this.mockMvc
		.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk()) ;
	
		
	
		verify(userService, atLeast(1)).login(any());
		
	}
	
//	public static <T> String convertToJson(T object) throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//
//	
//		return writer.writeValueAsString(object);
//	}
	
	@Test
	public void adminDashboard() throws Exception {
		
		

	
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("login", "login");
	
		this.mockMvc.perform(get("/adminDashboard").session(session))
		.andExpect(status().isOk());
		
		this.mockMvc.perform(get("/adminDashboard"))
		.andExpect(status().is3xxRedirection());
	
		
	}
	
	@Test
	public void adminProfile() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		
		User user = new User();

		this.mockMvc.perform(get("/adminProfile").session(session).contentType(MediaType.APPLICATION_JSON).content(user.toString()))
	.andExpect(status().is3xxRedirection());
		
		when(userService.viewProfile(user.getUserEmail())).thenReturn(user);
		
		this.mockMvc.perform(get("/adminProfile").session(session).contentType(MediaType.APPLICATION_JSON).content(user.toString()))
	.andExpect(status().is3xxRedirection());
	
		user.setUserEmail("vi@gmail.com");
		
		user.setUserId(1);
		user.setUserFName("drashti");
		user.setUserLName("khatra");
		user.setUserMobile("9265940877");
		user.setUserEmail("drashti@gmail.com");
		user.setAdmin(false);
		user.setUserGender("female");
		user.setUserPass("123");
		user.setUserHobby("singing");

		user.setRelativeId(2);
		
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

		session.setAttribute("emails", "vi@gmail.com");
		session.setAttribute("login", "login");
		
		when(userService.viewProfile(any())).thenReturn(user);
		this.mockMvc.perform(get("/adminProfile").session(session).contentType(MediaType.APPLICATION_JSON).content(user.toString()))
	.andExpect(status().isOk());

		
	}
	
	@Test
	public void userDashboard() throws Exception{
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		this.mockMvc.perform(get("/userDashboard").session(session).contentType(MediaType.APPLICATION_JSON).content(""))
		.andExpect(status().is3xxRedirection());
		session.setAttribute("login", "login");
		this.mockMvc.perform(get("/userDashboard").contentType(MediaType.APPLICATION_JSON).content(""))
	.andExpect(status().is3xxRedirection());
		this.mockMvc.perform(get("/userDashboard").session(session).contentType(MediaType.APPLICATION_JSON).content(""))
		.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void displayUser() throws Exception{
		
		List<User> userList = new ArrayList<User>();
		User user = new User();
		user.setUserFName("drashti");
		user.setUserLName("khatra");
		user.setUserMobile("9265940877");
		user.setUserEmail("drashti@gmail.com");
		user.setAdmin(false);
		user.setUserGender("female");
		user.setUserPass("123");
		user.setUserHobby("singing");
	
		userList.add(user);
		when(userService.viewUser()).thenReturn(userList);
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		
		session.setAttribute("login", "login");
		
		System.out.println("list "+userList);
		this.mockMvc.perform(get("/viewUser").contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().is3xxRedirection());
		this.mockMvc.perform(get("/viewUser").session(session).contentType(MediaType.APPLICATION_JSON).content(""))
		.andExpect(status().isOk());


		
	}
	
	@Test
	public void userProfile() throws Exception{
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpSession session = new MockHttpSession();
		
		String email ="r@gmail.com";
		User user = new User();
		session.setAttribute("emails", email);
		

		mockMvc.perform(get("/userProfile").session(session).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection());
		when(userService.viewProfile(email)).thenReturn(user);
	
		
		mockMvc.perform(get("/userProfile").session(session).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection());
		user.setRelativeId(2);

		mockMvc.perform(get("/userProfile").session(session).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection());
		List<Address> addresses = new ArrayList<>();
		
		Address address = new Address();
		
		address.setAddress("shivansh green");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPin("324156");
		address.setAddressType("Home");
		address.setAddressesId("1");
		address.setRemoveId("3");
		addresses.add(address);
		user.setAddress(addresses);
		when(userService.getHomeAddress(user.getRelativeId())).thenReturn(addresses);
		mockMvc.perform(get("/userProfile").session(session).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection());
		user.setUserFName("drashti");
		user.setUserLName("khatra");
		user.setUserMobile("9265940877");
		user.setUserEmail("drashti@gmail.com");
		user.setAdmin(false);
		user.setUserGender("female");
		user.setUserPass("123");
		user.setUserHobby("singing");

		user.setRelativeId(2);
		
		String imgLocation = "src/main/resources/static/images/1.png";
		
		BufferedImage bufferedImg = ImageIO.read(new File(imgLocation));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bufferedImg, "png", out);
		
		byte[] arr = out.toByteArray();
		
		user.setImages(arr);
		
		session.setAttribute("login", "login");
		mockMvc.perform(get("/userProfile").session(session).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
	}
	
	@Test
	public void checkEmail() throws Exception{
		

		mockMvc.perform(get("/checkEmail").param("email","r@gmail.com").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		boolean isExist = true;
		when(userService.checkEmail("r@gmail.com")).thenReturn(isExist);
	
		mockMvc.perform(get("/checkEmail").param("email","r@gmail.com").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
	}
	
	@Test
	public void forgotPassword() throws Exception{
		
		int results = 0;
		User user = new User();
		
		user.setUserEmail("vi@gmail.com");
		user.setUserPass("123");
		when(userService.updatePass(user.getUserEmail(), user.getUserPass())).thenReturn(results);
		
		
		mockMvc.perform(get("/forgotPass").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());
		
		int result =1;
		
		

		when(userService.updatePass(any(), any())).thenReturn(result);
		
		
		mockMvc.perform(get("/forgotPass").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());
		
		
		
		
	}
	
	
	@Test
	public void forgotPass() throws Exception{
	
		mockMvc.perform(get("/forgot-password").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
	}
	
	
	@Test
	public void deleteRecord() throws Exception{
		
		int id =1;
		User user = new User();
		user.setUserFName("123");
		
		when(userService.findById(id)).thenReturn(user);
	
		mockMvc.perform(get("/deleteRecord").param("deleteid","1").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());
		
		user.setRelativeId(2);
		
		
		when(userService.findById(id)).thenReturn(user);
	
		mockMvc.perform(get("/deleteRecord").param("deleteid","1").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());
		
		
	}
	
	@Test
	public void userEdit()  throws Exception{
		User user = new User();
		
		mockMvc.perform(get("/userEdit")).andExpect(status().isOk());
		
		user.setUserId(1);
		user.setUserFName("drashti");
		user.setUserLName("khatra");
		user.setUserMobile("9265940877");
		user.setUserEmail("drashti@gmail.com");
		user.setAdmin(false);
		user.setUserGender("female");
		user.setUserPass("123");
		user.setUserHobby("singing");
		user.setImages("123".getBytes());
		List<Address> addresses = new ArrayList<>();
		Address address = new Address();
		
		address.setAddress("abc");
		address.setCity("ahmedabad");
		address.setState("gujarat");
		address.setPin("324156");
		address.setAddressType("Office");
		address.setAddressId(1);
		addresses.add(address);
		user.setAddress(addresses);
		
		when(userService.findById(user.getUserId())).thenReturn(user);
		mockMvc.perform(get("/userEdit").param("userId", "1").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());
		

		user.setRelativeId(2);
		when(userService.getHomeAddress(user.getRelativeId())).thenReturn(addresses);
		when(userService.findById(user.getUserId())).thenReturn(user);
		mockMvc.perform(get("/userEdit").param("userId", "1").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());
		
		address.setAddressType("Home");
	
		when(userService.findById(user.getUserId())).thenReturn(user);
		mockMvc.perform(get("/userEdit").param("userId", "1").contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());

		
	}
	
	@Test
	public void editForm() throws Exception{
		
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
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

		user.setRelativeId(2);
		
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
		address.setAddressType("Home");
		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);

		user.setAddress(addresses);
	
		

		MockMultipartFile files = new MockMultipartFile("file","".getBytes());
		mockMvc
		.perform(multipart("/editForm").file("image",files.getBytes()).param("Address[]", "vidhi").param("City[]", "patel").param("relativeIds","2").param("State[]", "gujarat").param("Pin[]", "352417").param("addressId[]", "","1").param("userName","").param("addressType[]", "Home")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());

		mockMvc
		.perform(multipart("/editForm").file("image",file.getBytes()).param("Address[]", "vidhi").param("City[]", "patel").param("relativeIds","").param("State[]", "gujarat").param("Pin[]", "352417").param("addressId[]", "1").param("userName","admin").param("addressType[]", "Home")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());
		

		mockMvc
		.perform(multipart("/editForm").file("image",file.getBytes()).param("Address[]", "vidhi").param("City[]", "patel").param("relativeIds","").param("State[]", "gujarat").param("Pin[]", "352417").param("addressId[]", "1").param("userName","admin").param("addressType[]", "Office")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());
		
		
		when(userService.getHomeAddress(user.getUserId())).thenReturn(addresses);
		mockMvc
		.perform(multipart("/editForm").file("image",file.getBytes()).param("Address[]", "vidhi").param("City[]", "patel").param("relativeIds","").param("State[]", "gujarat").param("Pin[]", "352417").param("addressId[]", "1").param("userName","admin").param("addressType[]", "Office")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString()))
		.andExpect(status().isOk());
		
		
	}
	
		
	
}
