package controller;

import dao.AuthorityDaoInterface;
import entity.AuthorityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ACM-PC
 * Controller 层，调用多个 Service 完成 URL 请求
 * 不能复用，提供 Service 所需参数
 */
@Controller
public class AuthorityController {

    private AuthorityDaoInterface authorityDao;

    @Autowired
    public void setAuthorityDao(AuthorityDaoInterface authorityDao) {
        this.authorityDao = authorityDao;
    }

    @RequestMapping("/test")
    public String testController(Model model) {
        AuthorityEntity entity = new AuthorityEntity(5, 1608);
//        entity.setStartDate(new Date(new java.util.Date().getTime()));
//        entity.setEndDate(new Date(new java.util.Date().getTime() + 1000));
//        authorityDao.addAuthority(entity);
//        List<AuthorityEntity> list = authorityDao.getAuthoritiesOfDoor(12);
//        model.addAttribute(list);
//        List<AuthorityEntity> entityList = authorityDao.getAuthoritiesOfUser(2);
//        model.addAttribute("entity", entityList);
//        List<AuthorityEntity> authorities = authorityDao.getAuthorities();
//        model.addAttribute("f", authorities);
        return "test";
    }
}
