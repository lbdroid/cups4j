package org.cups4j.ppd;

import java.util.ArrayList;

public class PpdSectionList extends ArrayList<PpdItemList>{
    String name;
    String text;
    
    public String getName(){
        return name;
    }
    
    public String getGroupText(){
        return text;
    }
    
    @Override
    public String toString(){
        return text;
    }
    
    public PpdSectionList deepClone(){
        PpdSectionList sectionList = new PpdSectionList();
        sectionList.name = name;
        sectionList.text = text;
        for (PpdItemList itemList: this){
            sectionList.add(itemList.deepClone());
        }
        return sectionList;
   }
}
