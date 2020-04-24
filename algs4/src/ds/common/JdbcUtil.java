package ds.common;

import javax.sql.DataSource;
import java.util.Properties;

public class JdbcUtil {
    private static DataSource dbSource;

    static {
        try {
            Properties p = new Properties();
            p.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
//            dbSource = DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDbSource() {
        return dbSource;
    }
}
