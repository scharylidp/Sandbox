package sandbox.yaml.model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Config
{
   @JsonProperty("basic-sample")
   private YamlBasicSample yamlBasicSample;

   @JsonProperty("nest-sample")
   private YamlNestSample yamlNestSample;

   @JsonProperty("collection-sample")
   private YamlCollectionSample yamlCollectionSample;

   @JsonIgnore
   private boolean initObjects = false;


   public Config()
   {
      initialize();
   }

   @JsonIgnore
   public Config(boolean initObjects)
   {
      this.initObjects = initObjects;

      initialize();
   }

   @JsonIgnore
   public void setYamlBasicSample(YamlBasicSample param)
   {
      yamlBasicSample = param;
   }

   @JsonIgnore
   public YamlBasicSample getYamlBasicSample()
   {
      return yamlBasicSample;
   }

   @JsonIgnore
   public void setYamlNestSample(YamlNestSample param)
   {
      yamlNestSample = param;
   }

   @JsonIgnore
   public YamlNestSample getYamlNestSample()
   {
      return yamlNestSample;
   }

   @JsonIgnore
   public void setYamlCollectionSample(YamlCollectionSample param)
   {
      yamlCollectionSample = param;
   }

   @JsonIgnore
   public YamlCollectionSample getYamlCollectionSample()
   {
      return yamlCollectionSample;
   }

   @JsonIgnore
   private void initialize()
   {
      if (initObjects)
      {
         // Initialize Objects here.
         yamlBasicSample = new YamlBasicSample();
         yamlNestSample = new YamlNestSample();
         yamlCollectionSample = new YamlCollectionSample();
      }
      else
      {
         yamlBasicSample = null;
         yamlNestSample = null;
         yamlCollectionSample = null;

         initObjects = true;
      }
   }
}
