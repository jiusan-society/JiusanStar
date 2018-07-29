package gov.jiusan.star.user;

import gov.jiusan.star.user.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User u) {
        return repository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = repository.findUserByAccount(account);
        if (user == null) {
            throw new UsernameNotFoundException(account);
        }
        return new UserDetailsImpl(user);
    }

    public User findUserByUsername(String account) throws UsernameNotFoundException {
        User user = repository.findUserByAccount(account);
        if (user == null) {
            throw new UsernameNotFoundException(account);
        }
        return user;
    }

    public UserProfile loadUserProfile(User user) {
        UserProfile profile = new UserProfile();
        profile.setName(user.getUsername());
        profile.setPhoneNum(user.getPhoneNum());
        profile.setEmail(user.getEmail());
        profile.setOrgName(user.getOrg().getName());
        profile.setRoleName(user.getRole().getName());
        return profile;
    }

    public User updateUser(User user, UserProfile profile) {
        user.setUsername(profile.getName());
        user.setPhoneNum(profile.getPhoneNum());
        user.setEmail(profile.getEmail());
        return user;
    }
}
