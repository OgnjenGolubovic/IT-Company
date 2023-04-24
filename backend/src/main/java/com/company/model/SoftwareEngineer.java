package com.company.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
@DiscriminatorValue("1")
public class SoftwareEngineer extends User {

}
