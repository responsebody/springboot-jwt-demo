package cn.failbetter.daybreak.model.convert;

import cn.failbetter.daybreak.common.validator.ValidatorUtils;
import cn.failbetter.daybreak.model.dataobject.User;
import cn.failbetter.daybreak.model.dto.UserDTO;
import cn.failbetter.daybreak.model.vo.UserEditVO;
import cn.failbetter.daybreak.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author sun 2019/4/25
 */
@Mapper
public interface UserConvert {

	UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

	@Mappings({})
	UserVO convert(User user);

	@Mappings({})
	UserVO convert(UserDTO user);

	@Mappings({})
	User convert2DO(UserEditVO userVO);
	@Mappings({})
	UserDTO convert2DTO(User user);

	default User convert2DOWithExtras(UserEditVO userVO) {
		ValidatorUtils.validateEntity(userVO);
		User user = convert2DO(userVO);
		user.setDefaultRoles();
		return user;
	}

}
