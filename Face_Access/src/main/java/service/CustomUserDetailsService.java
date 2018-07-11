package service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ACM-PC
 */
//@Service
public class CustomUserDetailsService implements UserDetailsService {

    //    private UserDao userdao;
    private Logger logger = Logger.getLogger("user");

//    @Autowired
//    public void setService(UserDao userdao) {
//        this.userdao = userdao;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        TbUserEntity entity = userdao.findUser(s);
//        logger.info(entity.toString());
//        if (entity == null) {
//            System.out.println("User not found");
//            throw new UsernameNotFoundException("Username not found");
//        }
        return new User("username", "password", getGrantedAuthorities());
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
