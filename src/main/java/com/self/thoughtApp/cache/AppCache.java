package com.self.thoughtApp.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.self.thoughtApp.entity.ConfigthoughtAppEntity;
import com.self.thoughtApp.repository.ConfigthoughtAppRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigthoughtAppRepository configthoughtAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigthoughtAppEntity> all = configthoughtAppRepository.findAll();
        for (ConfigthoughtAppEntity configthoughtAppEntity : all) {
            appCache.put(configthoughtAppEntity.getKey(), configthoughtAppEntity.getValue());
        }
    }

}
