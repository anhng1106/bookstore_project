package exercise_4.exercise_4.web;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exercise_4.exercise_4.domain.AppUser;
import exercise_4.exercise_4.domain.AppUserRepository;


/**
 * This class is used by spring security to authenticate and authorize user
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository urepository;

    public UserDetailServiceImpl(AppUserRepository repository) {
		this.urepository = repository;
	}

  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	AppUser curruser = urepository.findByUsername(username);

		UserBuilder builder = null;
    	if (curruser == null) {
	    	throw new UsernameNotFoundException("User not found.");
    	}

    	else {
	    	builder = org.springframework.security.core.userdetails.User.withUsername(username);
	    	builder.password(curruser.getPasswordHash());
	    	builder.roles(curruser.getRole()); 
    	}
    	
	    return builder.build();
    }

}
