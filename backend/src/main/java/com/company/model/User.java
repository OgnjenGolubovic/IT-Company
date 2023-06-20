package com.company.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.company.model.enums.CompanyRole;
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

	//username je email
	@Column(nullable = true)
    private String username;
	
	@Column(nullable = true)
	private String password;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true)
	private String surname;

	@Column(nullable = true)
	private String state;

	@Column(nullable = true)
	private String city;

	@Column(nullable = true)
	private String street;

	@Column(nullable = true)
	private String streetNumber;

	@Column(nullable = true)
	private String phoneNumber;

	@Column(nullable = true)
	private CompanyRole companyRole;

	@Column(nullable = true)
    private boolean enabled;
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @Column
    private String secretKey;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

	@Column(nullable = true)
	private boolean tfa;

	public User() {
		super();
	}

	public User(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.state = user.getState();
		this.city = user.getCity();
		this.street = user.getStreet();
		this.streetNumber = user.getStreetNumber();
		this.phoneNumber = user.getPhoneNumber();
		this.companyRole = user.getCompanyRole();
		this.secretKey = user.getSecretKey();
		this.enabled = user.isEnabled();
		this.tfa = user.isTfa();
		this.roles = user.getRoles();
		this.lastPasswordResetDate = user.getLastPasswordResetDate();
	}
	
	public User(Integer id, String username, String password, String name, String surname, String phoneNumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}

	public User(String email, String password, String name, String surname, String state,
				String city, String street, String number, String phone, CompanyRole companyRole) {
		super();
		this.username = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.state = state;
		this.city = city;
		this.street = street;
		this.streetNumber = number;
		this.phoneNumber = phone;
		this.companyRole = companyRole;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public CompanyRole getCompanyRole() {
		return companyRole;
	}

	public void setCompanyRole(CompanyRole companyRole) {
		this.companyRole = companyRole;
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

	public void setRoles(List<Role> roles) {
	    this.roles = roles;
	}
	    
	public List<Role> getRoles() {
	    return roles;
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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

	public boolean isTfa() {
		return tfa;
	}

	public void setTfa(boolean tfa) {
		this.tfa = tfa;
	}
}
