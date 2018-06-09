package dao;

import entity.OpenRecordEntity;

import java.util.List;

public interface RecordDaoInterface {
    int addRecord(OpenRecordEntity recordEntity);

    String deleteRecord(int openid);

    String updateRecord(OpenRecordEntity recordEntity);

    OpenRecordEntity findRecord(int openid);

    List<OpenRecordEntity> getRecordList(int start, int end);
    Long countRecord();
}
