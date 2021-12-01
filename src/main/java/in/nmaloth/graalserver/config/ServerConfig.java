package in.nmaloth.graalserver.config;

import in.nmaloth.graalserver.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.PartitionAttributes;
import org.apache.geode.cache.RegionAttributes;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.PartitionAttributesFactoryBean;
import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
import org.springframework.data.gemfire.RegionAttributesFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;

@Configuration
@CacheServerApplication(logLevel = "error")
@EnableClusterConfiguration
@ComponentScan(basePackageClasses = {UserInfo.class})
//@EnableEntityDefinedRegions(basePackageClasses = {UserInfo.class})
@Slf4j
public class ServerConfig {

    @Bean()
    PartitionAttributesFactoryBean<?, ?> partitionAttributes() {

        PartitionAttributesFactoryBean<String, ?> partitionAttributesFactoryBean = new PartitionAttributesFactoryBean<>();
        partitionAttributesFactoryBean.setTotalNumBuckets(11);
        partitionAttributesFactoryBean.setRedundantCopies(0);
        return partitionAttributesFactoryBean;

    }

    @Bean("attrUserInfo")
    RegionAttributesFactoryBean<String, UserInfo> regionAttributesInstruments(PartitionAttributes<?, ?> partitionAttributes) {
        RegionAttributesFactoryBean<String, UserInfo> regionAttributesFactoryBean = new RegionAttributesFactoryBean<>();
        regionAttributesFactoryBean.setPartitionAttributes(partitionAttributes);
        return regionAttributesFactoryBean;
    }

    @Bean("user")
    PartitionedRegionFactoryBean<String,UserInfo> partitionRegionInstruments(GemFireCache gemfireCache,
                                                                               @Qualifier("attrUserInfo") RegionAttributes<String,UserInfo> regionAttributesUserInfo){

        PartitionedRegionFactoryBean<String,UserInfo> partitionedRegionFactoryBean = new PartitionedRegionFactoryBean<>();
        partitionedRegionFactoryBean.setCache(gemfireCache);
        partitionedRegionFactoryBean.setRegionName("user");
        partitionedRegionFactoryBean.setDataPolicy(DataPolicy.PARTITION);
        partitionedRegionFactoryBean.setAttributes(regionAttributesUserInfo);
        return partitionedRegionFactoryBean;

    }



}
