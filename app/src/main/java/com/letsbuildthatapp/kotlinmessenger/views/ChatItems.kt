package com.letsbuildthatapp.kotlinmessenger.views

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.letsbuildthatapp.kotlinmessenger.R
import com.letsbuildthatapp.kotlinmessenger.messages.ChatLogActivity
import com.letsbuildthatapp.kotlinmessenger.messages.ShowDetailImageMessActivity
import com.letsbuildthatapp.kotlinmessenger.models.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_from_image_row.view.*
import kotlinx.android.synthetic.main.chat_from_row.view.*
import kotlinx.android.synthetic.main.chat_from_row.view.imageview_chat_from_row
import kotlinx.android.synthetic.main.chat_to_image_row.view.*
import kotlinx.android.synthetic.main.chat_to_row.view.*
import java.io.File
import kotlin.coroutines.experimental.coroutineContext


class ChatFromItem(val activity: Activity, val text: String, val user: User, val selectedPhotoUri: String) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {

        val targetImageView = viewHolder.itemView.imageview_chat_from_row
        Picasso.get().load(user.profileImageUrl).into(targetImageView)
        if (text != "") {
            viewHolder.itemView.textview_from_row.text = text
        } else {
            val targetImageView = viewHolder.itemView.image_from_row
//            val uriIMage = Uri.fromFile(File(selectedPhotoUri))
            Log.d("THANH", selectedPhotoUri)
            Picasso.get().load(selectedPhotoUri).into(targetImageView)
            viewHolder.itemView.image_from_row.setOnClickListener {
//                Toast.makeText(activity, "SHOW IMAGE", Toast.LENGTH_SHORT).show()
//                showDialog()
                val intent = Intent(activity, ShowDetailImageMessActivity::class.java).apply {
                    putExtra("SHOW DETAIL IMAGE", selectedPhotoUri)
                }
                activity.startActivity(intent);
            }
        }

    }

    override fun getLayout(): Int {
        return if (text != "") R.layout.chat_from_row else R.layout.chat_from_image_row
    }
}

class ChatToItem(val activity: Activity, val text: String, val user: User, val selectedPhotoUri: String) : Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (text != "") {
            viewHolder.itemView.textview_to_row.text = text
            val uri = user.profileImageUrl
            val targetImageView = viewHolder.itemView.imageview_chat_to_row_avater
            Picasso.get().load(uri).into(targetImageView)

        } else {
            val uri = user.profileImageUrl
            val imageAvaterChatToItemRow = viewHolder.itemView.image_chat_tu_phia_ban_minh
            Picasso.get().load(uri).into(imageAvaterChatToItemRow)
            val imageChatToRow = viewHolder.itemView.image_show_detail_mess
            Picasso.get().load(selectedPhotoUri).into(imageChatToRow)
            viewHolder.itemView.image_show_detail_mess.setOnClickListener {
                val intent = Intent(activity, ShowDetailImageMessActivity::class.java).apply {
                    putExtra("SHOW DETAIL IMAGE", selectedPhotoUri)
                }
                activity.startActivity(intent);
            }
        }
        // load our user image into the star
    }

    override fun getLayout(): Int {
        return if (text != "") R.layout.chat_to_row
        else R.layout.chat_to_image_row
    }
}