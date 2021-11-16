package ru.alishev.springcourse.models;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	private String password;
	private boolean active;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
	
	@OneToMany(mappedBy="author" ,fetch=FetchType.EAGER)
	private Set<Postuniv> posts;
	
	public void addSet(Postuniv post)
	{
		posts.add(post);
	}
	public Postuniv getSet(int number)
	{
		Postuniv[] array = posts.toArray( new Postuniv[posts.size()] );
		Postuniv post=array[number];
		return post;
	}

	public Set<Postuniv> getPosts() {
		return posts;
	}

	public void setPosts(Set<Postuniv> posts) {
		this.posts = posts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
	
	
	
}
