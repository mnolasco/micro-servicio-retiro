package org.aforo255.retiro.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.aforo255.retiro.entity.Transaction;
import org.aforo255.retiro.repository.TransactionRepository;

@ApplicationScoped
public class TransactionService implements ITransactionService {
	
	@Inject
	TransactionRepository repository;
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		transaction.persist();
		return transaction;
	}

	@Override
	public Transaction findById(Integer id) {
		return repository.findById(id);
	}

}
