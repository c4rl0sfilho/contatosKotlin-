package br.senai.sp.jandira.repository

import android.content.Context
import br.senai.sp.jandira.projetocontatos.dao.ContatoDb
import br.senai.sp.jandira.projetocontatos.model.Contato

class ContatoRepository(context: Context) {
        private val db =  ContatoDb.getBancoDeDados(context).contatoDao()

            fun salvar(contato: Contato): Long{
                return db.salvar(contato)
            }

            fun buscarTodosOsContatos(): List<Contato>{
                return db.listarTodosOsContatos()
            }

            fun buscarContatoPeloId(id: Long): Contato{
                return db.listarContatoPeloId(id)
            }
}