package com.piotr.piotr.vocabtrainer;

/**
 * Created by Piotr on 03/07/2015.
 * code based on sample from:
 * http://hmkcode.com/android-simple-sqlite-database-tutorial/
 */
public class DictItem {
    private int id;
    private String item;
    private String translation;

    public DictItem(){}

    public DictItem(String item, String translation){
        super();
        this.item = item;
        this.translation = translation;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    //not sure I need it

    @Override
    public String toString(){
        return "DictItem [id=" +id+ ", item=" +item+ ", translation= " +translation+ "]";
    }
}
