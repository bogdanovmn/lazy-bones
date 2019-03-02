package ${pkgProjectPrefix}.web.orm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import com.github.bogdanovmn.common.spring.jpa.BaseEntityWithUniqueName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User extends BaseEntityWithUniqueName {
	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	private Date registerDate;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
		name = "role2user",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Set<UserRole> roles;

	public User(String name) {
		super(name);
	}
}
