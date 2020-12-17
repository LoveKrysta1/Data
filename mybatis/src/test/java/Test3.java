import com.weapon.entity.Student;
import com.weapon.repository.AccountRepository;
import com.weapon.repository.ClassesRepository;
import com.weapon.repository.StudentRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test3 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //獲取實現接口的代理對象
        StudentRepository studentRepository= sqlSession.getMapper(StudentRepository.class);
//        System.out.println(studentRepository.findById(1L));
        /**
         * 為延遲加載提供可能
         */
        Student student = studentRepository.findByIdLazy(2L);
//        ClassesRepository classesRepository = sqlSession.getMapper(ClassesRepository.class);
//        classesRepository.findByIdLazy(student.getId());

        /**
         * 單純打印這段話,不涉及班級就進行了懶加載
         */
        System.out.println(student.getName());
        sqlSession.close();

    }
}
