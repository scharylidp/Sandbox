package sandbox.yaml.model.structure;

import sandbox.yaml.model.config.YamlBasicSample;
import sandbox.yaml.model.config.YamlCollectionSample;
import sandbox.yaml.model.config.YamlNestSample;

public class Config
{
   private YamlBasicSample yamlBasicSample;
   private YamlNestSample yamlNestSample;
   private YamlCollectionSample yamlCollectionSample;


   public Config()
   {
      // do nothing
   }

   public void setYamlBasicSample(YamlBasicSample param)
   {
      yamlBasicSample = param;
   }

   public YamlBasicSample getYamlBasicSample()
   {
      return yamlBasicSample;
   }

   public void setYamlNestSample(YamlNestSample param)
   {
      yamlNestSample = param;
   }

   public YamlNestSample getYamlNestSample()
   {
      return yamlNestSample;
   }

   public void setYamlCollectionSample(YamlCollectionSample param)
   {
      yamlCollectionSample = param;
   }

   public YamlCollectionSample getYamlCollectionSample()
   {
      return yamlCollectionSample;
   }
}
