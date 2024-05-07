package br.senai.sp.jandira.projetocontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.senai.sp.jandira.projetocontatos.model.Contato

@Dao
interface ContatoDao {

    @Insert
    fun salvar(contato: Contato) : Long

    @Query("SELECT * FROM tbl_contato ORDER BY nome ASC")
    fun listarTodosOsContatos(): List<Contato>

    @Query("SELECT * FROM tbl_contato WHERE :id")
    fun listarContatoPeloId(id: Long): Contato

}