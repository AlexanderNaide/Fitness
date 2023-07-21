package org.satal.backservice.security;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties("security")
public class AppProperties {

    private String cors;

}
