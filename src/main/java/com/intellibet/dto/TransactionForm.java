package com.intellibet.dto;

import lombok.Data;

@Data
public class TransactionForm {

  private String depositAmount;
  private String withdrawAmount;
  private String type = "withdraw";
  private String balance;

}
