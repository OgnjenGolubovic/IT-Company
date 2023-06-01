package com.company.model;

import com.company.model.enums.CompanyRole;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@SuppressWarnings("serial")
@DiscriminatorValue("4")
public class Administrator extends User{
    @Column(nullable = true)
    private boolean passwordChanged;

    public Administrator() {super();}

    public Administrator(String email, String name, String surname, String password, List<Role> roles) {
        super();
        this.setUsername(email);
        this.setName(name);
        this.setSurname(surname);
        this.setPassword(password);
        this.setCompanyRole(CompanyRole.ADMINISTRATOR);
        this.setRoles(roles);
        this.setEnabled(true);
        this.setPasswordChanged(true);
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }
}
