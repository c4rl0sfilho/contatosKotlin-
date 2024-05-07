package br.senai.sp.jandira.projetocontatos.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.projetocontatos.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class ContatoDb : RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    companion object{
        private lateinit var instancia: ContatoDb

        fun getBancoDeDados(context: Context): ContatoDb {
            instancia = Room
                .databaseBuilder(
                    context,
                    ContatoDb::class.java,
                    name = "db_contatos"
                    )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

            return instancia
        }
    }


}