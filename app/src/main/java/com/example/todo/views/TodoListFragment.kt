package com.example.todo.views

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.model.TasksModel
import com.example.todo.objects.DatePickerBuilding.formatDateReadable
import com.example.todo.views.adapter.TaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TodoListFragment : Fragment() {

    val CHANNEL_ID = "notify"
    private lateinit var  spineer : Spinner
    private val modalBottomSheet = ModalBottomSheetFragment()
    private val TAG = "ModalBottomSheet"
    //private var categoryName = "No category"
    var isShown = true


    private val tasksItem = mutableListOf<TasksModel>()
    private val tasksItemCompleted = mutableListOf<TasksModel>()

    private val taskViewModel : TasksViewModel by activityViewModels()

    private val categoryViewModel:CategoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //number Of Tasks in the list
        val numberOfTasksTextView :TextView = view.findViewById(R.id.numbers_todo_textview)
        val doneTextView :TextView = view.findViewById(R.id.done_textView)
        // val fileIcon:ImageView =view.findViewById(R.id.file_icon_list)
        val eyeIcon:ImageView =view.findViewById(R.id.eye_icon)


        // TODO to show Menu of category

//        fileIcon.setOnClickListener { v: View ->
//            // show Category Popup menus
//            showMenu(v, list)
//            //showMenu(v, R.menu.popup_menu)
//
//        }

        // tasks RecyclerView
        val tasksRecyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        val taskAdapter = TaskAdapter(tasksItem,taskViewModel)
        tasksRecyclerView.adapter = taskAdapter


        // tasks complete RecyclerView
        val tasksCompleteRecyclerView : RecyclerView = view.findViewById(R.id.completedTask_recyclerView)
        val taskCompleteAdapter = TaskAdapter(tasksItemCompleted,taskViewModel)
        tasksCompleteRecyclerView.adapter = taskCompleteAdapter

        // fill database with exiting data
        categoryViewModel.addCategory(getString(R.string.option_1),getString(R.color.orange))
        categoryViewModel.addCategory(getString(R.string.option_2),getString(R.color.dark_pink))
        categoryViewModel.addCategory(getString(R.string.option_3),getString(R.color.green))
        categoryViewModel.addCategory(getString(R.string.option_4),getString(R.color.light_blue_200))


        // get data changes for tasks RecyclerView
        taskViewModel.tasksItems.observe(viewLifecycleOwner, {
            it?.let {

                tasksItem.clear()
                tasksItem.addAll(it)
                taskAdapter.notifyDataSetChanged()

                numberOfTasksTextView.text = "${tasksItem.size.toString()} tasks"

                // get pass date for notification
                var passDateList = getTasksWithPassDate(tasksItem)
                var counter = 1
                passDateList.forEach{
                    makeNotification(it.title,"Approaching date !! ${formatDateReadable(it.dueDate)}",counter)
                    counter++
                }
            }
        })


        // get data changes for tasks complete RecyclerView
        taskViewModel.tasksItemsCompleted.observe(viewLifecycleOwner, {
            it?.let {

                tasksItemCompleted.clear()
                tasksItemCompleted.addAll(it)
                taskCompleteAdapter.notifyDataSetChanged()

                if(tasksItemCompleted.size==0){
                    doneTextView.visibility = View.GONE
                    eyeIcon.visibility =View.GONE
                }else{
                    doneTextView.visibility = View.VISIBLE
                    eyeIcon.visibility =View.VISIBLE

                }
            }
        })


        // show and hide completed tasks
        eyeIcon.setOnClickListener {

            if (isShown){
                tasksCompleteRecyclerView.visibility = View.GONE
                doneTextView.visibility = View.INVISIBLE
                isShown = false
            }else{
                tasksCompleteRecyclerView.visibility = View.VISIBLE
                doneTextView.visibility = View.VISIBLE
                isShown = true
            }
        }

        val addFloatingActionButton : FloatingActionButton = view.findViewById(R.id.add_floating_button)
        val categories = resources.getStringArray(R.array.categories)
        spineer = view.findViewById<Spinner>(R.id.spinner)


        if (spineer != null)
        {
            val adapter = ArrayAdapter(activity as Context, android.R.layout.simple_spinner_dropdown_item, categories)
            spineer.adapter = adapter
        }


        addFloatingActionButton.setOnClickListener {

            modalBottomSheet.show(requireActivity().supportFragmentManager, TAG)
        }

    }

    // to create Notification
    private fun makeNotification(textTitle:String,textContent:String,notificationId:Int) {

        createNotificationChannel()

        var builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.warning_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }

    private fun getTasksWithPassDate(tasksItem:List<TasksModel>):List<TasksModel>{

        var passDateTasksItem = mutableListOf<TasksModel>()

        tasksItem.forEach{
            if (it.dueDate.isNotEmpty() && it.createdDate>=it.dueDate){
                passDateTasksItem.add(it)
            }
        }
        return passDateTasksItem
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getActivity()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // TODO to show Menu of category
//    private fun showMenu(v: View,  menuRes: List<String>) {
//        val popup = PopupMenu(requireContext()!!, v)
//
//        popup.menu.add("Test")
//        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
//
//        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
//            categoryName =menuItem.toString()
//            // Respond to menu item click.
//            Log.d("menuItem",menuItem.toString())
//            true
//        }
//        popup.setOnDismissListener {
//            // Respond to popup being dismissed.
//        }
//        // Show the popup menu.
//        popup.show()
//    }

}