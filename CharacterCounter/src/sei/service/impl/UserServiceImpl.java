package sei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sei.dao.UserMapper;
import sei.pojo.User;
import sei.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	UserMapper userDAO;

	@Override
	public User getUserById(long userId) {
		return this.userDAO.selectByPrimaryKey(userId);
	}
	

}
