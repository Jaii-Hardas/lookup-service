package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

@Component
public class LookupResponse {

  private String Tenant;

  private String Employment_Status;

  private String Currency;

  private String Skill;

  private String Education;

  private String Country;

  private String State;

  private String Nationalities;

  private String status;

  private String message;

  public String getEmployment_Status() {
    return Employment_Status;
  }

  public void setEmployment_Status(String employment_Status) {
    Employment_Status = employment_Status;
  }

  public String getCurrency() {
    return Currency;
  }

  public void setCurrency(String currency) {
    Currency = currency;
  }

  public String getSkill() {
    return Skill;
  }

  public void setSkill(String skill) {
    Skill = skill;
  }

  public String getCountry() {
    return Country;
  }

  public void setCountry(String country) {
    Country = country;
  }

  public String getState() {
    return State;
  }

  public void setState(String state) {
    State = state;
  }

  public String getNationalities() {
    return Nationalities;
  }

  public void setNationalities(String nationalities) {
    Nationalities = nationalities;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getEducation() {
    return Education;
  }

  public void setEducation(String education) {
    Education = education;
  }

  public String getTenant() {
    return Tenant;
  }

  public void setTenant(String tenant) {
    Tenant = tenant;
  }
}
