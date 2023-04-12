package pl.ignacbartosz.bank;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bank")
@Data
public class BankConfiguration {

    private String name;

    @Value("${paging.pageSize:2}")
    private int pagingPageSize;

}
