package sandbox.yaml.model.structure;

import java.io.File;

public class YamlPair
{
   private File defaultYaml;
   private File overrideYaml;


   public YamlPair()
   {
      // do nothing.
   }

   public void setDefaultYaml(File param)
   {
      defaultYaml = param;
   }

   public File getDefaultYaml()
   {
      return defaultYaml;
   }

   public void setOverrideYaml(File param)
   {
      overrideYaml = param;
   }

   public File getOverrideYaml()
   {
      return overrideYaml;
   }
}
