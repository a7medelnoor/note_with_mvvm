package com.a7medelnoor.dagger_with_mvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.a7medelnoor.dagger_with_mvvm.R
import com.a7medelnoor.dagger_with_mvvm.adapter.TodoAdapter
import com.a7medelnoor.dagger_with_mvvm.databinding.FragmentTodoListBinding
import com.a7medelnoor.dagger_with_mvvm.model.Todo
import com.a7medelnoor.dagger_with_mvvm.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : Fragment(R.layout.fragment_todo_list) {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoAdapter: TodoAdapter
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoListBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.fabAdTodo.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_todoListFragment_to_addTodoFragment
            )
        }
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter()
        binding.rvListTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
        viewModel.getAllTodos.observe(viewLifecycleOwner, { listTodo ->
            updateUi(listTodo)
            todoAdapter.differ.submitList(listTodo)
        })
    }

    private fun updateUi(listTodo: List<Todo>) {
        if (listTodo.isNotEmpty()) {
            binding.rvListTodo.visibility = View.VISIBLE
            binding.cardViewTvNoTodo.visibility = View.GONE
        } else {
            binding.rvListTodo.visibility = View.GONE
            binding.cardViewTvNoTodo.visibility = View.VISIBLE
        }
    }

}