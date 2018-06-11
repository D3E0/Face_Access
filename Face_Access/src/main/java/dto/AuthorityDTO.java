package dto;

import entity.AuthorityEntity;

import java.util.List;

/**
 * Data Transfer Object 数据传输对象
 */
public class AuthorityDTO {
    List<AuthorityEntity> list;
    Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<AuthorityEntity> getList() {
        return list;
    }

    public void setList(List<AuthorityEntity> list) {
        this.list = list;
    }
}
