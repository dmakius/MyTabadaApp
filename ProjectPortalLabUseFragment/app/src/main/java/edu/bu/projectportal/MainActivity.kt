package edu.bu.projectportal

import android.graphics.Insets.add
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.fragment.app.replace


class MainActivity : AppCompatActivity(R.layout.main_activity){//, EditProjectListener {
    companion object var pNew = Project(1, "Project Test", "This is a test description. " +
            "More information will be posted in the coming labs. " +
            "TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST " +
            "TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST " +
            "TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST " +
            "TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST ",
        "Daniel Makover", true,
        listOf("www.youtube.com/project_test", "www.twitter.com/project_test"),
        listOf("Project", "Test", "First Project")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    fun test(): String {
        return "test!!"
    }
}