package org.dnyanyog.service;

import jakarta.validation.Valid;
import org.dnyanyog.dto.LookupRequest;
import org.dnyanyog.dto.LookupResponse;

public interface LookupService {

  LookupResponse addLookup(@Valid LookupRequest request) throws Exception;

  LookupResponse updateLookup(String tenant, @Valid LookupRequest request) throws Exception;

  LookupResponse getSingleLookup(String tenant) throws Exception;

  LookupResponse deleteLookup(String tenant) throws Exception;
}
