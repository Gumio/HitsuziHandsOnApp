package com.gumioji.hitsuziqiitaviewerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ListAdapter(context: Context, resource: Int) : ArrayAdapter<Item>(context, resource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var convertView = convertView
        if (convertView == null) {
            // 再利用可能なViewがない場合は作る
            convertView = layoutInflater.inflate(R.layout.list_item, null)
        }

        val imageView = convertView?.findViewById<ImageView>(R.id.image_view) as ImageView
        val itemTitleView = convertView.findViewById<TextView>(R.id.item_title) as TextView
        val userNameView = convertView.findViewById<TextView>(R.id.user_name) as TextView

        imageView.setImageBitmap(null) // 残ってる画像を消す（再利用された時）

        // 表示する行番号のデータを取り出す
        val result = getItem(position)

        Picasso.get().load(result.user?.profile_image_url).into(imageView)
        itemTitleView.text = result.title
        userNameView.text = result.user?.name

        return convertView
    }
}