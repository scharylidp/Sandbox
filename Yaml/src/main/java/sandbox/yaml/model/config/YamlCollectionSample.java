package sandbox.yaml.model.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YamlCollectionSample
{
   @JsonProperty("basic-samples")
   private List<YamlBasicSample> yamlBasicSamples;

   @JsonProperty("nest-samples")
   private List<YamlNestSample> yamlNestSamples;


   public YamlCollectionSample()
   {
      // do nothing
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
}
