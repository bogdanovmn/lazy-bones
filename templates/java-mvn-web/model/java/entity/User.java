package ${pkgProjectPrefix}.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import com.github.bogdanovmn.common.spring.jpa.BaseEntityWithUniqueName;
import com.github.bogdanovmn.common.spring.menu.UserAuthorization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User extends BaseEntityWithUniqueName implements UserAuthorization {
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	private Date registerDate;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
		name = "role2user",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Set<UserRole> roles;

	public User(String name) {
		super(name);
	}

	@Override
	public String userName() {
		return getName();
	}

	@Override
	public boolean withRole(String role) {
		return roles.contains(new UserRole(role));
	}
}
