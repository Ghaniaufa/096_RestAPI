package com.example.consumeapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.consumeapi.R
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.ui.home.viewmodel.KontakUIState

    @Composable
    fun HomeStatus(
        kontakUIState: KontakUIState,
        retryAction: () -> Unit,
        modifier: Modifier = Modifier,
        onDeleteClick: (Kontak) -> Unit = {},
        onDetailClick: (Kontak) -> Unit
    ){
        when(kontakUIState) {
            is KontakUIState.Loading -> Onloading(modifier = modifier.fillMaxSize())
            is KontakUIState.Success -> KontakLayout(
                kontak = kontakUIState.kontak, modifier = modifier.fillMaxWidth(),
                onDetailClick = {
                    onDetailClick(it.id)
                },
                onDeleteClick = {
                    onDeleteClick(it)
                }
            )
            is KontakUIState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
        }
    }

    /**
     * The Home screen displaying the loading message.
     */

    @Composable
    fun Onloading(modifier: Modifier = Modifier){
        Image(
            modifier = modifier,
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

    /**
     * The Home screen displaying error message with re-attempt button.
     */

    @Composable
    fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = ""
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp)
            )
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }

    @Composable
    fun KontakLayout(
        kontak: List<Kontak>,
        modifier: Modifier = Modifier,
        onDetailClick: (Kontak) -> Unit,
        onDeleteClick: (Kontak) -> Unit = {}
    ){
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(kontak) {kontak ->
                KontakCard(kontak = kontak, modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kontak) },
                    onDeleteClick = {
                        onDeleteClick(kontak)
                    }
                )
            }
        }
    }

    @Composable
    fun KontakCard(
        kontak: Kontak,
        onDeleteClick: (Kontak) -> Unit = {},
        modifier: Modifier
    ){
        Card(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = kontak.nama,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = {onDeleteClick(kontak)}) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = kontak.telpon,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = kontak.alamat,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }