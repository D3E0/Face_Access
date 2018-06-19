package service;

import entity.OpenRecordEntity;

import java.util.List;

public interface RecordMangeService {
     List<OpenRecordEntity> getRecordlist(int start, int count);
     Long countRecord();
}
