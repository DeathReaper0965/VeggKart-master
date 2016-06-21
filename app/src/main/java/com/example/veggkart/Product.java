package com.example.veggkart;

/**
 * Created by Praneet on 19/4/2016.

 * _This class represents a singleton product stored in our DB_


 */
public class Product {
    public int id;
    public String productName ;
    public String description ;
    public String price ;
    public String quantity ;

    //DB interface class
  //  private BusinessLogic bl = new BusinessLogic();

    //Constructor
    public Product()
    {
       //create Local DB here
       //create Table in DB here

    }

    public Product(String _productName, String _discription, String _price)
    {
        this.productName = _productName;
        this.description = _discription;
        this.price = _price;
        //quantity = "0";

        /*
        bl.creatDB();
        bl.createTable();
        */
    }

    //function to fetch all data

    //implement fetch all data function here
    /*
    public TableQuery<Product> FetchAllData()
    {
        var db = bl.openConnection();
        var table = db.Table<Product>();
        return table;
    }
    */


    //Implement Insert into DB function here
    /*
    public String insertRecord()
    {
        try
        {
            var db = bl.openConnection();
            bl.creatDB();
            bl.createTable();
            db.Insert(this);
            return "record added";

        }
        catch (Exception e) { return "error "; }
    }
    */

}



