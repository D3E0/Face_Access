package dto;

import java.sql.Date;
import java.util.List;

/**
 * Data Transfer Object 数据传输对象
 */
public class AuthorityListDTO {

    private List<AuthorityDTO> list;
    private Long count;


    public AuthorityListDTO() {
    }

    public List<AuthorityDTO> getList() {
        return list;
    }

    public void setList(List<AuthorityDTO> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
