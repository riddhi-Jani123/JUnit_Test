package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Address;
import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserControlller {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model, HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		return "index";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		session = request.getSession();

		session.removeAttribute("login");
		session.invalidate();

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
															// origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility

		return "index";
	}

	@RequestMapping(value = { "/register", "/addUser" })
	public String userRegister(Model model, @RequestParam(value = "adduser", required = false) String user,

			@RequestParam(value = "id", required = false) String id, HttpSession session) {

		if (user != null) {

			model.addAttribute("adduser", user);

		}

		List<User> userList = userService.viewUser();

		System.out.println("user " + userList);

	
		

		if (id != null) {

			if (id.equals("")) {

				model.addAttribute("error", "Please select valid input ");

			} else {
				model.addAttribute("id", id);
				session.setAttribute("relativeId", id);

				boolean isExist = userService.existId(Integer.parseInt(id));

				System.out.println("exist " + isExist);

				if (!isExist) {

					List<Address> addressList = userService.getHomeAddress(Integer.parseInt(id));

					if (!addressList.isEmpty()) {

						System.out.println("in address list");
						model.addAttribute("addressList", addressList);
						model.addAttribute("home", "true");

					}
				}

				else {

					model.addAttribute("error", "User Already Selected ");
				}
			}
		}

		model.addAttribute("userLists", userList);
		System.out.println("in register");

		return "register";
	}

	@RequestMapping(value = { "/registerForm" }, method = { RequestMethod.POST })
	public String register(Model model, @ModelAttribute("user") User user, BindingResult result,
			@RequestParam("Address[]") String[] addresses, @RequestParam("City[]") String[] city,
			@RequestParam("State[]") String[] state, @RequestParam("Pin[]") String[] pin,
			@RequestParam("addressType[]") String[] addressType,
			@RequestParam(value = "adduser", required = false) String userName, HttpServletResponse response,
			HttpSession session) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		System.out.println("in register form ");

		String relativeId = (String) session.getAttribute("relativeId");

		List<Address> address_list = new ArrayList<Address>();
		Address address_obj = null;

		int i = 0;

		while (i < addresses.length) {

			address_obj = new Address();

			address_obj.setAddress(addresses[i]);

			address_obj.setCity(city[i]);

			address_obj.setState(state[i]);

			address_obj.setPin(pin[i]);

			address_obj.setAddressType(addressType[i]);

			address_obj.setUser(user);

			if (addressType[i].equals("Home") && relativeId != null) {
				
				user.setRelativeId(Integer.parseInt(relativeId));
				userService.updateAddress(address_obj, user);
			}

			else {
				address_list.add(address_obj);
			}
			i++;

		}

		if (relativeId != null) {

			user.setRelativeId(Integer.parseInt(relativeId));
		}
		user.setAddress(address_list);

		userService.register(user);

		if (userName != null) {

			return "adminDashboard";
		}

		else {

			return "index";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("loginForm") User user, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		session = request.getSession();
		System.out.println("in controller "+user);
		User users = userService.login(user);

		session.setAttribute("emails", user.getUserEmail());
		session.setAttribute("login", "login");
		if (users != null) {

			if (users.isAdmin()) {

				session.removeAttribute("userId");

				return "adminDashboard";
			} else {
				int id = users.getUserId();

				model.addAttribute("userId", id);
				session.setAttribute("userId", id);
				return "userDashboard";
			}
		} else {
			model.addAttribute("err", "Invalid email and password");
			return "index";

		}

	}

	@GetMapping("/adminDashboard")
	public String adminDashboard(HttpServletResponse response, HttpSession session) {



		response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
															// origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
		if (session != null && session.getAttribute("login") != null)
			return "adminDashboard";
		else
			return "redirect:index";

	}

	@GetMapping("/adminProfile")
	public String adminProfile(Model model, HttpSession session, HttpServletResponse response) {

		String email = (String) session.getAttribute("emails");

		User list = userService.viewProfile(email);

		if (list != null) {
			List<Address> address = list.getAddress();

			model.addAttribute("list", list);

			model.addAttribute("address", address);
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
															// origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
		if (session != null && session.getAttribute("login") != null)
			return "adminProfile";
		else
			return "redirect:index";

	}

	@GetMapping("/userDashboard")
	public String userDashboard(HttpServletResponse response, HttpSession session) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
															// origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
		if (session != null && session.getAttribute("login") != null)
			return "userDashboard";
		else
			return "redirect:index";

	}

	@RequestMapping(value = "/viewUser")
	public String DisplayUser(Model model, HttpServletResponse response, HttpSession session) {

		List<User> userList = userService.viewUser();

		System.out.println("user " + userList);

		
		model.addAttribute("list", userList);

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
															// origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
		if (session != null && session.getAttribute("login") != null)
			return "userTable";
		else
			return "redirect:index";

	}

	@RequestMapping(value = "/userProfile")
	public String userProfile(Model model, HttpSession session, HttpServletResponse response) {

		String email = (String) session.getAttribute("emails");
		
		System.out.println(email);

		User list = userService.viewProfile(email);
		
		System.out.println("spy list"+list);
		
		System.out.println("l "+list);

		int id = 0;

		int relativeId = 0;

		if (list != null) {
			id = list.getUserId();
			relativeId = list.getRelativeId();
			List<Address> address = list.getAddress();
			if (relativeId != 0) {

				List<Address> relativeAddress = userService.getHomeAddress(relativeId);
				if (!relativeAddress.isEmpty()) {
					System.out.println("r add "+relativeAddress);
					address.add(relativeAddress.get(0));

				}
			}

			model.addAttribute("list", list);

			model.addAttribute("address", address);

		}

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		response.setHeader("Cache-Control", "no-cache"); // Forces caches to obtain a new copy of the page from the
															// origin server
		response.setHeader("Cache-Control", "no-store"); // Directs caches not to store the page under any
															// circumstance
		response.setDateHeader("Expires", 0); // Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0 backward compatibility
		if (session != null && session.getAttribute("login") != null)
			return "userProfile";
		else
			return "redirect:index";

	}

	@RequestMapping(value = { "/userEdit", "/adminEdit" })
	public String getData(Model model, @RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "user", required = false) String user, HttpServletResponse response,
			HttpSession session) {

		model.addAttribute("userId", userId);

		model.addAttribute("user", user);

		if (userId != null) {

			User userObj = userService.findById(Integer.parseInt(userId));

			byte[] img = userObj.getImages();

			session.setAttribute("img", img);

			List<Address> address = userObj.getAddress();

			boolean isExist = false;

			for (int i = 0; i < address.size(); i++) {

				isExist = address.get(i).getAddressType().contains("Home");
				if (isExist) {
					break;
				}
			}

			System.out.println("exist " + isExist);

			if (!isExist && userObj.getRelativeId() != 0) {

				List<Address> addressLists = userService.getHomeAddress(userObj.getRelativeId());

				address.add(addressLists.get(0));

			}

			model.addAttribute("addressList", address);

			model.addAttribute("userList", userObj);

		}

		return "register";
	}

	@RequestMapping(value = { "/editForm" })
	public String editForm(Model model, @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "userName", required = false) String userName, HttpServletResponse response,
			@RequestParam(value = "image", required = false) MultipartFile file,
			@RequestParam("Address[]") String[] addresses, @RequestParam("City[]") String[] city,
			@RequestParam("addressType[]") String[] addressType, @RequestParam("State[]") String[] state,
			@RequestParam("Pin[]") String[] pin, @RequestParam("addressId[]") String[] addressId,
			@RequestParam(value = "relativeIds", required = false) String relativeIds,

			HttpSession session) {
		
		System.out.println(user);
		
		System.out.println("file "+file);

		if (!relativeIds.equals("")) {

			System.out.println("rid " + relativeIds);
			user.setRelativeId(Integer.parseInt(relativeIds));
		}

		List<Address> addressList = new ArrayList<Address>();
		List<Address> addressLists = userService.getHomeAddress(user.getUserId());
		
		
		System.out.println("lists "+addressLists);
		Address addressObj = null;

		int i = 0;
		while (i < addresses.length) {

			addressObj = new Address();

			System.out.println("id " + addressId[i]);

			if (addressId[i].equals("")) {

				addressObj.setAddressId(0);
			} else {
				addressObj.setAddressId(Integer.parseInt(addressId[i]));
			}

			addressObj.setAddressType(addressType[i]);
			addressObj.setAddressesId(addressId[i]);

			addressObj.setAddress(addresses[i]);

			addressObj.setCity(city[i]);

			addressObj.setState(state[i]);

			addressObj.setPin(pin[i]);

			addressObj.setUser(user);

			if (addressLists.isEmpty() && addressType[i].equals("Home") && !relativeIds.equals("")) {

				System.out.println("rid " + user.getRelativeId());
				System.out.println("uid " + user.getUserId());
				userService.updateAddress(addressObj, user);

			}

			else {

				addressList.add(addressObj);
			}

			i++;

		}

		if (addressList.isEmpty()) {

			addressObj.setUser(user);

			addressList.add(addressObj);
		}

		if (file.isEmpty()) {

			byte[] img = (byte[]) session.getAttribute("img");

			user.setImages(img);
		}

		user.setAddress(addressList);
		userService.update(user);

		if (userName.equals("admin")) {
			return "adminDashboard";
		} else {

			return "userDashboard";
		}

	}

	@RequestMapping(value = "/deleteRecord")

	public void deleteRecord(@RequestParam("deleteid") String id, HttpServletResponse response) throws IOException {

		User user = userService.findById(Integer.parseInt(id));

		int relativeId = user.getRelativeId();

		int userId = user.getUserId();

		if (relativeId != 0) {

			userService.replaceUserId(userId, relativeId);

		}

		response.getWriter().write("true");

	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	public void checkEmail(@RequestParam("email") String email, HttpServletResponse response) throws IOException {


		boolean isExist = userService.checkEmail(email);

		if (isExist) {

			response.getWriter().write("true");
		} else {

			response.getWriter().write("false");
		}

	}

	@RequestMapping(value = "/forgot-password")
	public String forgot_password(HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		return "forgot-password";
	}

	@RequestMapping(value = "/forgotPass")
	public String forgotPass(Model model, @ModelAttribute("forgotPass") User user, HttpServletResponse response) {

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		int result = userService.updatePass(user.getUserEmail(), user.getUserPass());
		
		System.out.println(user.getUserEmail()+" "+user.getUserPass());
		
		System.out.println("result "+result );
		if (result > 0) {

			return "index";
		}

		else {

			model.addAttribute("err", "Email address is not exist");
			return "forgot-password";
		}
	}

}