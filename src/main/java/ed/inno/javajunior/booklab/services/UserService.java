package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.User;
import ed.inno.javajunior.booklab.repositories.RoleRepository;
import ed.inno.javajunior.booklab.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        String name = principal.getName();
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", name)));

    }

    public boolean createUser(Map<String, String> regParams) {
        String username = regParams.get("username");
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(regParams.get("email"));
        user.setPassword(passwordEncoder.encode(regParams.get("password")));
        user.getRoles().add(roleRepository.findByName("ROLE_USER").
                orElseThrow(() -> new UsernameNotFoundException("В системе не установлена роль пользователя")));
        userRepository.save(user);
        return true;
    }

}
