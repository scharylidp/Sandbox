package sandbox.yaml.model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YamlNestSample
{
   @JsonProperty("basic-sample")
   private YamlBasicSample yamlBasicSample;

   @JsonIgnore
   private boolean initObjects = false;


   public YamlNestSample()
   {
      initialize();
   }

   @JsonIgnore
   public YamlNestSample(boolean initObjects)
   {
      this.initObjects = initObjects;

      initialize();
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

   @JsonIgnore
   private void initialize()
   {
      if (initObjects)
      {
         // Initialize Objects here.
         yamlBasicSample = new YamlBasicSample();
      }
      else
      {
         yamlBasicSample = null;

         initObjects = true;
      }
   }
}
