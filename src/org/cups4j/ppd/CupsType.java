package org.cups4j.ppd;

public enum CupsType {
    KEYWORD("keyword"), ENUM("enum"), BOOLEAN("boolean"), 
    INTEGER("integer"), SETOFRANGEOFINTEGER("setOfRangeOfInteger");
   
   private final String stringValue;
   
   private CupsType(final String s) { 
       stringValue = s; 
   }
   
   public String toString() {
       return stringValue;
   }
}
