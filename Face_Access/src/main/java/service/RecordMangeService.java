package service;

import entity.OpenRecordEntity;

import java.util.List;

public interface RecordMangeService {
     List<OpenRecordEntity> getRecordlist(int start, int count);
     List<OpenRecordEntity> getAllRecord();
     Long countRecord();
     List<OpenRecordEntity> getRecordListForSearch(int page, int limit,String keyword);
}
