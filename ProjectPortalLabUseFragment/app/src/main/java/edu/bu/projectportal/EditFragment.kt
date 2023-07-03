package edu.bu.projectportal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class EditFragment : Fragment() {

    private lateinit var projTitle: EditText
    private lateinit var projDesc: EditText
    private lateinit var projAuthor: EditText
    private lateinit var projLinks: EditText
    private lateinit var projKeyowrds: EditText
    private lateinit var submit:Button
    private lateinit var cancel:Button

    companion object {
        fun newInstance() = EditFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        projTitle = view.findViewById(R.id.projTitleEdit)
        projAuthor = view.findViewById(R.id.editText_author)
        projLinks = view.findViewById(R.id.editText_links)
        projDesc =  view.findViewById(R.id.projDescEdit)
        projKeyowrds = view.findViewById(R.id.editText_keywords)

        submit = view.findViewById(R.id.submit)
        cancel = view.findViewById(R.id.cancel)

        projTitle.setText((activity as MainActivity).pNew.title)
        projAuthor.setText((activity as MainActivity).pNew.author)
        projLinks.setText(listToString((activity as MainActivity).pNew.projectLinks))
        projDesc.setText((activity as MainActivity).pNew.description)
        projKeyowrds.setText(listToString((activity as MainActivity).pNew.keywords))



        submit.setOnClickListener {
            (activity as MainActivity).pNew.title = projTitle.text.toString()
            (activity as MainActivity).pNew.author = projAuthor.text.toString()
            (activity as MainActivity).pNew.projectLinks = stringToList(projLinks.text.toString())
            (activity as MainActivity).pNew.description = projDesc.text.toString()
            (activity as MainActivity).pNew.keywords = stringToList(projKeyowrds.text.toString())
            stringToList(projKeyowrds.text.toString())

            parentFragmentManager.popBackStack()
        }

        cancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun stringToList(s:String):List<String>{
        val list = mutableListOf<String>()
        var sArray = s.split("\n")
        for(sA in sArray){
            list.add(sA)
        }
        return list
    }

    private fun listToString(list: List<String>):String {
        var s:String = ""
        for(l in list){
            if(l == list[list.size -1]){
                s += l.toString()
            }else {
                s += l.toString() + "\n"
            }
        }
        return s
    }
}

