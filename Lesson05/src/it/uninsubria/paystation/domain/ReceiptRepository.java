package it.uninsubria.paystation.domain;

import java.util.List;

public interface ReceiptRepository {
	List<Receipt> findAll();
	void add(Receipt receipt);
	Integer size();
}
