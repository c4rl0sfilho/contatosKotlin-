package br.senai.sp.jandira.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.projetocontatos.R
import br.senai.sp.jandira.projetocontatos.model.Contato
import br.senai.sp.jandira.projetocontatos.ui.theme.ProjetoContatosTheme
import br.senai.sp.jandira.repository.ContatoRepository

@Composable
fun FormularioCadastro(controleDeNavegacao: NavHostController) {

    val cr = ContatoRepository(LocalContext.current)


    var nomeState = remember {
        mutableStateOf("")
    }
    var foneState = remember {
        mutableStateOf("")
    }
    var emailState = remember {
        mutableStateOf("")
    }
    var dataNascimentoState = remember {
        mutableStateOf("")
    }
    var familiaState = remember {
        mutableStateOf(false)
    }
    var nomeError = remember {
        mutableStateOf(false)
    }


    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.title),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Magenta
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = nomeState.value,
                onValueChange = { nomeState.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.contact_name))
                },
                isError = nomeError.value
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = foneState.value ,
                onValueChange = { foneState.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.contact_phone))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = emailState.value ,
                onValueChange = { emailState.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.contact_mail))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = dataNascimentoState.value,
                onValueChange = { dataNascimentoState.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.birthday))
                }
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(checked = familiaState.value, onCheckedChange = { familiaState.value = it })
                Text(text = stringResource(id = R.string.family))
            }
            Button(
                onClick = {
                    //Criar um objeto contato
                    val contato = Contato(
                        nome = nomeState.value,
                        email = emailState.value,
                        telefone = foneState.value,
                        dataNascimento = dataNascimentoState.value,
                        isFamilia = familiaState.value
                    )
                    //garantir que o nome não ficara em branco
                    if (nomeState.value == ""){
                        nomeError.value = true
                    }else {
                        cr.salvar(contato)
                        controleDeNavegacao.navigate("home")
                    }
                },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

