package br.com.lccode.paymentms.Service;

import br.com.lccode.paymentms.dto.PaymentDTO;
import br.com.lccode.paymentms.model.Payment;
import br.com.lccode.paymentms.model.Status;
import br.com.lccode.paymentms.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentDTO> getAll (Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(payment -> modelMapper.map(payment, PaymentDTO.class));
    }

    public PaymentDTO getById (Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("payment with not found"));

        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO create (PaymentDTO dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.CREATED);
        repository.save(payment);

        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO update (Long id, PaymentDTO dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);

        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void delete (Long id) {
        repository.deleteById(id);
    }
}
