import config.WebConfig;
import dao.AuthorityDaoImp;
import dao.AuthorityDaoInterface;
import entity.AuthorityEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
public class MySpringTest {

    @Test
    public void testAuthority() {
        AuthorityDaoInterface dao = new AuthorityDaoImp();
        List list = dao.getAuthorities();
        Iterator iterable = list.iterator();
        while (iterable.hasNext()) {
            AuthorityEntity entity = (AuthorityEntity) iterable.next();
            System.out.println(entity.toString());
        }
    }


}
