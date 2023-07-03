package edu.bu.projectportal

import androidx.recyclerview.widget.RecyclerView

class MyProjListRecyclerViewAdapter ( private val projects: List<Project>) : RecyclerView.Adapter<MyProjListRecyclerViewAdapter.ViewHolder>()) {

    inner class ViewHolder(View holder) : RecyclerView.ViewHolder(view root) {
        val idView: TextView = binding.projIdView
        val contentView: TextView = binding.projTitleinCard
        val cardView: CardView = binding.projectCard

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

    }
}