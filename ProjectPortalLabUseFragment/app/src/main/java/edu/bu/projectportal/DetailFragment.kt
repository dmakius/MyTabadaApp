package edu.bu.projectportal

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class DetailFragment : Fragment() {
    private lateinit var projTitle:TextView
    private lateinit var projDesc:TextView
    private lateinit var projAuthor:TextView
    private lateinit var projLinks:TextView
    private lateinit var projKeywords:TextView
    private lateinit var projFavorite:CheckBox
    private lateinit var editProj: ImageButton

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        Log.d("Detail Fragment", this.toString())
        Log.d("Detail Fragment", this.activity.toString())


        //Initializing values
        projTitle = view.findViewById(R.id.projTitle)
        projDesc =  view.findViewById(R.id.projDesc)
        projAuthor = view.findViewById(R.id.textView_author)
        projLinks = view.findViewById(R.id.textView_links)
        projKeywords = view.findViewById(R.id.textView_searchTerms)
        projFavorite = view.findViewById(R.id.checkBox_favorite)
        editProj = view.findViewById(R.id.editProj)

        //setting values
        projTitle.text =  (activity as MainActivity).pNew.title
        projDesc.text = (activity as MainActivity).pNew.description
        projAuthor.text = (activity as MainActivity).pNew.author
        projLinks.text = unpackList((activity as MainActivity).pNew.projectLinks, true)
        projKeywords.text = unpackList((activity as MainActivity).pNew.keywords, false)

        checkFavorite((activity as MainActivity).pNew.favorite)

        editProj.setOnClickListener{
            parentFragmentManager.commit{
                replace<EditFragment>(R.id.container)
                addToBackStack("detail")
            }
        }

        projFavorite.setOnClickListener {
            if(projFavorite.isChecked==true){
                (activity as MainActivity).pNew.favorite = true
            }else{
                (activity as MainActivity).pNew.favorite = false
            }
        }
    }
    private fun checkFavorite(favorite:Boolean){
        if(favorite){
            projFavorite.isChecked = true;
        }
    }

    private fun unpackList(list: List<String> , newLine:Boolean): String {
        var stringList:String = ""
        for(l in list){
            if(l == list[list.size -1]){
                    stringList += l.toString()
            }else {
                if(newLine == true){
                    stringList += l.toString() + "\n"
                }else{
                    stringList += l.toString() + ", "
                }
            }
        }
        return stringList
    }

}