package com.example.a30tips

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30tips.Data.AnimeTip
import com.example.a30tips.ui.theme._30TipsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _30TipsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Anime30()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anime30() {
    val context = LocalContext.current
    MaterialTheme {
        val animeTitles = stringArrayResource(id = R.array.anime_titles)
        val animeTips = remember { generateAnimeTips(context, animeTitles) }

        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "30 Animes to Watch",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)) }) },
            content = { AnimeTipList(animeTips) }
        )
    }
}

@Composable
fun AnimeTipList(animeTips: List<AnimeTip>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(animeTips) { tip ->
            AnimeTipItem(tip)
        }
    }
}

@Composable
fun AnimeTipItem(animeTip: AnimeTip) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = animeTip.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = animeTip.imageId),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }
        if (expanded) {
            Text(
                text = animeTip.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

fun generateAnimeTips(context: Context, animeTitles: Array<String>): List<AnimeTip> {
    val animeTips = mutableListOf<AnimeTip>()

    for (i in 1..30) {
        val title = animeTitles[i - 1]
        val description = context.getString(
            context.resources.getIdentifier("tip$i", "string", context.packageName)
        )
        val imageId = getImageResourceId(context, title)
        animeTips.add(AnimeTip(title, description, imageId))
    }

    return animeTips
}

private fun getImageResourceId(context: Context, title: String): Int {
    return try {
        when (title) {
            "Attack on Titan" -> R.drawable._attackontitan
            "Naruto" -> R.drawable._naruto
            "One Piece" -> R.drawable._onepiece
            "Your Lie in April" -> R.drawable._yourlieinapril
            "Cowboy Bebop" -> R.drawable._cowboy_bebop
            "Spirited Away" -> R.drawable._spirited_away
            "Dragon Ball Z" -> R.drawable._dragon_ball_z
            "Death Note" -> R.drawable._death_note
            "Fullmetal Alchemist: Brotherhood" -> R.drawable._fullmetal_alchemist
            "Hunter x Hunter" -> R.drawable._0hunter_x_hunter
            "Berserk" -> R.drawable._1berserk
            "Steins;Gate" -> R.drawable._2steins_gate
            "My Hero Academia" -> R.drawable._3my_hero_academia
            "Demon Slayer: Kimetsu no Yaiba" -> R.drawable._4demon_slayer
            "Death Parade" -> R.drawable._5_death_parade
            "Monogatari Series" -> R.drawable._6monogatari
            "Sword Art Online" -> R.drawable._7sword_art_online
            "Mushishi" -> R.drawable._8mushishi
            "Given" -> R.drawable._9given
            "Haikyuu!!" -> R.drawable._0haikyuu
            "Neon Genesis Evangelion" -> R.drawable._1neon_genesis_evangelion
            "Fairy Tail" -> R.drawable._2fairy_tail
            "Kaiji: Ultimate Survivor" -> R.drawable._3kaiji
            "Mob Psycho 100" -> R.drawable._4mob_psyco_100
            "Akira" -> R.drawable._5akira
            "No Game No Life" -> R.drawable._6no_game_no_life
            "Welcome to the NHK" -> R.drawable._7nhk
            "Fate/stay night: Unlimited Blade Works" -> R.drawable._8fate
            "Higurashi: When They Cry" -> R.drawable._9higurashi
            "Ghost in the Shell" -> R.drawable._0ghost_in_the_shell
            else -> R.drawable.sample_image
        }
    } catch (e: Exception) {
        // Return the sample image drawable resource if the specified image resource is not found
        R.drawable.sample_image
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _30TipsTheme {
        Anime30()
    }
}