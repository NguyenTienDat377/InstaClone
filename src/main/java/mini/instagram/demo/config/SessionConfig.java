package mini.instagram.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.security.jackson2.SecurityJackson2Modules;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableRedisHttpSession(redisNamespace = "InstaClone:demo")
public class SessionConfig implements BeanClassLoaderAware {
    @Autowired
    private ClassLoader loader;
    /**
    * Customized {@link ObjectMapper} to add mix-in for class that doesn't have
    * default
    * constructors
    * 
    * @return the {@link ObjectMapper} to use
    */
    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
        return mapper;
    }
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }
}
