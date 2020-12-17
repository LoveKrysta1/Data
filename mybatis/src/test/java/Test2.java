import com.weapon.entity.Account;
import com.weapon.repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 用接口代理對象方法
 */
public class Test2 {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //獲取實現接口的代理對象
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);

        /**
         * 添加對象
         */
//        Account account = new Account(3L,"haze","1234",36);
//        accountRepository.save(account);
//        sqlSession.commit();

        /**
         * 查詢所有用戶
         */
//        List<Account> list = accountRepository.findAll();
//        for(Account account:list){
//            System.out.println(account);
//        }
//        sqlSession.close();

        /**
         * 通過id查詢用戶
         */
//        Account account = accountRepository.findById(3L);
//        System.out.println(account);
//        sqlSession.close();

        /**
         * 修改對象
         */
//        Account account = accountRepository.findById(3L);
//        account.setUsername("小明");
//        account.setPassword("000");
//        account.setAge(18);
//        int result = accountRepository.update(account);
//        sqlSession.commit();
//        System.out.println(result);
//        sqlSession.close();

        /**
         * 通過id刪除對象
         */
//        int result = accountRepository.deleteById(3L);
//        System.out.println(result);
//        sqlSession.commit();
//        sqlSession.close();

        /**
         * 多參數查詢
         */
//        System.out.println(accountRepository.findByNameAndAge("weapon",22));
//        sqlSession.close();

        /**
         * 統計id個數
         */
//        System.out.println(accountRepository.count());
//        sqlSession.close();

        /**
         * 簡單的條件查詢
         */
//        System.out.println(accountRepository.findNameById(3L));
//        sqlSession.close();

        /**
         * 動態sql查詢
         */
//        Account account = new Account();
//        account.setId(2L);
//        account.setUsername("weapon");
//        account.setAge(22);
//        System.out.println(accountRepository.findByAccount(account));

        /**
         * foreach 標籤  用於in select * from t_account where id in (1,2,3)
         */
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
    }
}
