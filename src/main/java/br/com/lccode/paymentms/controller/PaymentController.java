package br.com.lccode.paymentms.controller;

import br.com.lccode.paymentms.Service.PaymentService;
import br.com.lccode.paymentms.dto.PaymentDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Page<PaymentDTO> listAll(@PageableDefault(size = 10)Pageable pageable) {
        return paymentService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> detail(@PathVariable @NotNull Long id) {
        PaymentDTO paymentDTO = paymentService.getById(id);

        return ResponseEntity.ok(paymentDTO);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody @Valid PaymentDTO paymentDTO, UriComponentsBuilder uriBuilder) {
        PaymentDTO payment = paymentService.create(paymentDTO);
        URI path = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(path).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDTO dto) {
        PaymentDTO updatedPayment = paymentService.update(id, dto);

        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDTO> delete(@PathVariable @NotNull Long id) {
        paymentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
