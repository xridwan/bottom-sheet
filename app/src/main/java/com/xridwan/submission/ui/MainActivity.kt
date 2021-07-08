package com.xridwan.submission.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.xridwan.submission.R
import com.xridwan.submission.adapter.MainAdapter
import com.xridwan.submission.adapter.SheetAdapter
import com.xridwan.submission.adapter.SheetListAdapter
import com.xridwan.submission.databinding.ActivityMainBinding
import com.xridwan.submission.model.Game
import com.xridwan.submission.model.Sheet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var sheetAdapter: SheetAdapter
    private lateinit var sheetListAdapter: SheetListAdapter

    private var list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.elevation = 0.0f
        BottomSheetBehavior.from(binding.layoutBottom).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        recyclerView()
        bottomSheet()

        binding.swLayout.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) bottomSheetList() else bottomSheet()
        }
    }

    private fun bottomSheet() {
        val list = arrayListOf<Sheet>()
        list.addAll(getListMenu())

        sheetAdapter = SheetAdapter(list)
        sheetAdapter.notifyDataSetChanged()

        binding.apply {
            rvMenu.layoutManager = GridLayoutManager(this@MainActivity, 4)
            rvMenu.setHasFixedSize(true)
            rvMenu.adapter = sheetAdapter
        }

        sheetAdapter.setOnItemClick(object : SheetAdapter.OnItemCLickCallBack {
            override fun onItemClicked(data: Sheet) {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DetailMenuActivity::class.java
                    ).putExtra(DetailMenuActivity.EXTRA_DATA, data)
                )
            }
        })
    }

    private fun bottomSheetList() {
        val list = arrayListOf<Sheet>()
        list.addAll(getListMenu())

        sheetListAdapter = SheetListAdapter(list)
        sheetListAdapter.notifyDataSetChanged()

        binding.apply {
            rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMenu.setHasFixedSize(true)
            rvMenu.adapter = sheetListAdapter
        }

        sheetListAdapter.setOnItemClick(object : SheetListAdapter.OnItemCLickCallBack {
            override fun onItemClicked(data: Sheet) {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DetailMenuActivity::class.java
                    ).putExtra(DetailMenuActivity.EXTRA_DATA, data)
                )
            }
        })
    }

    private fun recyclerView() {
        list.addAll(getListGame())

        mainAdapter = MainAdapter(list)
        mainAdapter.notifyDataSetChanged()

        binding.apply {
            rvGames.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGames.setHasFixedSize(true)
            rvGames.adapter = mainAdapter
        }

        mainAdapter.setOnItemClick(object : MainAdapter.OnItemCLickCallBack {
            override fun onItemClicked(data: Game) {
                selectedGame(data)
            }
        })
    }

    private fun getListGame(): ArrayList<Game> {
        val dataPhoto = resources.obtainTypedArray(R.array.avatar)
        val dataGame = resources.getStringArray(R.array.games)
        val dataRelease = resources.getStringArray(R.array.release)
        val dataDesc = resources.getStringArray(R.array.description)

        val gameList = ArrayList<Game>()
        for (position in dataGame.indices) {
            val games = Game(
                dataPhoto.getResourceId(position, -1),
                dataGame[position],
                dataRelease[position],
                dataDesc[position],
            )
            gameList.add(games)
        }
        return gameList
    }

    private fun getListMenu(): ArrayList<Sheet> {
        val dataPhoto = resources.obtainTypedArray(R.array.icon)
        val dataFeature = resources.getStringArray(R.array.feature)

        val sheetList = ArrayList<Sheet>()
        for (position in dataFeature.indices) {
            val sheets = Sheet(
                dataPhoto.getResourceId(position, -1),
                dataFeature[position],
            )
            sheetList.add(sheets)
        }
        return sheetList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option_about -> startActivity(
                Intent(
                    applicationContext,
                    AboutActivity::class.java
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private fun selectedGame(data: Game) {
        startActivity(
            Intent(
                this,
                DetailActivity::class.java
            ).putExtra(DetailActivity.EXTRA_DATA, data)
        )
    }
}