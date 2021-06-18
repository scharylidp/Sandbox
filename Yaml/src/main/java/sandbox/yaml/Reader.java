package sandbox.yaml;

import java.io.File;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import sandbox.yaml.model.config.Config;
import sandbox.yaml.model.config.YamlBasicSample;
import sandbox.yaml.model.config.YamlCollectionSample;
import sandbox.yaml.model.config.YamlNestSample;
import sandbox.yaml.model.structure.YamlPair;

public class Reader
{
   private final YamlPair basicSample = new YamlPair();
   private final YamlPair nestSample = new YamlPair();
   private final YamlPair collectionSample = new YamlPair();

   public Config config;


   public Reader()
   {
      // do nothing.
   }

   public void read()
   {
      config = new Config(true);

      retrieveFiles();
      setDefault();
      applyOverrideConfig();
   }

   /*
      Retrieves default and override yaml configuration files. If a file is missing or
      unable to be accessed, the File value set on the particular property is null.
    */
   private void retrieveFiles()
   {
      String defaultRoot = "src/main/resources/default/";
      String overrideRoot = "src/main/resources/override/";

      basicSample.setDefaultYaml(fetch(defaultRoot, "yaml-basic-sample.yaml"));
      nestSample.setDefaultYaml(fetch(defaultRoot, "yaml-nest-sample.yaml"));
      collectionSample.setDefaultYaml(fetch(defaultRoot, "yaml-collection-sample.yaml"));

      basicSample.setOverrideYaml(fetch(overrideRoot, "yaml-basic-sample.yaml"));
      nestSample.setOverrideYaml(fetch(overrideRoot, "yaml-nest-sample.yaml"));
      collectionSample.setOverrideYaml(fetch(overrideRoot, "yaml-collection-sample.yaml"));
   }

   /*
      Reads the fetched yaml file into a temporary parent Config object.
      Then applies the specific child object to the working parent Config.
    */
   private void setDefault()
   {
      Config tempConfig;

      tempConfig = read(basicSample.getDefaultYaml(), new Config());
      config.setYamlBasicSample(tempConfig.getYamlBasicSample());

      tempConfig = read(nestSample.getDefaultYaml(), new Config());
      config.setYamlNestSample(tempConfig.getYamlNestSample());

      tempConfig = read(collectionSample.getDefaultYaml(), new Config());
      config.setYamlCollectionSample(tempConfig.getYamlCollectionSample());
   }

   private void applyOverrideConfig()
   {
      if (basicSample.getOverrideYaml() != null)
      {
         YamlBasicSample base = config.getYamlBasicSample();
         YamlBasicSample ovrd = read(basicSample.getOverrideYaml(), new Config()).getYamlBasicSample();

         processConfigOverride(base, ovrd);
      }

      if (nestSample.getOverrideYaml() != null)
      {
         YamlNestSample base = config.getYamlNestSample();
         YamlNestSample ovrd = read(nestSample.getOverrideYaml(), new Config()).getYamlNestSample();

         processConfigOverride(base, ovrd);
      }

      if (collectionSample.getOverrideYaml() != null)
      {
         YamlCollectionSample base = config.getYamlCollectionSample();
         YamlCollectionSample ovrd = read(collectionSample.getOverrideYaml(), new Config()).getYamlCollectionSample();

         processConfigOverride(base, ovrd);
      }
   }

   private <T> void processConfigOverride(T base, T ovrd)
   {
      Field[] fields = ovrd.getClass().getDeclaredFields();

      for (Field ovrdField : fields)
      {
         try
         {
            Field baseField = base.getClass().getDeclaredField(ovrdField.getName());
            ovrdField.setAccessible(true);

            // TODO: This does not handle nesting.
            //
            if (ovrdField.get(ovrd) != null && ! ovrdField.getName().contains("initObjects"))
            {
               baseField.setAccessible(true);
               baseField.set(base, ovrdField.get(ovrd));

               System.out.println("Overwriting: " + ovrdField);
            }
         }
         catch (NoSuchFieldException | IllegalAccessException ex)
         {
            System.out.println("Illegal access during override.");
         }
      }
   }

   private File fetch(String root, String filename)
   {
      File file = null;

      try
      {
         file = new File(root + filename);

         if (! file.exists())
         {
            file = null;
         }
      }
      catch (NullPointerException ex)
      {
         System.out.println("Null pointer on file retrieve.");
      }

      return file;
   }

   private Config read(File file, Config obj)
   {
      try
      {
         ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

         obj = mapper.readValue(file, Config.class);
      }
      catch (JsonParseException jsonParseException)
      {
         System.out.println("Cannot parse element: " + obj.getClass());
         System.out.println(jsonParseException.getMessage());
      }
      catch (JsonMappingException jsonMappingException)
      {
         System.out.println("Cannot map element: " + obj.getClass());
         System.out.println(jsonMappingException.getMessage());
      }
      catch (Exception exception)
      {
         System.out.println("Unknown exception: " + obj.getClass());
         System.out.println(exception.getMessage());
      }

      return obj;
   }
}
