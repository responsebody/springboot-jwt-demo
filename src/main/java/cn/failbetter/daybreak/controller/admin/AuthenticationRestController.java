package cn.failbetter.daybreak.controller.admin;

import cn.failbetter.daybreak.common.web.response.Response;
import cn.failbetter.daybreak.security.JwtUser;
import cn.failbetter.daybreak.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthenticationRestController {


	@Autowired
	private AuthenticationService authenticationService;

    @PostMapping(value = "/auth")
    public Response createAuthenticationToken(@RequestBody JwtUser jwtUser)
			throws AuthenticationException {

		return Response.success(authenticationService.createAuthenticationToken(jwtUser));
    }

}
