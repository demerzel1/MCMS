import com.dhu.service.OrderService;
import com.dhu.service.TimeService;
import com.dhu.utils.Jacksons.Jacksons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by demerzel on 2018/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class orderTest {
    @Autowired
    OrderService orderService;

    @Autowired
    TimeService timeService;

    @Test
    public void main() {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = null;
        try {
            date1 = dateFormat1.parse("2018-02-14");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date=new Date(date1.getTime());
        List<Object> list= timeService.findByMidCidDate(1,1,date);
        System.out.println(Jacksons.me().readAsString(list));
        int sz=list.size();
        for(int i=0;i<sz;++i){
            Object[] obj= (Object[]) list.get(i);
            Integer id= (Integer) obj[0];
            System.out.println(id);
            Timestamp date2=(Timestamp) obj[1];
            System.out.println(date2.toString());
        }
    }
}
