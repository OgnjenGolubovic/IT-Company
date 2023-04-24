package com.company.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
@DiscriminatorValue("2")
public class HumanResources extends User{
}
