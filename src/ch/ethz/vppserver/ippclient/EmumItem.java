package ch.ethz.vppserver.ippclient;

class EnumItem {
    
    public String name;
    public String description;
    
    public EnumItem(String name){
        this.name = name;
    }

    public EnumItem(String name, String description){
        this.name = name;
        this.description = description;
    }
    
}
