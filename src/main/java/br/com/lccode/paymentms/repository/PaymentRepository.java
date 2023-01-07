package br.com.lccode.paymentms.repository;

import br.com.lccode.paymentms.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
