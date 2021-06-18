package sandbox.yaml.model.config;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YamlCollectionSample
{
   @JsonProperty("basic-samples")
   private List<YamlBasicSample> yamlBasicSamples;

   @JsonProperty("nest-samples")
   private List<YamlNestSample> yamlNestSamples;

   @JsonIgnore
   private boolean initObjects = false;


   public YamlCollectionSample()
   {
      initialize();
   }

   @JsonIgnore
   public YamlCollectionSample(boolean initObjects)
   {
      this.initObjects = initObjects;

      initialize();
   }

   @JsonIgnore
   public void setYamlBasicSamples(List<YamlBasicSample> param)
   {
      yamlBasicSamples = param;
   }

   @JsonIgnore
   public List<YamlBasicSample> getYamlBasicSamples()
   {
      return yamlBasicSamples;
   }

   @JsonIgnore
   public void setYamlnestSamples(List<YamlNestSample> param)
   {
      yamlNestSamples = param;
   }

   @JsonIgnore
   public List<YamlNestSample> getYamlNestSamples()
   {
      return yamlNestSamples;
   }

   @JsonIgnore
   private void initialize()
   {
      if (initObjects)
      {
         // Initialize Objects here.
         yamlBasicSamples = new ArrayList<>();
         yamlNestSamples = new ArrayList<>();
      }
      else
      {
         yamlBasicSamples = null;
         yamlNestSamples = null;

         initObjects = true;
      }
   }
}
