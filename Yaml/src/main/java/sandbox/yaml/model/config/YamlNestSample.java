package sandbox.yaml.model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YamlNestSample
{
   @JsonProperty("basic-sample")
   private YamlBasicSample yamlBasicSample;


   public YamlNestSample()
   {
      // do nothing
   }

   @JsonIgnore
   public void setBasic(YamlBasicSample param)
   {
      yamlBasicSample = param;
   }

   @JsonIgnore
   public YamlBasicSample getBasic()
   {
      return yamlBasicSample;
   }
}
