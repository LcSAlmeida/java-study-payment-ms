package br.com.lccode.paymentms.dto;

import br.com.lccode.paymentms.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {
    private Long id;
    private BigDecimal amount;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
    private Long periodId;
    private Long methodOfPayment;
}
