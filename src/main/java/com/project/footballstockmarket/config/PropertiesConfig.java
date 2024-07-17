import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("local")
    public DataSource localDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/localdb")
                .username("localuser")
                .password("localpass")
                .build();
    }

    @Bean
    @Profile("production")
    public DataSource productionDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://productionserver:3306/proddb")
                .username("produser")
                .password("prodpass")
                .build();
    }
}
