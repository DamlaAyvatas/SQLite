package com.dayvatas.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            //Musicians isimli veritabanının içinde musicians isimli bir tablo oluşturuyorum
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(name VARCHAR, age INT)")
            myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 50)")
            //veriyi çekmek için bir imleç yani bir cursor ile çalışmalıyım
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)
            //ismin ve yaşın hangi indexlerde kayıtlı olduğunu arıyorum
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            while(cursor.moveToNext()){
                //bütün hücrelere tek tek tıklayıp verileri yazdırmak için
                println("Name: "+ cursor.getString(nameIx)) //name indexte kayıtlı stringi çekiyorum
                println("Age: " + cursor.getInt(ageIx))     //age indexte kayıtlı integerı çekiyorum
            }
            cursor.close() //kodu verimli hale getirir

        }catch(e : Exception){
            e.printStackTrace()
        }
    }
}