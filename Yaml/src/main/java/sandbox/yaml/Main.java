package sandbox.yaml;

import sandbox.yaml.model.config.YamlBasicSample;
import sandbox.yaml.model.config.YamlCollectionSample;
import sandbox.yaml.model.config.YamlNestSample;

public class Main
{
   public static void main(String[] args)
   {
      System.out.println("Started Main");

      Reader reader = new Reader();

      reader.read();

      processResults(reader);
   }

   private static void processResults(Reader reader)
   {
      System.out.println("Results:");

      processBasicSample(reader.getBasicSample());
      processNestSample(reader.getNestSample());
      processCollectionSample(reader.getCollectionSample());
   }

   private static void processBasicSample(YamlBasicSample sample)
   {
      if (sample == null)
      {
         System.out.println("Basic Sample is null.");
      }
      else
      {
         System.out.println("   Basic Sample:");
         System.out.println("      Property One: " + sample.getPropertyOne());
         System.out.println("      Property Two: " + sample.getPropertyTwo());
         System.out.println("      Property Three: " + sample.getPropertyThree());
         System.out.println();
      }
   }

   private static void processNestSample(YamlNestSample sample)
   {
      if (sample == null)
      {
         System.out.println("Nest Sample is null.");
      }
      else
      {
         System.out.println("   Nest Sample:");
         System.out.println("      Nest Property One: " + sample.getBasic().getPropertyOne());
         System.out.println("      Nest Property Two: " + sample.getBasic().getPropertyTwo());
         System.out.println("      Nest Property Three: " + sample.getBasic().getPropertyThree());
         System.out.println();
      }
   }

   private static void processCollectionSample(YamlCollectionSample sample)
   {
      if (sample == null)
      {
         System.out.println("Collection Sample is null.");
      }
      else
      {
         System.out.println("   Collection Sample:");
         System.out.println("      Basic Elements:");

         for (YamlBasicSample basics : sample.getYamlBasicSamples())
         {
            System.out.println("         Property One: " + basics.getPropertyOne());
            System.out.println("         Property Two: " + basics.getPropertyTwo());
            System.out.println("         Property Three: " + basics.getPropertyThree());
            System.out.println();
         }

         System.out.println("      Nest Elements:");

         for (YamlNestSample nests : sample.getYamlNestSamples())
         {
            System.out.println("         Property One: " + nests.getBasic().getPropertyOne());
            System.out.println("         Property Two: " + nests.getBasic().getPropertyTwo());
            System.out.println("         Property Three: " + nests.getBasic().getPropertyThree());
            System.out.println();
         }
      }
   }
}
