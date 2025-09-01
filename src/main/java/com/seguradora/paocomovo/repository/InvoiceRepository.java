package com.seguradora.paocomovo.repository;

import com.seguradora.paocomovo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String>    {
}
