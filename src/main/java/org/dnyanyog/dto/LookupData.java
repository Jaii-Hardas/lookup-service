package org.dnyanyog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class LookupData {

  private Long id;

  @NotNull(message = " tenant is mandatory")
  @NotBlank(message = "tenant should not be blank")
  @Size(max = 50, message = "tenant length should be at most 50 characters")
  private String tenant;

  @NotNull(message = "Employment Status is mandatory")
  @NotBlank(message = "Employment Status should not be blank")
  @Size(max = 50, message = "Employment Status length should be at most 50 characters")
  private String employmentStatus;

  @NotNull(message = "Currency is mandatory")
  @NotBlank(message = "Currency should not be blank")
  private String currency;

  @NotNull(message = "Skill is mandatory")
  @NotBlank(message = "Skill should not be blank")
  @Size(max = 20, message = "Skill length should be at most 50 characters")
  private String skill;

  @Size(max = 50, message = "education length should be at most 50 characters")
  private String education;

  @NotNull(message = "Country is mandatory")
  @NotBlank(message = "Country should not be blank")
  @Pattern(
      regexp = "[A-Z]{2}",
      message = "Country code should be a 2-letter ISO code (e.g., US, IN)")
  private String country;

  @Size(max = 50, message = "State length should be at most 50 characters")
  private String state;

  @Size(max = 50, message = "Nationalities length should be at most 50 characters")
  private String nationalities;

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getEmploymentStatus() {
    return employmentStatus;
  }

  public void setEmploymentStatus(String employmentStatus) {
    this.employmentStatus = employmentStatus;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getNationalities() {
    return nationalities;
  }

  public void setNationalities(String nationalities) {
    this.nationalities = nationalities;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
