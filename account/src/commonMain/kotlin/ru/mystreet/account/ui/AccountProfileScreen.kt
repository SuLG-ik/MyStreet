package ru.mystreet.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.mystreet.account.domain.entity.AccountProfileFull
import ru.mystreet.uikit.iconpack.UIKitIconPack
import ru.mystreet.uikit.iconpack.uikiticonpack.AddOutlined
import ru.mystreet.uikit.tokens.UIKitSizeTokens

@Composable
fun AccountProfileScreen(
    account: AccountProfileFull,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            AccountProfileTopBar(
                modifier = Modifier.fillMaxWidth(),
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            AccountProfileInfo(
                account = account,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            )
            AccountProfileExtra(
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountProfileTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text("Профиль")
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountProfileInfo(account: AccountProfileFull, modifier: Modifier = Modifier) {
    OutlinedCard(
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            AccountProfilePicture(account = account, modifier = Modifier.size(120.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = account.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = account.username,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = account.email,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun AccountProfilePicture(
    account: AccountProfileFull,
    modifier: Modifier = Modifier,
) {
    AccountProfileAddPicture(
        onClick = {},
        modifier = modifier,
    )
}

@Composable
fun AccountProfileAddPicture(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick,
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically)
        ) {
            Icon(
                UIKitIconPack.AddOutlined,
                contentDescription = null,
                modifier = Modifier.size(UIKitSizeTokens.DefaultIconSize)
            )
            Text(
                "Добавить",
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun AccountProfileExtra(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        FavouriteBlock(
            modifier = Modifier.fillMaxWidth()
        )
        ReviewsBlock(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FavouriteBlock(modifier: Modifier = Modifier) {
    TitledBlock(
        title = "Избранные места",
        modifier = modifier
    ) {

    }
}

@Composable
fun ReviewsBlock(modifier: Modifier = Modifier) {
    TitledBlock(
        title = "Отзывы",
        modifier = modifier
    ) {

    }
}

@Composable
fun TitledBlock(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
            maxLines = 1,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        content()
    }
}