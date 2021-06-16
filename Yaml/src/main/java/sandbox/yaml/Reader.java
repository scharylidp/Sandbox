package sandbox.yaml;

import java.io.File;
import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import sandbox.yaml.model.config.YamlBasicSample;
import sandbox.yaml.model.config.YamlCollectionSample;
import sandbox.yaml.model.config.YamlNestSample;
import sandbox.yaml.model.structure.Config;
import sandbox.yaml.model.structure.YamlPair;

public class Reader
{
   private Config config;
   private YamlPair basicSample = new YamlPair();
   private YamlPair nestSample = new YamlPair();
   private YamlPair collectionSample = new YamlPair();


   public YamlBasicSample getBasicSample()
   {
      return hasConfig() ? config.getYamlBasicSample() : null;
   }

   public YamlNestSample getNestSample()
   {
      return hasConfig() ? config.getYamlNestSample() : null;
   }

   public YamlCollectionSample getCollectionSample()
   {
      return hasConfig() ? config.getYamlCollectionSample() : null;
   }

   public Reader()
   {
      // do nothing.
   }

   public void read()
   {
      System.out.println("Starting Reader.read().");

      config = new Config();

      fetch();
      setDefault();
      setOverride();

      System.out.println("Completed Reader.read().");
   }

   private void fetch()
   {
      System.out.println("Starting fetches.");

      String defaultRoot = "src/main/resources/default/";
      String overrideRoot = "src/main/resources/override/";

      basicSample.setDefaultYaml(fetch(defaultRoot, "yaml-basic-sample.yaml"));
      nestSample.setDefaultYaml(fetch(defaultRoot, "yaml-nest-sample.yaml"));
      collectionSample.setDefaultYaml(fetch(defaultRoot, "yaml-collection-sample.yaml"));

      basicSample.setOverrideYaml(fetch(overrideRoot, "yaml-basic-sample.yaml"));
      nestSample.setOverrideYaml(fetch(overrideRoot, "yaml-nest-sample.yaml"));
      collectionSample.setOverrideYaml(fetch(overrideRoot, "yaml-collection-sample.yaml"));

      System.out.println("Completed fetches.");
   }

   private void setDefault()
   {
      System.out.println("Starting reads.");

      config.setYamlBasicSample(read(basicSample.getDefaultYaml(), new YamlBasicSample()));
      config.setYamlNestSample(read(nestSample.getDefaultYaml(), new YamlNestSample()));
      config.setYamlCollectionSample(read(collectionSample.getDefaultYaml(), new YamlCollectionSample()));

      System.out.println("Completed reads.");
   }

   private void setOverride()
   {
      System.out.println("Starting overrides");

      if (basicSample.getOverrideYaml() != null)
      {
         YamlBasicSample base = config.getYamlBasicSample();
         YamlBasicSample ovrd = read(basicSample.getOverrideYaml(), new YamlBasicSample());

         Class<? extends YamlBasicSample> baseClass = base.getClass();
         Class<? extends YamlBasicSample> ovrdClass = ovrd.getClass();

         Field[] fields = ovrdClass.getDeclaredFields();

         for (Field f : fields)
         {
            System.out.println("Evaluating override: " + f.getName());

            try
            {
               Field t = baseClass.getDeclaredField(f.getName());
               f.setAccessible(true);

               if (f.get(ovrd) != null)
               {
                  t.setAccessible(true);
                  t.set(base, f.get(ovrd));
               }
            }
            catch (NoSuchFieldException | IllegalAccessException ex)
            {
               System.out.println("Illegal access during override.");
            }
         }
      }

      System.out.println("Completed overrides");
   }

   private File fetch(String root, String filename)
   {
      System.out.println("Starting private fetch.");

      File file = null;

      try
      {
         file = new File(root + filename);
      }
      catch (NullPointerException ex)
      {
         System.out.println("Null pointer on file retrieve.");
      }

      System.out.println("Completed private fetch.");

      return file;
   }

   private <T> T read(File file, T obj)
   {
      try
      {
         System.out.println("Reading " + obj.getClass());

         ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

         obj = mapper.readValue(file, (Class<T>) obj.getClass());
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

      System.out.println("Read complete.");

      return obj;
   }

   private boolean hasConfig()
   {
      return config != null;
   }
}
