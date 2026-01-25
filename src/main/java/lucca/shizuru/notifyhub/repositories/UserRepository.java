package lucca.shizuru.notifyhub.repositories;

import lucca.shizuru.notifyhub.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}


