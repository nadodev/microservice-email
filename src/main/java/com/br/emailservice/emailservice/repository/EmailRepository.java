package com.br.emailservice.emailservice.repository;

import com.br.emailservice.emailservice.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}