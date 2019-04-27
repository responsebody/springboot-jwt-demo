package cn.failbetter.daybreak.controller.admin;

import cn.failbetter.daybreak.common.web.response.Response;
import cn.failbetter.daybreak.model.convert.UserConvert;
import cn.failbetter.daybreak.model.dataobject.User;
import cn.failbetter.daybreak.model.vo.UserEditVO;
import cn.failbetter.daybreak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sun 2019/4/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{username}")
	public Response find(@PathVariable("username") String username) {
		return Response.success(UserConvert.INSTANCE.convert(userService.find(username)));
	}

	@PostMapping("/register")
	public void save(@RequestBody UserEditVO userVO) {
		User user = UserConvert.INSTANCE.convert2DOWithExtras(userVO);
		userService.save(user);
	}

}
