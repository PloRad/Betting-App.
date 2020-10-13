package com.intellibet.validator;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;


import com.intellibet.dto.TransactionForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class TransactionFormValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(TransactionForm.class);
  }

  @Override
  public void validate(Object o, Errors errors) {

    TransactionForm transactionForm = (TransactionForm) o;
    String amountToValidate;

    boolean isWithdraw = transactionForm.getType().equals("withdraw");

    if(isWithdraw) {
      amountToValidate = transactionForm.getWithdrawAmount();
    } else {
      amountToValidate = transactionForm.getDepositAmount();
    }


    // de facut erori si pentru withdraw
    if (isEmpty(amountToValidate) || !isNumber(amountToValidate)) {
      errors.rejectValue("depositAmount", "transactionForm.amount.invalid");
    }
    double amount = Double.parseDouble(amountToValidate);
    if (Double.compare(amount, 0) <= 0) {
      errors.rejectValue("depositAmount", "transactionForm.amount.negative");
    }
  }
}
