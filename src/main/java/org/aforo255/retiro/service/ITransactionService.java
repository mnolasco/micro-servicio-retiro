package org.aforo255.retiro.service;

import org.aforo255.retiro.entity.Transaction;

public interface ITransactionService {
	
	public Transaction saveTransaction(Transaction transaction);
	public Transaction findById(Integer id);

}
