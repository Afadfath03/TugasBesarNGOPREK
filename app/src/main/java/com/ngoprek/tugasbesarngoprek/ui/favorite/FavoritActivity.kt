package com.ngoprek.tugasbesarngoprek.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngoprek.tugasbesarngoprek.data.local.FavoriteUser
import com.ngoprek.tugasbesarngoprek.data.model.User
import com.ngoprek.tugasbesarngoprek.databinding.ActivityFavoritBinding
import com.ngoprek.tugasbesarngoprek.ui.detail.DetailUserActivity
import com.ngoprek.tugasbesarngoprek.ui.main.UserAdapter

class FavoritActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityFavoritBinding
    private lateinit var viewModel: FavoritviewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoritviewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.onItemCallback {
            override fun OnItemClick(data: User) {
                val intent: Intent = Intent(this@FavoritActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatar_url)
                }
                startActivity(intent)
            }
        })

        binding.apply {
            rvUser.setHasFixedSize(true)
                rvUser.layoutManager = LinearLayoutManager(this@FavoritActivity)
            rvUser.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if(it != null){

                val list = mapList(it)

                adapter.setList(list)
            }
        })
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
        for (user in users){
            val userMapped = User(
                user.login,
                user.id,
                user.avatar_url
            )
            listUsers.add(userMapped)
        }

        return  listUsers
    }
}