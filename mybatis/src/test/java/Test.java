import com.weapon.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 使用原生接口
 */
public class Test {
    public static void main(String[] args) {
        //加載配置文件
        InputStream is = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String statement = "com.weapon.mapper.AccountMapper.save";
        Account account = new Account(1L,"weapon","123456",22);
        sqlSession.insert(statement,account);
        //最後要提交事務才能夠成功
        sqlSession.commit();
        //sqlSession要關掉,避免造成資源浪費
        sqlSession.close();
    }
}
