package com.company.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
@DiscriminatorValue("4")
public class Administrator extends User{
}
