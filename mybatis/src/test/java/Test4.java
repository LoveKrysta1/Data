import com.weapon.repository.ClassesRepository;
import com.weapon.repository.StudentRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test4 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //獲取實現接口的代理對象
        ClassesRepository classesRepository= sqlSession.getMapper(ClassesRepository.class);
        System.out.println(classesRepository.findById(2L));
        sqlSession.close();
        /**
         * 驗證一級緩存,查詢了兩次sql,一級緩存是自帶的,不需要任何操作,作用域是同個sqlSession對象
         *
         * 開啟了二級緩存後,只查了一次,classes對象和student對象都要實現序列化接口
         */
        sqlSession = sqlSessionFactory.openSession();
        ClassesRepository classesRepository2= sqlSession.getMapper(ClassesRepository.class);
        System.out.println(classesRepository2.findById(2L));

    }
}
