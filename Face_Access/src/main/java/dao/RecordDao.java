package dao;

import entity.OpenRecordEntity;

import java.util.List;

public interface RecordDao {
    int addRecord(OpenRecordEntity recordEntity);

    String deleteRecord(int openid);

    String updateRecord(OpenRecordEntity recordEntity);

    OpenRecordEntity findRecord(int openid);

    List<OpenRecordEntity> getRecordList(int page, int limit);
    List<OpenRecordEntity> getRecordListForSearch(int page, int limit,String keyword);
    Long countRecord();
}
