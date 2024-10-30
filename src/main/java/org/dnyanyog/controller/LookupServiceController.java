package org.dnyanyog.controller;

import org.dnyanyog.dto.LookupRequest;
import org.dnyanyog.dto.LookupResponse;
import org.dnyanyog.service.LookupServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LookupServiceController {

  @Autowired LookupServiceImp lookupService;

  @PostMapping(
      path = "/api/v1/lookup/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public LookupResponse addLookup(@RequestBody LookupRequest LookupRequest) throws Exception {
    return lookupService.addLookup(LookupRequest);
  }

  @PostMapping(path = "/api/v1/lookup/{tenant}")
  public LookupResponse updateLookup(
      @PathVariable String tenant, @RequestBody LookupRequest request) {
    return lookupService.updateLookup(tenant, request);
  }

  @GetMapping(path = "/api/v1/lookup/{tenant}")
  public LookupResponse getSingleLookup(@PathVariable String tenant) {

    return lookupService.getSingleLookup(tenant);
  }

  @DeleteMapping(path = "/api/v1/lookup/{tenant}")
  public LookupResponse deleteLookup(@PathVariable String tenant) {
    return lookupService.deleteLookup(tenant);
  }
}
