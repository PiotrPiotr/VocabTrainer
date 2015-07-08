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

    /**
     * in the below constructor only the item variable probably needed as for the
     * list in CreateDrill only item is displayed
     */
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

    //returning only the item column for the DrillCration customised listView

    @Override
    public String toString(){
        return item; //original item  returned with all columns: "DictItem [id=" +id+ ", item=" +item+ ", translation= " +translation+ "]";
    }
}
