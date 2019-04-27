package cn.failbetter.daybreak.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;

/**
 * @author sun 2019/4/26
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class JwtToken implements Serializable {

	private static final long serialVersionUID = 8627478529080372568L;

	private String token;
	private String issuer;
	private String subject;
	private SecretKey secretKey;
	private Date issueDate;
	private Date expDate;

}
