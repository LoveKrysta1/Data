import com.weapon.repository.CustomerRepository;
import com.weapon.repository.GoodsRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test6 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //獲取實現接口的代理對象
        GoodsRepository goodsRepository= sqlSession.getMapper(GoodsRepository.class);

        System.out.println(goodsRepository.findById(3L));
        sqlSession.close();

    }
}
