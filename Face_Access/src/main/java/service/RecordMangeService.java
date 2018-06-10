package service;

import entity.OpenRecordEntity;

import java.util.List;

public interface RecordMangeService {
    public List<OpenRecordEntity> getRecordlist(int start, int count);
    public Long countRecord();
}
