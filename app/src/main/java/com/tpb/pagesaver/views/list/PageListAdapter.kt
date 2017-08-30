package com.tpb.pagesaver.views.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tpb.pagesaver.R
import com.tpb.pagesaver.data.models.Page
import kotlinx.android.synthetic.main.page_list_viewholder.view.*

/**
 * Created by theo on 30/08/17.
 */
class PageListAdapter(val clickHandler: ListClickHandler) : RecyclerView.Adapter<PageListAdapter.PageViewHolder>() {

    private val pages: ArrayList<Page> = ArrayList()

    fun addPage(page: Page) {
        pages.add(0, page)
        notifyItemInserted(0)
    }

    fun addPages(toAdd: Collection<Page>) {
        pages.addAll(0, toAdd)
        notifyItemRangeInserted(0, toAdd.size)
    }

    fun removePage(page: Page): Int {
        val index = pages.indexOf(page)
        if (index != -1) {
            pages.removeAt(index)
            notifyItemRemoved(index)
        }
        return index
    }

    fun removePages(toRemove: Collection<Page>) {
        pages.removeAll(toRemove)
        notifyDataSetChanged()
    }

    fun clear() {
        pages.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.page_list_viewholder, parent, false))
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        pages[position].let { page ->
            holder.title.text = page.title
            holder.url.text = page.url
            holder.info.text = page.published + " " + page.wordCount
            holder.itemView.setOnClickListener { clickHandler.onOpen(page) }
        }

    }

    override fun getItemCount(): Int {
        return pages.size
    }

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.page_title
        val url = itemView.page_url
        val info = itemView.page_info

    }

}