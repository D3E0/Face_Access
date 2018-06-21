package service;

import entity.OpenRecordEntity;

import java.util.List;

public interface RecordMangeService {
     List<OpenRecordEntity> getRecordlist(int start, int count);
     Long countRecord();
     List<OpenRecordEntity> getRecordListForSearch(int page, int limit,String keyword);
}
