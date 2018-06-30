import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import dto.AuthorityDTO;
import dto.AuthorityListDTO;
import entity.AuthorityEntity;
import entity.UserEntity;
import org.junit.Test;
import service.FaceMangeService;
import service.FaceMangeServiceImp;
import util.Base64Util;
import util.EncryptInfo;
import util.Face;
import util.FileUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class MyTest {

    @Test
    public void testSQL() {
        UserDao userDao = new UserDaoImp();
        UserEntity entity = userDao.verifyUser("1", "2");
        if (entity == null) {
            System.out.println("null");
        } else {
            System.out.println("not null");
        }
    }


    @Test
    public void testDataBase() {
        AuthorityDao authorityDao = new AuthorityDaoImp();
        AuthorityListDTO list = authorityDao.searchAuthoritiesOfOwnerLimit(1, "T", 0, 10);
        System.out.println(list.getCount());
        List<AuthorityDTO> authorities = list.getList();
        for (AuthorityDTO authority : authorities) {
            System.out.println(authority.getUserName());
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
    public void testFace() throws IOException {
        String filePath = "C:\\Users\\11835\\Desktop\\666.jpg";
        byte[] imgData = FileUtil.readFileByBytes(filePath);
        String imgStr = Base64Util.encode(imgData);
//        String imgParam = URLEncoder.encode(imgStr, "UTF-8");
        Face.search(imgStr);
    }
    @Test
    public void addFace() throws IOException {
        FaceDaoImp faceDao=new FaceDaoImp();
        byte[] imgData = new byte[0];
        for (int i=1001;i<=4000;i++){
            String filePath = "C:\\Users\\11835\\Documents\\Tencent Files\\1183503933\\FileRecv\\原始人脸 - 副本\\"+i+".jpg";
           try {
               imgData = FileUtil.readFileByBytes(filePath);
           }catch (Exception e){

           }
            String imgStr = Base64Util.encode(imgData);
//        String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            faceDao.addface(i+"",imgStr);

        }
    }
    @Test
    public void addFace1() throws IOException {
        FaceDaoImp faceDao=new FaceDaoImp();
            String filePath = "C:\\Users\\11835\\Desktop\\"+666+".jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
//        String imgParam = URLEncoder.encode(imgStr, "UTF-8");
        faceDao.addface("1",imgStr);
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