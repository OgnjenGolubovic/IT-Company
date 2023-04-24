package com.company.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.company.model.enums.Gender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
@SuppressWarnings("serial")
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
public class User implements Serializable, UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = true)
    private String username;
	
	@Column(nullable = true)
	private String password;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true)
	private String surname;
	
	@Column(nullable = true)
	private Gender gender;
	
	@Column(nullable = true)
	private Integer jmbg;
	
	@Column(nullable = true)
    private boolean enabled;
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

	@Column(nullable = true)
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
	
	public User() {
		super();
	}
	
	
	public User(Integer id, String username, String password, String name, String surname, Gender gender, int jmbg, String phoneNumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.jmbg = jmbg;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	//mi ne koristimo username vec email
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
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }
	
	public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
	
	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getJmbg() {
		return jmbg;
	}
	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setRoles(List<Role> roles) {
	    this.roles = roles;
	}
	    
	public List<Role> getRoles() {
	    return roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", gender=" + gender + ", jmbg=" + jmbg + ", phoneNumber=" + phoneNumber + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// ovdje ce se dodati za registraciju da kad se posalje na mejl i stisne link onda se odobri
	@Override	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
	    this.enabled = enabled;
	}
}
