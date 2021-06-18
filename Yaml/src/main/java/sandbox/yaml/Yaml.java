package sandbox.yaml;

import sandbox.yaml.model.config.YamlBasicSample;
import sandbox.yaml.model.config.YamlCollectionSample;
import sandbox.yaml.model.config.YamlNestSample;

public class Yaml
{
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String BOLD = "\u001B[1m";


   public static void main(String[] args)
   {
      Reader reader = new Reader();

      reader.read();

      processResults(reader);
   }

   private static void processResults(Reader reader)
   {
      System.out.println(ANSI_GREEN + BOLD + "RESULTS:\n" + ANSI_RESET);

      processBasicSample(reader.config.getYamlBasicSample());
      processNestSample(reader.config.getYamlNestSample());
      processCollectionSample(reader.config.getYamlCollectionSample());
   }

   private static void processBasicSample(YamlBasicSample sample)
   {
      if (sample == null)
      {
         System.out.println("Basic Sample is null.");
      }
      else
      {
         System.out.println(ANSI_YELLOW + "   Basic Sample:" + ANSI_RESET);
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
         System.out.println(ANSI_YELLOW + "   Nest Sample:" + ANSI_RESET);
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
         System.out.println(ANSI_YELLOW + "   Collection Sample:" + ANSI_RESET);

         System.out.println(ANSI_CYAN + "      Basic Elements:" + ANSI_RESET);

         for (YamlBasicSample basics : sample.getYamlBasicSamples())
         {
            System.out.println("         Property One: " + basics.getPropertyOne());
            System.out.println("         Property Two: " + basics.getPropertyTwo());
            System.out.println("         Property Three: " + basics.getPropertyThree());
            System.out.println();
         }

         System.out.println(ANSI_CYAN + "      Nest Elements:" + ANSI_RESET);

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
