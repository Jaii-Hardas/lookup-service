package org.dnyanyog.common;

public enum ResponseCode {
  Lookup_ADDED("Success", "Lookup added successfully!"),
  Lookup_FAILED("Fail", "Failed to add Lookup!"),
  Lookup_NOT_UPDATED("Fail", "Lookup not found or invalid request!"),
  Lookup_UPDATED("Success", "Lookup updated successfully!"),
  SEARCH_Lookup("Success", "Lookup found successfully!"),
  SEARCH_Lookup_FAILED("Fail", "Lookup not found or invalid request!"),
  NOT_DELETE_Lookup("Fail", "Lookup not deleted !"),
  DELETE_Lookup("Success", "Lookup deleted successfully !");

  private final String status;
  private final String message;

  ResponseCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
