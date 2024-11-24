package feelmeal.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
public class SecurityProperties {
   private List<String> ignorePaths;
   private List<String> postIgnorePaths;
   private List<String> patchIgnorePaths;
   private List<String> getIgnorePaths;
}
