package com.aligntech.inventory;

import com.aligntech.inventory.security.SecurityConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by mozg on 13.03.2018.
 * inventory
 */
@Configuration
@Import(SecurityConfiguration.class)
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
