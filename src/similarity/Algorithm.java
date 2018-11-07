package similarity;

import java.util.*;

/**
 * @author BB小天使
 * @author Yumen
 * @version 1.0
 */
public interface Algorithm {
    public String[] getInfo() throws Exception;
    public ArrayList<String> getName();
    public ArrayList<Double> getData();
}