package com.a7medelnoor.dagger_with_mvvm.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.a7medelnoor.dagger_with_mvvm.R
import com.a7medelnoor.dagger_with_mvvm.databinding.FragmentAddTodoBinding
import com.a7medelnoor.dagger_with_mvvm.model.Todo
import com.a7medelnoor.dagger_with_mvvm.viewmodel.TodoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 *  every where that we will use somethings that will be injected
 *  we need this annotation @AndroidEntryPoint
 *  except in data class, or final class
 * */
@AndroidEntryPoint
class AddTodoFragment : Fragment(R.layout.fragment_add_todo) {
    private var _binding: FragmentAddTodoBinding? = null
    private val binding get() = _binding!!

    // this is all we need to do to access our view model
    // we don't need to initializate, hilt will do it for us
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddTodoBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancle.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_addTodoFragment_to_todoListFragment
            )
        }
        binding.btnAddTodoList.setOnClickListener { mView ->
            saveTodo(mView)
        }
    }

    private fun saveTodo(mView: View) {
        val todoName = binding.edTotoTitle.text.toString()
        if (todoName.isNotEmpty()) {
            val todo = Todo(0, todoName)
            viewModel.insertTodo(todo)
            Snackbar.make(mView, " Todo added ", Snackbar.LENGTH_SHORT).show()
            mView.findNavController().navigate(
                R.id.action_addTodoFragment_to_todoListFragment
            )
        } else {
            val toast = Toast.makeText(activity, "Todo title cannot be empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}