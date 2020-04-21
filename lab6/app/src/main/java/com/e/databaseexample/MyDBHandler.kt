package com.e.databaseexample

import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import android.content.ContentValues
import androidx.annotation.IntegerRes

class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "productDB.db"
        val TABLE_PRODUCTS = "products"
        val COLUMN_ID = "_id"
        val COLUMN_PRODUCTNAME = "productname"
        val COLUMN_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCT_TABLE = ("CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCTNAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")")
        db.execSQL(CREATE_PRODUCT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS)
        onCreate(db)
    }

    fun addProduct(product: Product) {
        val values = ContentValues()
        values.put(COLUMN_PRODUCTNAME, product.productName)
        values.put(COLUMN_QUANTITY, product.quantity)
        val db = this.writableDatabase
        db.insert(TABLE_PRODUCTS, null, values)
        db.close()
    }

    fun findProduct(productname: String): Product? {
        val query = "SELECT * FROM $TABLE_PRODUCTS WHERE $COLUMN_PRODUCTNAME LIKE \"$productname\""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var product: Product? = null

        if (cursor.moveToFirst()) {
            val id = (cursor.getString(0)).toInt()
            val name = cursor.getString(1)
            val quantity = (cursor.getString(2)).toInt()
            product = Product(id, name, quantity)
            cursor.close()
        }
        db.close()
        return product
    }

    fun deleteProduct(productname: String): Boolean {
        var result = false
        var query = "SELECT * FROM $TABLE_PRODUCTS WHERE $COLUMN_PRODUCTNAME = \"$productname\""

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = (cursor.getString(0)).toInt()
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }
}