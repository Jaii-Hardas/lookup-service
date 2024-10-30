package org.dnyanyog.service;

import jakarta.validation.Valid;
import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.LookupRequest;
import org.dnyanyog.dto.LookupResponse;
import org.dnyanyog.entity.Lookup;
import org.dnyanyog.repo.LookupServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookupServiceImp {

  @Autowired private LookupServiceRepository lookupRepository;

  @Autowired private LookupResponse lookupResponse;

  public LookupResponse addLookup(@Valid LookupRequest request) {
    try {
      List<Lookup> existingLookups = lookupRepository.findByTenant(request.getTenant());

      if (!existingLookups.isEmpty()) {
        lookupResponse.setStatus(ResponseCode.Lookup_FAILED.getStatus());
        lookupResponse.setMessage(ResponseCode.Lookup_FAILED.getMessage());
        return lookupResponse;
      }

      // Create new Lookup entry
      Lookup newLookup = new Lookup();
      newLookup.setEmploymentStatus(request.getEmploymentStatus());
      newLookup.setSkill(request.getSkill());
      newLookup.setEducation(request.getEducation());
      newLookup.setCurrency(request.getCurrency());
      newLookup.setCountry(request.getCountry());
      newLookup.setState(request.getState());
      newLookup.setNationality(request.getNationalities());
      newLookup.setTenant(request.getTenant());
      newLookup.setStatus(Lookup.Status.ACTIVE);

      // Save the new Lookup entry
      newLookup = lookupRepository.save(newLookup);
      populateLookupResponse(lookupResponse, newLookup);
      lookupResponse.setStatus(ResponseCode.Lookup_ADDED.getStatus());
      lookupResponse.setMessage(ResponseCode.Lookup_ADDED.getMessage());
    } catch (Exception e) {
      lookupResponse.setStatus(ResponseCode.Lookup_ADDED.getStatus());
      lookupResponse.setMessage(ResponseCode.Lookup_ADDED.getMessage());
    }
    return lookupResponse;
  }

  // Helper method to populate the response from the Lookup entity
  private void populateLookupResponse(LookupResponse response, Lookup lookup) {
    response.setEmployment_Status(lookup.getEmploymentStatus());
    response.setSkill(lookup.getSkill());
    response.setCurrency(lookup.getCurrency());
    response.setEducation(lookup.getEducation());
    response.setCountry(lookup.getCountry());
    response.setState(lookup.getState());
    response.setNationalities(lookup.getNationality());
    response.setTenant(lookup.getTenant());
    response.getStatus(); // Ensure you set the status
  }

  public LookupResponse updateLookup(String tenant, LookupRequest request) {
    List<Lookup> optionalCases = lookupRepository.findByTenant(tenant);

    if (optionalCases.isEmpty()) {
      lookupResponse.setStatus(ResponseCode.Lookup_NOT_UPDATED.getStatus());
      lookupResponse.setMessage(ResponseCode.Lookup_NOT_UPDATED.getMessage());
    } else {
      Lookup lookup = optionalCases.get(0);
      lookup.setEmploymentStatus(request.getEmploymentStatus());
      lookup.setSkill(request.getSkill());
      lookup.setCurrency(request.getCurrency());
      lookup.setEducation(request.getEducation());
      lookup.setCountry(request.getCountry());
      lookup.setState(request.getState());
      lookup.setNationality(request.getNationalities());
      lookup.setTenant(request.getTenant());
      lookup.getStatus(); // Ensure you set the status

      lookupRepository.save(lookup);

      populateLookupResponse(lookupResponse, lookup);
      lookupResponse.setMessage(ResponseCode.Lookup_UPDATED.getMessage());
      lookupResponse.setStatus(ResponseCode.Lookup_UPDATED.getStatus());
    }

    return lookupResponse;
  }

  public LookupResponse getSingleLookup(String tenant) {
    List<Lookup> optionalLookup = lookupRepository.findByTenant(tenant);

    if (optionalLookup.isEmpty()) {
      lookupResponse.setMessage(ResponseCode.SEARCH_Lookup_FAILED.getMessage());
      lookupResponse.setStatus(ResponseCode.SEARCH_Lookup_FAILED.getStatus());
    } else {
      Lookup cases = optionalLookup.get(0);
      populateLookupResponse(lookupResponse, cases);
      lookupResponse.setMessage(ResponseCode.SEARCH_Lookup.getMessage());
      lookupResponse.setStatus(ResponseCode.SEARCH_Lookup.getStatus());
    }
    return lookupResponse;
  }

  public LookupResponse deleteLookup(String tenant) {
    List<Lookup> optionalLookup = lookupRepository.findByTenant(tenant);

    if (optionalLookup.isEmpty()) {
      lookupResponse.setMessage(ResponseCode.NOT_DELETE_Lookup.getMessage());
      lookupResponse.setStatus(ResponseCode.NOT_DELETE_Lookup.getStatus());
    } else {
      Lookup lookup = optionalLookup.get(0);
      lookup.setStatus(Lookup.Status.DELETED);
      lookupRepository.save(lookup);

      lookupResponse.setMessage(ResponseCode.DELETE_Lookup.getMessage());
      lookupResponse.setStatus(ResponseCode.DELETE_Lookup.getStatus());
      populateLookupResponse(lookupResponse, lookup);
    }
    return lookupResponse;
  }
}
