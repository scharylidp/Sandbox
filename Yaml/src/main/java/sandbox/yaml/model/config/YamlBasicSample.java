package sandbox.yaml.model.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class YamlBasicSample
{
   @JsonProperty("property-one")
   private String propertyOne;

   @JsonProperty("property-two")
   private String propertyTwo;

   @JsonProperty("property-three")
   private Integer propertyThree;


   public YamlBasicSample()
   {
      // do nothing
   }

   @JsonIgnore
   public void setPropertyOne(String param)
   {
      propertyOne = param;
   }

   @JsonIgnore
   public String getPropertyOne()
   {
      return propertyOne;
   }

   @JsonIgnore
   public void setPropertyTwo(String param)
   {
      propertyTwo = param;
   }

   @JsonIgnore
   public String getPropertyTwo()
   {
      return propertyTwo;
   }

   @JsonIgnore
   public void setPropertyThree(Integer param)
   {
      propertyThree = param;
   }

   @JsonIgnore
   public Integer getPropertyThree()
   {
      return propertyThree;
   }
}
