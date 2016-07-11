package ca.mcgill.cs.konaila.selection.weka;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

/**
 * Generates a little ARFF file with different attribute types.
 *
 * @author FracPete
 */

public class TestWeka {
  public static void main(String[] args) throws Exception {
    ArrayList<Attribute>      atts;
    ArrayList<String>      attVals;
    Instances       data;
    double[]        vals;
    int             i;

    // 1. set up attributes
    atts = new ArrayList<Attribute>();
    // - numeric
    atts.add(new Attribute("numericAttribute"));
    // - nominal
    attVals = new ArrayList<String>();
    for (i = 0; i < 5; i++)
      attVals.add("value" + (i+1));
    atts.add(new Attribute("categoricalAttribute", attVals));
    // - string
    atts.add(new Attribute("stringAttribute", (ArrayList<String>) null));


    // 2. create Instances object
    data = new Instances("Summarization Training Data", atts, 0);

    // 3. fill with data
    // first instance
    vals = new double[data.numAttributes()];
    // - numeric
    vals[0] = Math.PI;
    // - nominal
    vals[1] = attVals.indexOf("value3");
    // - string
    vals[2] = data.attribute(2).addStringValue("This is a string!");
    // add
    data.add(new DenseInstance(1.0, vals));

    // second instance
    vals = new double[data.numAttributes()];  // important: needs NEW array!
    // - numeric
    vals[0] = Math.E;
    // - nominal
    vals[1] = attVals.indexOf("value1");
    // - string
    vals[2] = data.attribute(2).addStringValue("And another one!");
    // add
    data.add(new DenseInstance(1.0, vals));

    // 4. output data
    System.out.println(data);
  }
}