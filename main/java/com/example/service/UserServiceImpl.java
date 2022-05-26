
package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AddressDAO;
import com.example.dao.UserDAO;
import com.example.model.Address;
import com.example.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Autowired
	AddressDAO addressDao;
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub

		try {
			List<Integer> addressList = new ArrayList<Integer>();
			System.out.println("in service");
			for (Address address : user.getAddress()) {
				address.setUser(user);
			}
			
			

			System.out.println("user in update " + user);
			System.out.println(user.getAddress());

			
			 userDao.save(user);
			
			if (user.getRelativeId()!=0) {
				
				System.out.println("relative id "+user.getRelativeId());
				
				System.out.println("user id "+user.getUserId());

				userDao.updateId(user.getUserId(),user.getRelativeId());
				
			}


		}

		catch (Exception e) {
			e.printStackTrace();
		}

	
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		User users = null;
		try {
			users = userDao.findByUserEmailAndUserPass(user.getUserEmail(), user.getUserPass());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public List<User> viewUser() {
		// TODO Auto-generated method stub

		List<User> user = new ArrayList<User>();
		try {

			user = (userDao.findByisAdmin(false));
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = userDao.findByuserId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

		try {
		List<Integer> addressList = new ArrayList<Integer>();
		System.out.println("in update");
		for (Address address : user.getAddress()) {
			address.setUser(user);
		}
			

		System.out.println("user in update " + user);
		System.out.println(user.getAddress());

		if (user.getUserId() != 0) {

			for (Address address : user.getAddress()) {
				addressList.add(address.getAddressId());
			}
			userDao.deleteByUserId(addressList,user);
		}
		 userDao.save(user);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

	@Override
	public User viewProfile(String email) {
		// TODO Auto-generated method stub
		User users = null;
		try {
			users = userDao.findByUserEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
	
		userDao.deleteById(id);
		

	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		boolean isExist = false;

		try {
			User user = userDao.findByUserEmail(email);

			if (user != null) {

				isExist = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExist;
	}

	@Override
	public int updatePass(String email, String pass) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			result = userDao.updateByuserEmail(email, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean existId(int relativeId) {
		// TODO Auto-generated method stub
		boolean isExist = false;

		try {
			User user = userDao.findByrelativeId(relativeId);

			if (user != null) {

				isExist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExist;
	}

	@Override
	public List<Address> getHomeAddress( int id) {
		// TODO Auto-generated method stub
		List<Address> address = new ArrayList<>();
	try {
		User user = userDao.findByuserId(id);
		System.out.println("before list "+user.getAddress());
		
	
		
		for(int i=0;i<user.getAddress().size();i++) {
			
			if(user.getAddress().get(i).getAddressType().equals("Home")) {
				
				System.out.println("in home");
				System.out.println(user.getAddress().get(i));
				address.add(user.getAddress().get(i));
			}
		}
	}
	catch(Exception e) {
		e.printStackTrace();
		
	}
		return address;
	}

	@Override
	public void updateAddress(Address address, User user) {
		// TODO Auto-generated method stub

		addressDao.updateAddress(address.getAddress(), address.getCity(), address.getState(), address.getPin(), user.getRelativeId());
		
	}

	@Override
	public void replaceUserId(int userId, int relativeId) {
		// TODO Auto-generated method stub
		
	
		System.out.println("rid"+relativeId+"uid "+userId);
		
		int affected = addressDao.replaceUserId(userId, relativeId);
		
		System.out.println("aff"+affected);
		
		userDao.updateId(0, relativeId);
		
		User user = userDao.findByuserId(userId);
		
		System.out.println(" u "+user.getAddress());
		
		userDao.deleteByuId(userId);
		
		
	}


}
