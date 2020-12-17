import com.weapon.entity.Customer;
import com.weapon.repository.ClassesRepository;
import com.weapon.repository.CustomerRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test5 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //獲取實現接口的代理對象
        CustomerRepository customerRepository= sqlSession.getMapper(CustomerRepository.class);

        System.out.println(customerRepository.findById(1L));
        sqlSession.close();

    }
}
