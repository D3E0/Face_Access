import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.AuthorityDao;
import dao.AuthorityDaoImp;
import dao.UserDao;
import dao.UserDaoImp;
import dto.AuthorityDTO;
import dto.AuthorityListDTO;
import entity.AuthorityEntity;
import entity.UserEntity;
import manager.FaceManager;
import manager.FaceManagerImp;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.Base64Util;
import util.DateParse;
import util.EncryptInfo;
import util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class MyTest {

    @Test
    public void testAddAuthority() {
        AuthorityDao authorityDao = new AuthorityDaoImp();

        int start = 10;
        int end = 10;
        int id = 62;
        for (int i = 0; i < 19; i++) {
            AuthorityEntity entity = new AuthorityEntity(id++, 90606);
            String startDate = "2018-05-" + start++;
            String endDate = "2018-08-" + end++;
            Date startSqlDate = DateParse.stringToSql(startDate);
            Date endSqlDate = DateParse.stringToSql(endDate);
            entity.setStartDate(startSqlDate);
            entity.setEndDate(endSqlDate);
            authorityDao.addAuthority(entity);
        }
    }

    @Test
    public void testOS() {
        Properties props = System.getProperties(); //系统属性
        System.out.println("操作系统的名称：" + props.getProperty("os.name"));
        System.out.println("文件分隔符：" + props.getProperty("file.separator"));
        System.out.println("用户的账户名称：" + props.getProperty("user.name"));
        System.out.println("用户的主目录：" + props.getProperty("user.home"));
        String path = props.getProperty("user.home");
        System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
    }

    @Test
    public void testBase64() {
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();


        String raw = "admin";
        String encodeStr = encoder.encodeToString(raw.getBytes());
        String urlEncodeStr = URLEncoder.encode(encodeStr);
        System.out.println(urlEncodeStr);

        String urlDecodeStr = URLDecoder.decode(urlEncodeStr);
        String decodeStr = new String(decoder.decode(urlDecodeStr));

        System.out.println(decodeStr);
    }

    @Test
    public void testSplit() {
        String val = "root,admin";
        String[] temp = val.split("\\.");
        System.out.println(temp.length);
        for (String aTemp : temp) {
            System.out.println(aTemp);
        }
//        String username = val.substring(0, val.indexOf('.'));
//        String password = val.substring(val.indexOf('.') + 1, val.length());
    }

    @Test
    public void testEncry() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = passwordEncoder.encode(EncryptInfo.MD5("admin"));
        String rawPassword = EncryptInfo.MD5("admin");
        System.out.println(password);
        System.out.println(rawPassword);
        System.out.println(passwordEncoder.matches(rawPassword, password));
    }


    @Test
    public void insertAuthority() {
        int house[] = new int[]{120506, 90606};
        String startDate = "2018-07-01";
        String endDate = "2018-07-16";
        Date startSqlDate = DateParse.stringToSql(startDate);
        Date endSqlDate = DateParse.stringToSql(endDate);

        AuthorityDao aut = new AuthorityDaoImp();
        for (int i = 12; i < 250; i++) {
            int houseIndex = (int) (Math.random() * 2);
            AuthorityEntity entity = new AuthorityEntity(i, house[houseIndex]);
            entity.setEndDate(endSqlDate);
            entity.setStartDate(startSqlDate);
            aut.addAuthority(entity);
        }

    }

    @Test
    public void insertUser() {
        UserDao userDao = new UserDaoImp();
        File file = new File("C:\\Users\\91417\\Desktop", "name.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println("--" + str + "--");
                UserEntity entity = new UserEntity();
                entity.setUserName(str);
                String p = getTelephone();
                entity.setUserTelephone(p);
                entity.setUserPassword(EncryptInfo.MD5(p));
                System.out.println(userDao.addUser(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTelephone() {
        int[] prefix = new int[]{134, 135, 136, 137, 138, 139, 150, 151, 152, 157, 158, 159, 130, 131, 132, 145, 155, 156, 171, 175, 176, 185, 186, 153, 173, 189, 177, 170};
        int index = (int) (Math.random() * prefix.length);
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix[index]);
        for (int i = 0; i < 8; i++) {
            int x = (int) (Math.random() * 10);
            buffer.append(x);
        }
        return String.valueOf(buffer);
    }

    @Test
    public void testFace() throws IOException {
        String filePath = "C:\\Users\\ACM-PC\\Desktop\\666.jpg";
        byte[] imgData = FileUtil.readFileByBytes(filePath);
        String imgStr = Base64Util.encode(imgData);
        FaceManager faceManager = new FaceManagerImp();
// faceManager.updateFace("61101", imgStr);
//        System.out.println(str);
//        JSONObject object = JSON.parseObject(str);
//        System.out.println(object.get("error_msg"));
//        String str = faceManager.searchface(imgStr);

//        System.out.println("------" + str);
//        System.out.println();
//        jsonObject.get()

//        Face.identify();
    }

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