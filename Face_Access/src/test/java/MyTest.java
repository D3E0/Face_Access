import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.AuthorityDao;
import dao.AuthorityDaoImp;
import entity.AuthorityEntity;
import org.junit.Test;
import util.EncryptInfo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class MyTest {

    @Test
    public void testDataBase() {
        AuthorityDao authorityDao = new AuthorityDaoImp();
        List list = authorityDao.searchAuthoritiesOfHouse(1666, "戴");
        System.out.println(list.size());
        for (Object entity : list) {
            System.out.println(entity.toString());
        }

    }

    @Test
    public void testCode() {
        System.out.println(EncryptInfo.MD5("admin"));
        System.out.println(EncryptInfo.MD5("root"));
    }


    @Test
    public void testEncrpy() {
        System.out.println(EncryptInfo.encryptName("戴霸天"));
        System.out.println(EncryptInfo.encryptName("戴天"));
        System.out.println(EncryptInfo.encryptTelephone("13105868827"));

    }


    @Test
    public void testJson() {
        JSONObject object = new JSONObject();
        object.put("result", "success");
        System.out.println(JSON.toJSONString(object));
    }


    @Test
    public void testHibernate() {
//        AuthorityDao authorityDao = new AuthorityDaoImp();
//        AuthorityEntity entity = authorityDao.getAuthority(6, 1608);
//        System.out.println(entity.toString());
//        authorityDao.deleteAuthority(1166);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long time = 0;
        try {
            time = sdf.parse("2018-09-12").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date(time);
        System.out.println(date.toString());
//        authorityDao.updateAuthority(1166, new Date(time));

//        List<AuthorityEntity> list = authorityDao.getAuthoritiesOfHouse(12605);
//        List<AuthorityEntity> entityList = authorityDao.getAuthoritiesOfUser(2);
//        List<AuthorityEntity> authorities = authorityDao.getAuthorities();
//        f(list);
//        f(entityList);
//        f(authorities);

    }

    public void f(List list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            AuthorityEntity entity = (AuthorityEntity) iterator.next();
            System.out.println(entity.toString());
        }
        System.out.println("------------------------------");
    }
}