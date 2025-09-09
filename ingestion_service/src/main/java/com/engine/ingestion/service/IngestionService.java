package com.engine.ingestion.service;

import com.engine.ingestion.repository.TransactionRepository;
import com.engine.ingestion.repository.ActivityLogRepository;
import com.engine.ingestion.entity.Transaction;
import com.engine.ingestion.entity.ActivityLog;
import com.engine.ingestion.grpc.TransactionDto;
import com.engine.ingestion.grpc.ActivityDto;
import org.springframework.stereotype.Service;

@Service
public class IngestionService {
    private final TransactionRepository transactionRepo;
    private final ActivityLogRepository activityRepo;

    public IngestionService(TransactionRepository transactionRepo, ActivityLogRepository activityRepo) {
        this.transactionRepo = transactionRepo;
        this.activityRepo = activityRepo;
    }

    public void saveTransaction(TransactionDto dto) {
        Transaction tx = new Transaction();
        tx.setId(dto.getId());
        tx.setDescription(dto.getDescription());
        tx.setAmount(dto.getAmount());
        tx.setCurrency(dto.getCurrency());
        tx.setStatus(dto.getStatus());
        tx.setTimestamp(dto.getTimestamp());
        transactionRepo.save(tx);
    }

    public void saveActivity(ActivityDto dto) {
        ActivityLog log = new ActivityLog();
        log.setId(dto.getId());
        log.setActivity(dto.getActivity());
        log.setDescription(dto.getDescription());
        log.setTimestamp(dto.getTimestamp());
        activityRepo.save(log);
    }
}
